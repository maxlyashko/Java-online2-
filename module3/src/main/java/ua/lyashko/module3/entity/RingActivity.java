package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RingActivity extends Activity {

    @Column
    private String number;

    public RingActivity ( String name , Abonent abonent , String number ) {
        super ( name , abonent );
        this.number = number;
    }

    @Override
    public String toString () {
        return "RingActivity{" + this.getName ( ) +
                " number='" + number + '\'' +
                '}';
    }
}
