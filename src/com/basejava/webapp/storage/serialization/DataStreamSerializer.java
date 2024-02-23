package com.basejava.webapp.storage.serialization;

import com.basejava.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializer implements StreamSerializeStrategy{
    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume r = new Resume(uuid, fullName);
            readComponents(dis, () -> {
                    ContactType contactType = ContactType.valueOf(dis.readUTF());
                    String contactValue = dis.readUTF();
                    r.addContact(contactType, contactValue);
                });
            readComponents(dis, () -> {
                SectionType sT = SectionType.valueOf(dis.readUTF());
                r.addSection(sT, readSection(sT, dis));
            });
            return r;
        }
    }

    @Override
    public void doWrite(OutputStream os, Resume r) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            writeInCollection(r.getContacts().entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeInCollection(r.getSections().entrySet(), dos, entry -> {
                SectionType t = entry.getKey();
                Section s = entry.getValue();
                dos.writeUTF(t.name());
                switch (t) {
                    case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS -> writeInCollection(((ListSection) s).getItems(), dos, dos::writeUTF);
                    case EXPERIENCE, EDUCATION -> writeInCollection(((OrganizationSection) s).getOrganization(), dos, organization -> {
                        dos.writeUTF(organization.getWebsite().getTitle());
                        dos.writeUTF(organization.getWebsite().getUrl());
                        writeInCollection(organization.getPeriods(), dos, period -> {
                            writeLD(period.getDateOfStart(), dos);
                            writeLD(period.getDateOfEnd(), dos);
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        });
                    });
                }
            });
        }
    }

    private Section readSection(SectionType sectionType, DataInputStream dis) throws IOException{
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS -> new ListSection(readInArrayList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(
                    readInArrayList(dis, () -> new Organization(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readInArrayList(dis, () -> new Period(
                                    readLD(dis), readLD(dis),
                                    dis.readUTF(), dis.readUTF())))));
        };
    }

    private LocalDate readLD(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeLD (LocalDate ld, DataOutputStream dos) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonthValue());
    }

    private <T> List<T> readInArrayList(DataInputStream dis, ComponentReader<T> componentReader) throws IOException{
        int size = dis.readInt();
        List<T> l = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            l.add(componentReader.read());
        }
        return l;
    }

    private void readComponents(DataInputStream dis, ComponentProcessor p) throws IOException{
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            p.process();
        }
    }

    private <T> void writeInCollection(Collection<T> collection, DataOutputStream dos, ComponentWriter<T> componentWriter) throws IOException{
        dos.writeInt(collection.size());
        for (T component : collection) {
            componentWriter.write(component);
        }
    }

    private interface ComponentReader<T> {
        T read() throws IOException;
    }

    private interface ComponentWriter<T> {
        void write (T t) throws IOException;
    }

    private interface ComponentProcessor {
        void process() throws IOException;
    }
}
