package ua.lyashko.module4.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Horse {
    private int distance;
    private int horseNumber;
    private byte place;

    public Horse ( int distance , int horseNumber ) {
        this.distance = distance;
        this.horseNumber = horseNumber;
    }

    public Horse ( int horseNumber, byte place ) {
        this.horseNumber = horseNumber;
        this.place = place;
    }

    @Override
    public String toString () {
        return "Horse{" +
                "horseNumber=" + horseNumber +
                ", place=" + place +
                '}';
    }
}
