package com.basejava.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Resume" + uuid + "don't exist", uuid);
    }
}