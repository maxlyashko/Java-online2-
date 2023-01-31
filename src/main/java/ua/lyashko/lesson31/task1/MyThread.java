package ua.lyashko.lesson31.task1;

public class MyThread extends Thread {
    public static synchronized Thread[] createThreadArray () {
        Thread[] arr = new Thread[50];
        for (int i = 0; i <= arr.length - 1; i++) {
            arr[i] = new MyThread ( );
        }
        return arr;
    }

    @Override
    public void run () {
        System.out.println ( "Hello from thread " + Thread.currentThread ( ).getName ( ) );
    }
}
