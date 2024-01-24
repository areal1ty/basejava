package com.basejava.webapp.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

public class Xmlparser {
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public Xmlparser(Class... classesToBeFound) {
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
