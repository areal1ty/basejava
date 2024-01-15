package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.serialization.StreamSerializeStrategy;
import lombok.NonNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    @NonNull
    private final Path directory;
    private final StreamSerializeStrategy serializeStrategy;

    protected PathStorage(String dir, StreamSerializeStrategy serializeStrategy) {
        directory = Paths.get(dir);
        this.serializeStrategy = serializeStrategy;
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not a directory or not writable");
        }
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Error occurred while creating file " + path, null);
        }
        doUpdate(path, r);
    }

    @Override
    protected void doClear() {
        try(Stream<Path> paths = Files.list(directory)) {
            paths.forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Error occurred while deleting", null);
        }
    }

    @Override
    public int size() {
        try(Stream<Path> paths = Files.walk(directory)) {
            return (int) paths.filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            throw new StorageException("Cannot read Directory", null);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.deleteIfExists(directory.resolve(path));
        } catch (IOException e) {
            throw new StorageException("Cannot delete file", null);
        }
    }

    @Override
    protected Path findSearchKey(String searchKey) {
        return directory.resolve(searchKey);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializeStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Error occurred while reading file", null, e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected List<Resume> doGetAll() {
        try (Stream<Path> files = Files.list(directory)) {
            return files.map(this::doGet)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Error occurred while reading", null, e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume r) {
        try {
            serializeStrategy.doWrite(new BufferedOutputStream(Files.newOutputStream(path)), r);
        } catch (IOException e) {
            throw new StorageException("Could not write to this file", r.getUuid(), e);
        }
    }
}
