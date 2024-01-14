package com.basejava.webapp.storage.serialize;

import com.basejava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializeStrategy {

    Resume doRead(InputStream is) throws IOException;
    void doWrite(OutputStream os, Resume r) throws IOException;

}
