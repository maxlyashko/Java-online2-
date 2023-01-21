package ua.lyashko.module4.service;

import lombok.SneakyThrows;
import ua.lyashko.module4.model.Horse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HippodromeService implements Runnable {
    private static final HorseService horseService = new HorseService ();
    private static HippodromeService instance;
    private static List<Horse> horses = new ArrayList<> ( );
    private static int horseNumber = 0;
    private static final int distance = 1000;
    private static final Random random = new Random ( );
    private static final Object lock = new Object ( );
    Horse horse = new Horse ( distance , horseNumber );

    public synchronized static HippodromeService getInstance () {
        if (instance == null) {
            instance = new HippodromeService ( );
        }
        return instance;
    }

    @Override
    public void run () {
        increaseHorseNumber ( );
        horse.setDistance ( distance );
        horse.setHorseNumber ( horseNumber );
        while ( horse.getDistance ( ) > 0 ) {
            start ( );
        }
        if (horse.getDistance ( ) < 0) {
            System.out.println ( horse.getHorseNumber ( ) + "finished" );
            horses.add ( horse );
        }
    }

    public void saveHorses ( int horseQuantity ) {
        while ( horses.size ( ) != horseQuantity ) {
            Thread.onSpinWait ( );
        }
        horseService.saveHorses ( horses );
    }

    @SneakyThrows
    public void start () {
        horse.setDistance ( horse.getDistance ( ) - random.nextInt ( 100 , 200 ) );
        Thread.sleep ( random.nextInt ( 400 , 500 ) );
    }

    public void flush () {
        horseNumber = 0;
        horses = new ArrayList<> (  );
    }


    private void increaseHorseNumber () {
        synchronized (lock) {
            horseNumber++;
        }
    }
}
