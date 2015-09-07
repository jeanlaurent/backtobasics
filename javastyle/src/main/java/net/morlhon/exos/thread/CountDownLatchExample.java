package net.morlhon.exos.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {


    public class ReadingThread extends Thread {
        private final SharedData shared;
        private final CountDownLatch latch;

        public ReadingThread(SharedData shared, CountDownLatch latch) {
            this.shared = shared;
            this.latch = latch;
        }

        @Override
        public void run() {
            if (!shared.isReady()) {
                System.out.println(getName() + " is waiting");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(getName() + " --> I'm free !!");
            }
        }
    }

    public class WritingThread extends Thread {
        private final SharedData shared;
        private final CountDownLatch latch;

        public WritingThread(SharedData shared, CountDownLatch latch) {
            this.shared = shared;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            shared.ready();
            latch.countDown();
            System.out.println(getName() + " is ready !");
        }
    }


    public void go() {
        SharedData data = new SharedData();
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 1000; i++) {
            new ReadingThread(data, latch).start();
        }

        new WritingThread(data, latch).start();
    }

    public static void main(String[] args) {
        new CountDownLatchExample().go();
    }
}
