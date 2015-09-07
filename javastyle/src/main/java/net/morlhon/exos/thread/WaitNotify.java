package net.morlhon.exos.thread;

public class WaitNotify {

    public class SharedData {
        private boolean ready = false;

        public synchronized boolean isReady() {
            return ready;
        }

        public synchronized void ready() {
            this.ready = true;
        }
    }

    public class Monitor {}

    public class ReadingThread extends Thread {
        private final SharedData shared;
        private final Monitor monitor;

        public ReadingThread(SharedData shared, Monitor monitor) {
            this.shared = shared;
            this.monitor = monitor;
        }

        @Override
        public void run() {
//            while (!shared.ready()) {
//                System.out.println(getName() + " --> waiting." );
//            }
            if(!shared.isReady()) {
                synchronized (monitor) {
                    try {
                        System.out.println(getName() + " Waiting");
                        monitor.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println(getName() + " --> I'm free !!");
        }
    }

    public class WritingThread extends Thread {
        private final SharedData shared;
        private final Monitor monitor;


        public WritingThread(SharedData shared, Monitor monitor) {
            this.shared = shared;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            shared.ready();
            synchronized (monitor) {
                monitor.notifyAll(); // notify free only 1 thread, 999 others are stuck
            }

        }
    }


    public void go() {
        SharedData data = new SharedData();
        Monitor monitor = new Monitor();
        for(int i=0;i<1000; i++) {
            new ReadingThread(data, monitor).start();
        }
        new WritingThread(data, monitor).start();
    }

    public static void main(String[] args) {
        new WaitNotify().go();
    }



}
