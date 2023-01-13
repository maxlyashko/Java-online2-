package ua.lyashko.lesson24.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FactoryStats {
    long count;
    long price;
    int factoryID;
}
