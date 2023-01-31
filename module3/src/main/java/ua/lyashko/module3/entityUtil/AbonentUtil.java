package ua.lyashko.module3.entityUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lyashko.module3.config.HibernateUtils;
import ua.lyashko.module3.entity.Abonent;
import ua.lyashko.module3.entity.Activity;
import ua.lyashko.module3.entity.Devices;
import ua.lyashko.module3.entity.Tariff;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AbonentUtil {
    private static final Random random = new Random ( );
    private static final Session session = HibernateUtils.getSessionFactory ( ).openSession ( );

    public static Abonent createAbonent () {
        Transaction transaction = session.beginTransaction ( );
        Abonent abonent = new Abonent ( );
        Tariff tariff = session.get ( Tariff.class , random.nextInt ( 1 , 6 ) );
        abonent.setFirstName ( createAbonentName ( ) );
        abonent.setLastname ( createAbonentLastName ( ) );
        abonent.setActivityList ( createAbonentActivityList ( abonent ) );
        abonent.setTariff ( tariff );
        abonent.setDevices ( Devices.values ( )[random.nextInt ( Devices.values ( ).length )] );
        session.save ( abonent );
        transaction.commit ( );
        return abonent;
    }

    public static List<Activity> createAbonentActivityList ( Abonent abonent ) {
        List<Activity> activityList = new ArrayList<> ( );

        for (int i = 0; i < random.nextInt ( 1 , 6 ); i++) {
            for (int j = 1; j < random.nextInt ( 1 , 5 ); j++) {
                if (j == 1) {
                    activityList.add ( ActivityUtil.createRingActivity ( abonent ) );
                } else if (j == 2) {
                    activityList.add ( ActivityUtil.createSmsActivity ( abonent ) );
                } else if (j == 3) {
                    activityList.add ( ActivityUtil.createInternetActivity ( abonent ) );
                }
            }
        }
        return activityList;
    }

    public static String createAbonentName () {
        String[] names = {"Jake" , "Jared" , "Joseph" , "Jane" ,
                "Dan" , "Drake" , "Dean" , "Darren" ,
                "Abdullah" , "Rashad" , "Ramesh" , "Ahmed"};
        return names[random.nextInt ( names.length )];
    }

    public static String createAbonentLastName () {
        String[] lastNames = {"Fluffy" , "Framework" , "Footpads" , "Faultless" ,
                "Down" , "Discrimination" , "Drum" , "Determined" ,
                "Explosion" , "Bang" , "Boom" , "Blast"};
        return lastNames[random.nextInt ( lastNames.length )];
    }
}
