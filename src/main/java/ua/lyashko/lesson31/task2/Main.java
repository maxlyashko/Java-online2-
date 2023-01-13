package ua.lyashko.lesson31.task2;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Main {

    @SneakyThrows
    public static void main ( String[] args ) {
        List<Integer> list = List.of ( 2 , 3 , 5 , 7 , 11 , 12 );
        List[] lists = split ( list );
        Counter counter1 = new Counter ( lists[0] );
        Counter counter2 = new Counter ( lists[1] );

        MyThread thread1 = new MyThread ( counter1 );
        MyThread thread2 = new MyThread ( counter2 );
        thread1.start ( );
        thread2.start ( );
        thread1.join ();
        thread2.join ( );

        System.out.println ( thread1.counter.getCounter () + thread2.counter.getCounter () );

    }

    public static List[] split ( List<Integer> list ) {
        List<Integer> first = new ArrayList<> ( );
        List<Integer> second = new ArrayList<> ( );
        int size = list.size ( );
        for (int i = 0; i < size; i++) {
            if (i < ( size + 1 ) / 2) {
                first.add ( list.get ( i ) );
            } else {
                second.add ( list.get ( i ) );
            }
        }
        return new List[]{first , second};
    }
}

