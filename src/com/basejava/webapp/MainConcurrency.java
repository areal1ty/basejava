package com.basejava.webapp;

public class MainConcurrency {

    private static void deadLock(Object firstResource, Object secondResource) {
        new Thread(() -> {
            synchronized (firstResource) {
                System.out.println("1st resource has been acquired");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (secondResource) {
                    System.out.println("2nd resource has been acquired");
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        final Object firstResource = new Object();
        final Object secondResource = new Object();

        deadLock(firstResource, secondResource);
        deadLock(secondResource, firstResource);
    }
}
