package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    @NonNull
    private final File directory;
    private static final Logger LOG = Logger.getLogger(AbstractFileStorage.class.getName());

    protected AbstractFileStorage(File directory) {
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "  is not a directory");
        }
        else if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("cannot get access to this file " + directory.getAbsolutePath());
        } else {
            this.directory = directory;
        }
    }


    @Override
    protected void doSave(File file, Resume r) {
        try {
            if(file.createNewFile()) doWrite(file, r);
        } catch (IOException e) {
            throw new StorageException("IO exception occurred", file.getName(), e);
        }
    }

    @Override
    protected void doClear() {
        try(RandomAccessFile raf = new RandomAccessFile(directory, "rws")) {
            raf.setLength(0);
        } catch (IOException e) {
            throw new StorageException("IO Exception, file not found", directory.getName(), e);
        }
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        int size = 0;
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                size += file.length();
            }
        }
        return size;
    }

    @Override
    protected void doDelete(File file) {
        if (file.delete()) LOG.info("file deleted");
        else {
           LOG.info("Error occurred while deleting. File does not exist");
        }
    }

    @Override
    protected File findSearchKey(String searchKey) {
        return new File(directory, searchKey);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> res = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : Objects.requireNonNull(files)) {
            Resume r = doGet(file);
            res.add(r);
        }
        return res;
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            doWrite(file, r);
        } catch (IOException e) {
            throw new StorageException("IO Exception occurred", file.getName(), e);
        }
    }

    protected abstract void doWrite(File file, Resume r) throws IOException;
    protected abstract Resume doRead(File file) throws IOException;

}
