package ua.lyashko.lesson24.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Factory")
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factoryID")
    private int factoryID;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @OneToMany (mappedBy = "factory")
    @ToString.Exclude
    private List<Device> deviceList = new ArrayList<> (  );

    public Factory ( String name , String country ) {
        this.name = name;
        this.country = country;
    }
}
