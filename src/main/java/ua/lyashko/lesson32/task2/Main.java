package ua.lyashko.lesson32.task2;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main ( String[] args ) {
        int horseQuantity;
        int selectedHorse;
        Scanner scanner = new Scanner ( System.in );

        System.out.println ( "Select horse quantity: " );
        horseQuantity = scanner.nextInt ( );

        System.out.println ( "Choose number of horse to bet" );
        selectedHorse = scanner.nextInt ( );

        ExecutorService executorService = Executors.newFixedThreadPool ( horseQuantity );
        for (int i = 0; i < horseQuantity; i++) {
            executorService.submit ( new Hippodrome ( ) );
        }

        Hippodrome.getSelectedHorsePositionFromHorseList ( selectedHorse , horseQuantity );
        executorService.shutdown ( );
    }
}
