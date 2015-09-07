package net.morlhon.exos.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreFTW {

    public class ReadingThread extends Thread {
        private final SharedData shared;
        private final Semaphore semaphore;

        public ReadingThread(SharedData shared, Semaphore semaphore) {
            this.shared = shared;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            if (!shared.isReady()) {
                System.out.println(getName() + " is waiting");
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(getName() + " --> I'm free !!");
                semaphore.release();
            }
        }
    }

    public class WritingThread extends Thread {
        private final SharedData shared;
        private final Semaphore semaphore;

        public WritingThread(SharedData shared, Semaphore semaphore) {
            this.shared = shared;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            shared.ready();
            semaphore.release();
            System.out.println(getName() + " is ready !");
        }
    }


    public void go() {
        SharedData data = new SharedData();
        Semaphore semaphore = new Semaphore(1);

        new WritingThread(data, semaphore).start(); //<-- this is not good, first call blocks critical section using this.
        // must be done before anyother call...

        for (int i = 0; i < 1000; i++) {
            new ReadingThread(data, semaphore).start();
        }
    }

    public static void main(String[] args) {
        new SemaphoreFTW().go();
    }
}
