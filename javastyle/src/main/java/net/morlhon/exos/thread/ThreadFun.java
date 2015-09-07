package net.morlhon.exos.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

public class ThreadFun {

    public static void main(String[] args) throws IOException, URISyntaxException {
        long start = new Date().getTime();
        for (int i = 0; i < 10; i++) {
            new Thread(new GetSerpodileRunnable(),"thread-" + i).start();
        }
        long end = new Date().getTime();
        System.out.println("End time: " + ((end - start)) + "ms");
    }

    public static String curl(String url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        final StringBuilder builder = new StringBuilder();
        bufferedReader.lines().forEach(line -> builder.append(line + "\n"));
        return builder.toString();
    }

    public static class GetSerpodileRunnable implements Runnable {

        @Override
        public void run() {
            long start = new Date().getTime();
            try {
                curl("http://serpodile.com");
            } catch (IOException e) {
                System.out.println(e);
            }
            long end = new Date().getTime();
            System.out.println( Thread.currentThread().getName() + " > " + ((end - start)) + "ms");
        }
    }

    public static class GetSerpodileThread extends Thread {
        @Override
        public void run() {
            long start = new Date().getTime();
            try {
                curl("http://serpodile.com");
            } catch (IOException e) {
                System.out.println(e);
            }
            long end = new Date().getTime();
            System.out.println( getName() + " > " + ((end - start)) + "ms");
        }
    }

}
