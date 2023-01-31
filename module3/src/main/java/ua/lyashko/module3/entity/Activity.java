package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "activity")
public abstract class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private int id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "abonent_id")
    private Abonent abonent;

    public Activity ( String name , Abonent abonent ) {
        this.name = name;
        this.abonent = abonent;
    }
}
