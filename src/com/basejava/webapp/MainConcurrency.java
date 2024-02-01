package com.basejava.webapp;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {

    public static final int NUMBER_OF_THREADS = 10000;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

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

    public static void main(String[] args) throws InterruptedException {
        final Object firstResource = new Object();
        final Object secondResource = new Object();

        deadLock(firstResource, secondResource);
        deadLock(secondResource, firstResource);

        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }

        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_THREADS);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Future<Integer> future = executorService.submit(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(DATE_FORMAT);
                }
                latch.countDown();
                return 5;
            });
            latch.await(10, TimeUnit.SECONDS);
            executorService.shutdown();
            System.out.println(mainConcurrency.atomicCounter.get());
        }
    }

    private void inc() {
        atomicCounter.incrementAndGet();
    }

}
