package ua.lyashko.module3.systemUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.lyashko.module3.config.HibernateUtils;
import ua.lyashko.module3.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SystemUtil {
    private static final Session session = HibernateUtils.getSessionFactory ( ).openSession ( );
    private static Transaction transaction;

    public static List<ActivityStats> mostCallsConsumers () {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT abonent, name, COUNT(name)" +
                " FROM Activity WHERE name = 'Calling'" +
                " GROUP BY abonent ORDER BY COUNT(name) DESC LIMIT 5 " );
        List<ActivityStats> abonents = resultConverter ( query );
        transaction.commit ( );
        return abonents;
    }

    public static List<ActivityStats> mostSmsConsumers () {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT abonent, name, COUNT(name)" +
                " FROM Activity WHERE name = 'Sent sms'" +
                " GROUP BY abonent ORDER BY COUNT(name) DESC LIMIT 5 " );
        List<ActivityStats> abonents = resultConverter ( query );
        transaction.commit ( );
        return abonents;
    }

    public static List<ActivityStats> mostInternetConsumers () {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT abonent, name, COUNT(name)" +
                " FROM Activity WHERE name = 'Searching in the Internet'" +
                " GROUP BY abonent ORDER BY COUNT(name) DESC LIMIT 5 " );
        List<ActivityStats> abonents = resultConverter ( query );
        transaction.commit ( );
        return abonents;
    }

    private static List<ActivityStats> resultConverter ( Query query ) {
        List<ActivityStats> abonents = new ArrayList<> ( );
        List<ActivityStats> list = query.getResultList ( );
        for (Object o : list) {
            ActivityStats activityStats = new ActivityStats ( );
            Object[] row = (Object[]) o;
            activityStats.setAbonent ( (Abonent) row[0] );
            activityStats.setName ( (String) row[1] );
            activityStats.setCount ( (long) row[2] );
            abonents.add ( activityStats );
        }
        return abonents;
    }

    public static ActivityStats showTheMostPopularService () {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT name, COUNT(name)" +
                " FROM Activity GROUP BY name ORDER BY COUNT(name) DESC " );
        List<ActivityStats> abonents = new ArrayList<> ( );
        List<ActivityStats> list = query.getResultList ( );
        for (Object o : list) {
            ActivityStats activityStats = new ActivityStats ( );
            Object[] row = (Object[]) o;
            activityStats.setName ( (String) row[0] );
            activityStats.setCount ( (long) row[1] );
            abonents.add ( activityStats );
        }
        transaction.commit ( );
        return abonents.get ( 0 );
    }

    public static DeviceStats showTheMostPopularDevice () {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT devices, COUNT(abonentId)" +
                "FROM Abonent GROUP BY devices ORDER BY COUNT(abonentId) DESC" );
        List<DeviceStats> deviceList = new ArrayList<> ( );
        List<DeviceStats> list = query.getResultList ( );
        for (Object o : list) {
            DeviceStats deviceStats = new DeviceStats ( );
            Object[] row = (Object[]) o;
            deviceStats.setDevices ( (Devices) row[0] );
            deviceStats.setAbonents ( (long) row[1] );
            deviceList.add ( deviceStats );
        }
        transaction.commit ( );
        return deviceList.get ( 0 );
    }

    public static List<ActivityStats> smsSearch ( String text ) {
        transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "SELECT abonent, message" +
                " FROM Activity WHERE message LIKE '%" + text + "%' " );
        List<ActivityStats> abonents = new ArrayList<> ( );
        List<ActivityStats> list = query.getResultList ( );
        for (Object o : list) {
            ActivityStats activityStats = new ActivityStats ( );
            Object[] row = (Object[]) o;
            activityStats.setAbonent ( (Abonent) row[0] );
            activityStats.setName ( (String) row[1] );
            abonents.add ( activityStats );
        }
        transaction.commit ( );
        return abonents;
    }
}
