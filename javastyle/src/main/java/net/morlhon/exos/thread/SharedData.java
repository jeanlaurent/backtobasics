package net.morlhon.exos.thread;

public class SharedData {
    private boolean ready = false;

    public synchronized boolean isReady() {
        return ready;
    }

    public synchronized void ready() {
        this.ready = true;
    }
}


