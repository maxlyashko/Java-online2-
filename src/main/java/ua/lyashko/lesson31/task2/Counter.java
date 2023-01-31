package ua.lyashko.lesson31.task2;

import lombok.Data;

import java.util.List;

@Data
public class Counter {
    List<Integer> list;
    int counter = 0;

    public Counter ( List<Integer> list ) {
        this.list = list;
    }
    public  boolean isPrime ( int number ) {
        int temp;
        if (number <= 1) return false;
        for (int i = 2; i <= number / 2; i++) {
            temp = number % i;
            if (temp == 0) return false;
        }
        return true;
    }

    public synchronized void increaseCounter () {
        counter++;
    }
}
