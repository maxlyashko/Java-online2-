package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "abonents")
public class Abonent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "abonent_id")
    private int abonentId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastname;

    @ManyToOne
    Tariff tariff;

    @OneToMany(mappedBy = "abonent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Activity> activityList;

    @Column(name = "device")
    Devices devices;
}
