package ua.lyashko.module4.model;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Race {
    int id;
    Date date;
    int quantity;
    List<Horse> horseList;
    int number;
}
