package ua.lyashko.lesson32.task2;

import lombok.SneakyThrows;

import java.util.*;

public class Hippodrome implements Runnable {
    private static final List<Horse> horses = new ArrayList<> ( );
    private static int horseNumber = 0;
    private static final int distance = 1000;
    private static final Random random = new Random ( );
    private static final Object lock = new Object ( );
    Horse horse = new Horse ( distance , horseNumber );

    @Override
    public void run () {
        increaseHorseNumber ( );
        horse.setHorseNumber ( horseNumber );
        while ( horse.getDistance ( ) > 0 ) {
            start ( );
        }
        if (horse.getDistance ( ) < 0) {
            System.out.println ( horse.getHorseNumber ( ) + " finished" );
            horses.add ( horse );
        }
    }

    public static void getSelectedHorsePositionFromHorseList ( int number , int horseQuantity ) {
        while ( horses.size ( ) != horseQuantity ) {
            Thread.onSpinWait ( );
        }
        for (int i = 0; i < horses.size ( ); i++) {
            if (horses.get ( i ).getHorseNumber ( ) == number) {
                System.out.println ( "Your horse finished in place: " + ( i + 1 ) );
            }
        }
        System.out.println ( horses );
    }

    @SneakyThrows
    public void start () {
        horse.setDistance ( horse.getDistance ( ) - random.nextInt ( 100 , 200 ) );
        Thread.sleep ( random.nextInt ( 400 , 500 ) );
    }

    private void increaseHorseNumber () {
        synchronized (lock) {
            horseNumber++;
        }
    }
}
