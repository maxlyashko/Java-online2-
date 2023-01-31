package ua.lyashko.lesson24;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.lyashko.lesson24.entity.Device;
import ua.lyashko.lesson24.entity.Factory;
import ua.lyashko.lesson24.entity.FactoryStats;
import ua.lyashko.lesson24.enums.DeviceName;
import ua.lyashko.lesson24.enums.DeviceType;
import ua.lyashko.lesson24.enums.FactoryCountry;
import ua.lyashko.lesson24.enums.FactoryName;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TableUtil {
    private static final Session session = HibernateUtil.getSessionFactory ( ).openSession ( );

    private static Date randomDate () {
        Random random = new Random ( );
        int minDay = (int) LocalDate.of ( 2010 , 1 , 1 ).toEpochDay ( );
        int maxDay = (int) LocalDate.of ( 2022 , 1 , 1 ).toEpochDay ( );
        long randomDay = minDay + random.nextInt ( maxDay - minDay );
        return Date.valueOf ( LocalDate.ofEpochDay ( randomDay ) );
    }

    public static void generateFactories () {
        Transaction transaction = session.beginTransaction ( );
        Random random = new Random ( );
        for (int i = 0; i < 4; i++) {
            String tempName = String.valueOf (
                    FactoryName.values ( )[random.nextInt ( FactoryName.values ( ).length )] );
            String tempCountry = String.valueOf (
                    FactoryCountry.values ( )[random.nextInt ( FactoryCountry.values ( ).length )] );
            Factory factory = new Factory ( tempName , tempCountry );
            session.save ( factory );
        }
        transaction.commit ( );
    }

    public static void generateDevices () {
        Transaction transaction = session.beginTransaction ( );
        Random random = new Random ( );
        for (int i = 0; i < 10; i++) {
            String tempType = String.valueOf (
                    DeviceType.values ( )[random.nextInt ( DeviceType.values ( ).length )] );
            String tempName = String.valueOf (
                    DeviceName.values ( )[random.nextInt ( DeviceName.values ( ).length )] );
            int tempPrice = random.nextInt ( 1000 , 5000 );
            Date tempDate = randomDate ( );
            String tempDescription = "good " + tempName + " for best price: " + tempPrice;
            boolean tempBoolean = random.nextBoolean ( );
            int tempFactoryID = random.nextInt ( 1 , 4 );
            Factory factory = session.get ( Factory.class , tempFactoryID );
            Device device = new Device ( tempType , tempName , tempPrice , tempDate ,
                    tempDescription , tempBoolean , tempFactoryID , factory );
            session.save ( device );
        }
        transaction.commit ( );
    }

    public static Factory getFactory ( Device device ) {
        return session.get ( Factory.class , device.getFactoryID ( ) );
    }

    public static Device getDevice ( int deviceID ) {
        return session.get ( Device.class , deviceID );
    }

    public static void changeData ( Device device ) {
        Transaction transaction = session.beginTransaction ( );
        String deviceID = String.valueOf ( device.getDeviceID ( ) );
        Query query = session.createQuery ( "UPDATE Device set type = :typeParam," +
                " name = :nameParam," +
                " price = :priceParam," +
                " manufactureDate = :manufactureParam," +
                " description = :description," +
                " isAvailable = :availableParam," +
                " factoryID = :factoryParam " +
                " where deviceID = :devID" );
        query.setParameter ( "typeParam", device.getType () );
        query.setParameter ( "nameParam", device.getName () );
        query.setParameter ( "priceParam" , "55" );
        query.setParameter ( "manufactureParam", device.getManufactureDate () );
        query.setParameter ( "description" , "aaa" );
        query.setParameter ( "availableParam", device.isAvailable () );
        query.setParameter ( "factoryParam", device.getFactoryID () );
        query.setParameter ( "devID" , device.getDeviceID () );
        query.executeUpdate ( );
        transaction.commit ( );
    }

    public static void dropDevice ( int deviceID ) {
        Transaction transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "delete Device where deviceID = :param" );
        query.setParameter ( "param" , String.valueOf ( deviceID ) );
        query.executeUpdate ( );
        transaction.commit ( );
    }

    public static List<Device> getAllDevicesByFactory ( int factoryID ) {
        Transaction transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "from Device where factoryID = :param" );
        query.setParameter ( "param" , String.valueOf ( factoryID ) );
        List<Device> deviceList = query.getResultList ( );
        transaction.commit ( );
        return deviceList;
    }

    public static List<FactoryStats> getQuantityAndSum () {
        List<FactoryStats> factoryStatsList = new ArrayList<> ( );
        Transaction transaction = session.beginTransaction ( );
        Query query = session.createQuery ( "select COUNT(deviceID), SUM (price), factoryID " +
                "from Device GROUP BY factoryID" );
        var deviceList = query.getResultList ( );
        for (Object o : deviceList) {
            FactoryStats factoryStats = new FactoryStats ( );
            Object[] row = (Object[]) o;
            factoryStats.setCount ( (long) row[0] );
            factoryStats.setPrice ( (long) row[1] );
            factoryStats.setFactoryID ( (int) row[2] );
            factoryStatsList.add ( factoryStats );
        }
        transaction.commit ( );
        return factoryStatsList;
    }
}
