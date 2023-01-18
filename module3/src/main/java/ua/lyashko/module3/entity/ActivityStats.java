package ua.lyashko.module3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityStats {
    Abonent abonent;
    String name;
    long count;

    @Override
    public String toString () {
        return " \n ActivityStats{" +
                "abonent=" + abonent +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
