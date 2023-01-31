package ua.lyashko.lesson31.task2;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class MyThread extends Thread {
    Counter counter;

    public MyThread ( Counter counter ) {
        this.counter = counter;
    }

    @Override
    public void run () {
        for (Integer integer : counter.list) {
            if (counter.isPrime ( integer )) {
                counter.increaseCounter ( );
            }
        }
    }
}
