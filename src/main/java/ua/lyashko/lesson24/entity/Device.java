package ua.lyashko.lesson24.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private int deviceID;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacture")
    private Date manufactureDate;

    @Column(name = "description")
    private String description;

    @Column(name = "isAvailable")
    private boolean isAvailable;

    @Column(name = "factoryID")
    private int factoryID;

    @ManyToOne
    private Factory factory;

    public Device ( String type , String name , int price ,
                    Date manufactureDate , String description , boolean isAvailable ,
                    int factoryID , Factory factory ) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.description = description;
        this.isAvailable = isAvailable;
        this.factoryID = factoryID;
        this.factory = factory;
    }
}
