package ua.lyashko.module3.entityUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lyashko.module3.config.HibernateUtils;
import ua.lyashko.module3.entity.Tariff;

import java.util.Arrays;
import java.util.Random;

public class TariffUtil {
    private static final Random random = new Random ( );
    private static final Session session = HibernateUtils.getSessionFactory ( ).openSession ( );

    public static Tariff createTariff () {
        Transaction transaction = session.beginTransaction ( );
        Tariff tariff = new Tariff ( generateTariffName ( ) , new Random ( ).nextInt ( 100 , 1000 ) );
        session.save ( tariff );
        transaction.commit ( );
        return tariff;
    }

    private static String generateTariffName () {
        String[] adjective = {"Best" , "Fastest" , "Cheapest" , "Easiest" , "Convenient"};
        String[] action = {"call" , "use" , "search" , "surf" , "send"};


        String[] message = {adjective[random.nextInt ( adjective.length )] ,
                action[random.nextInt ( action.length )] + " service"};
        return Arrays.toString ( message )
                .replace ( "[" , "" )
                .replace ( "," , "" )
                .replace ( "]" , "" );
    }
}
