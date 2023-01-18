package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "tariff")
public class Tariff {
    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tariffId;

    @Column
    private String name;

    @Column
    private int price;

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Abonent> abonents = new ArrayList<> ( );

    public Tariff ( String name , int price ) {
        this.name = name;
        this.price = price;
    }
}
