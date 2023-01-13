package ua.lyashko.lesson31.task1;

public class Main {
    public static void main ( String[] args ) {
        Thread[] threads = MyThread.createThreadArray ( );
        for (int i = threads.length - 1; i >= 0; i--) {
            threads[i].start ( );
            try {
                threads[i].join ( );
            } catch ( InterruptedException e ) {
                e.printStackTrace ( );
            }
        }
    }
}
