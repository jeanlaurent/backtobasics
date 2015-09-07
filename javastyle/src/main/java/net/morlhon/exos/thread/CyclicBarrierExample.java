package net.morlhon.exos.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public class ReadingThread extends Thread {
        private final SharedData shared;
        private final CyclicBarrier barrier;

        public ReadingThread(SharedData shared, CyclicBarrier barrier) {
            this.shared = shared;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            if (!shared.isReady()) {
                System.out.println(getName() + " is waiting");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(getName() + " --> I'm free !!");
            }
        }
    }

    public class WritingThread extends Thread {
        private final SharedData shared;
        private final CyclicBarrier barrier;

        public WritingThread(SharedData shared, CyclicBarrier barrier) {
            this.shared = shared;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            shared.ready();
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println(getName() + " is ready !");
        }
    }


    public void go() {
        SharedData data = new SharedData();
        CyclicBarrier barrier = new CyclicBarrier(1001, () -> System.out.println("unleash !!"));

        for (int i = 0; i < 1000; i++) {
            new ReadingThread(data, barrier).start();
        }

        new WritingThread(data, barrier).start();
    }

    public static void main(String[] args) {
        new CountDownLatchExample().go();
    }

}
