package com.basejava.webapp.util;

public class LazySingleton {

    private LazySingleton() {

    }

    private static volatile LazySingleton INSTANCE;

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }

    /*
The following is the implementation of Initialization-on-demand holder idiom

DIFFERENCE APPROACH [Double checked locking]-->

    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                INSTANCE = new LazySingleton();
            }
            }
        }
        return INSTANCE;
    }
     */
}
