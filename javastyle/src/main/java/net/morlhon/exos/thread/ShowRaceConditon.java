package net.morlhon.exos.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShowRaceConditon {

    interface Counter {

        int inc();

        int get();

    }

    class SynchronizedCounter implements Counter {
        private int count = 0;

        public int inc() {
            synchronized (this) {
                return ++count;
            }
        }

        public int get() {
            return count;
        }

    }

    class NaiveCounter implements Counter {
        private int count = 0;

        public int inc() {
            int newCount = ++count;
            return newCount;
        }

        public int get() {
            return count;
        }
    }

    class CounterWithLock implements Counter {
        private Lock lock = new ReentrantLock();
        private int count = 0;

        public int inc() {
            lock.lock();
            int newCount = ++count;
            lock.unlock();
            return newCount;
        }

        public int get() {
            return count;
        }
    }

    public void go() throws InterruptedException {
        incrementCounter(new CounterWithLock(), 1000);
        incrementCounter(new NaiveCounter(), 1000);
        incrementCounter(new SynchronizedCounter(), 1000);

    }

    private void incrementCounter(Counter counter, int numberOfTime) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < numberOfTime; i++) executorService.execute(new CounterThread(counter));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(counter.getClass().getSimpleName() + " -> " + counter.get());
    }

    public static void main(String[] args) throws InterruptedException {
        new ShowRaceConditon().go();

    }

    public static class CounterThread extends Thread {
        private final Counter counter;

        public CounterThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            counter.inc();
        }
    }

}
