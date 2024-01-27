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
            readComponents(() -> r.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()), dis);
            readComponents(() -> {
                SectionType sT = SectionType.valueOf(dis.readUTF());
                r.addSection(sT, readSection(sT, dis));
            }, dis);
            return r;
        }
    }

    @Override
    public void doWrite(OutputStream os, Resume r) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            for (Map.Entry<ContactType, String> entry : r.getContacts().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            writeInCollection(r.getSections().entrySet(), entry -> {
                SectionType t = entry.getKey();
                Section s = entry.getValue();
                dos.writeUTF(t.name());
                switch (t) {
                    // case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) s).getText());
                    case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS -> writeInCollection(((ListSection) s).getItems(), dos::writeUTF, dos);
                    case EXPERIENCE, EDUCATION -> writeInCollection(((OrganizationSection) s).getOrganization(), organization -> {
                        dos.writeUTF(organization.getWebsite().getTitle());
                        dos.writeUTF(organization.getWebsite().getUrl());
                        writeInCollection(organization.getPeriods(), period -> {
                            writeLD(period.getDateOfStart(), dos);
                            writeLD(period.getDateOfEnd(), dos);
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        }, dos);
                    }, dos);
                }
            }, dos);
        }
    }

    private Section readSection(SectionType sectionType, DataInputStream dis) throws IOException{
        return switch (sectionType) {
            // case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case PERSONAL, OBJECTIVE, ACHIEVEMENTS, QUALIFICATIONS -> new ListSection(readArrayList(dis::readUTF, dis));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(
                    readArrayList(() -> new Organization(
                            dis.readUTF(), dis.readUTF(),
                            readArrayList(() -> new Period(
                                    readLD(dis), readLD(dis),
                                    dis.readUTF(), dis.readUTF()), dis)), dis));
        };
    }

    private LocalDate readLD(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void writeLD (LocalDate ld, DataOutputStream dos) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonthValue());
    }

    private <T> List<T> readArrayList(ComponentReader<T> componentReader, DataInputStream dis) throws IOException{
        int size = dis.readInt();
        List<T> l = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            l.add(componentReader.read());
        }
        return l;
    }

    private void readComponents(ComponentProcessor p, DataInputStream dis) throws IOException{
        int size = dis.readInt();
        if (dis.available() >= 4) {
        for (int i = 0; i < size; i++) {
            p.process();
        }}
    }

    private <T> void writeInCollection(Collection<T> collection, ComponentWriter<T> componentWriter, DataOutputStream dos) throws IOException{
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
