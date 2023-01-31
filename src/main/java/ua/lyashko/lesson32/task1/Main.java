package ua.lyashko.lesson32.task1;

import java.util.concurrent.*;

public class Main {

    public static void main ( String[] args ) throws ExecutionException, InterruptedException {
        Counter counter = new Counter ();
        ExecutorService executorService = Executors.newFixedThreadPool ( counter.getThreadPoolSize () );
        for (int i = 0; i <= counter.getThreadPoolSize (); i++) {
            executorService.submit ( new Counter () );
        }
        executorService.shutdown ();
    }
}
