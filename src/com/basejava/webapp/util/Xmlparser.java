package com.basejava.webapp.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.Reader;
import java.io.Writer;

public class Xmlparser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public Xmlparser(Class<?>... classesToBeFound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeFound);

            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public <T> T unmarshall(Reader r, Class<T> targetType) {
        try {
            return targetType.cast(unmarshaller.unmarshal(r));
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public void marshall(Object instance, Writer w) {
        try {
            marshaller.marshal(instance, w);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}
