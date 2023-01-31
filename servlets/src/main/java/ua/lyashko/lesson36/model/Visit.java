package ua.lyashko.lesson36.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visit {
    private String name;
    private Date date;
    private String city;
}
