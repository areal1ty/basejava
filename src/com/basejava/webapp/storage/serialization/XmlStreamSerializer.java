package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.*;
import com.basejava.webapp.util.Xmlparser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSerializer implements StreamSerializeStrategy{
    private final Xmlparser xmlParser;

    public XmlStreamSerializer() {
    xmlParser = new Xmlparser(
            Resume.class, Organization.class, Link.class,
            OrganizationSection.class, ListSection.class,
            Period.class
    );
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r, Resume.class);
        }
    }

    @Override
    public void doWrite(OutputStream os, Resume r) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }
}
