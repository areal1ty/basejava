package com.basejava.webapp.test;

public class TestSingleton {
private static volatile TestSingleton object = null;
private TestSingleton() {}

    public static TestSingleton getInstance() {
    if (object == null) {
        synchronized (TestSingleton.class) {
            if (object == null) {
                object = new TestSingleton();
            }
        }
    }
        return object;
    }
}