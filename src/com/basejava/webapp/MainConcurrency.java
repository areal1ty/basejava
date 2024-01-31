package com.basejava.webapp;

public class MainConcurrency {
    private final Object firstResource = new Object();
    private final Object secondResource = new Object();

    public void firstMethod() {
        synchronized (firstResource) {
            System.out.println("1st resource has acquired");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (secondResource) {
                System.out.println("2nd resource has acquired");
            }
        }
    }

    public void secondMethod() {
        synchronized (secondResource) {
            System.out.println("2nd resource has acquired");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (firstResource) {
                System.out.println("1nd resource has acquired");
            }
        }
    }

    public static void main(String[] args) {
        MainConcurrency main = new MainConcurrency();
        Thread firstThread = new Thread(main::firstMethod);
        Thread secondThread = new Thread(main::secondMethod);

        firstThread.start();
        secondThread.start();
    }
}
