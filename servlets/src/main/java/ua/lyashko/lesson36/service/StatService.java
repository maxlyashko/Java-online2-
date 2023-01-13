package ua.lyashko.lesson36.service;

public class StatService {
    private static StatService instance;
    private int counter = 0;

    private StatService () {
    }

    public synchronized static StatService getInstance () {
        if (instance == null) {
            instance = new StatService ( );
        }
        return instance;
    }

    public synchronized void increaseCounter() {
        counter++;
    }

    public int getCounter () {
        return counter;
    }
}
