package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SmsActivity extends Activity {

    @Column
    private String number;
    @Column
    private String message;

    public SmsActivity ( String name , Abonent abonent , String number , String message ) {
        super ( name , abonent );
        this.number = number;
        this.message = message;
    }

    @Override
    public String toString () {
        return "SmsActivity{" + this.getName ( ) +
                " number='" + number + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
