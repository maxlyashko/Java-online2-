package ua.lyashko.lesson32.task1;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Data
@Getter
public class Counter implements Runnable {
    private static int counter = 50;
    private final int limit = 250;
    private final int threadPoolSize = 100;
    private static ReentrantLock locker = new ReentrantLock ( );


    @Override
    public void run () {
        increaseCounter ( );
    }

    private void increaseCounter () {
        locker.lock ( );
        System.out.println ( Thread.currentThread ( ).getName ( ) + " : " + counter );
        counter = counter + 2;
        locker.unlock ();
    }
}
