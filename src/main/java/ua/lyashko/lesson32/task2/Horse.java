package ua.lyashko.lesson32.task2;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Horse {
    private int distance;
    private int horseNumber;

    @Override
    public String toString () {
        return "Horse{" +
                "horseNumber=" + horseNumber +
                '}';
    }
}
