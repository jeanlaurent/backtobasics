package net.morlhon.exos.thread;

import java.util.concurrent.*;

public class NoFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit( () -> {
            System.out.println("Starting long running task");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Long running task is now over.");
            return "foobar";
        });
        System.out.println("I'm doing something else while waiting for the long running task to finish");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I'm done with my things, still waiting for the future to finish");
        System.out.println("Result from long running task : " + future.get());


    }
}
