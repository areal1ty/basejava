package com.basejava.webapp.test;

import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.SortedArrayStorage;
import com.basejava.webapp.storage.Storage;

public class MainTestSortedArrayStorage {
    static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("6");
        Resume r2 = new Resume("2");
        Resume r3 = new Resume("7");
        Resume r4 = new Resume("3");
        Resume r5 = new Resume("3");


        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r4);
        SORTED_ARRAY_STORAGE.save(r5);

        SORTED_ARRAY_STORAGE.update(r5);

        System.out.println("Get r1: " + SORTED_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + SORTED_ARRAY_STORAGE.get("dummy"));

        printAll();
        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        SORTED_ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
