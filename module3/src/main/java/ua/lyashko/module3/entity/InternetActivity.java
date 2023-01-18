package ua.lyashko.module3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class InternetActivity extends Activity {
    @Column
    private int spentMB;

    public InternetActivity ( String name , Abonent abonent , int spentMB ) {
        super ( name , abonent );
        this.spentMB = spentMB;
    }

    @Override
    public String toString () {
        return "InternetActivity{" + this.getName ( ) +
                "spentMB=" + spentMB +
                '}';
    }
}
