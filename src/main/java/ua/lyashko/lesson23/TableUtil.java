package ua.lyashko.lesson23;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableUtil {
    private static final Connection connection = createConnection ( );

    public static Connection createConnection () {
        Connection connection = null;
        String user = "root";
        String password = "root";
        String url = "jdbc:mariadb://localhost:3306/lesson23";

        try {
            connection = DriverManager.getConnection ( url , user , password );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return connection;
    }

    public static void generateDevices () {
        try {
            Statement statement = connection.createStatement ( );
            String createDevices = """
                    INSERT INTO Device ( type, name, price, manufactureDate, description, isAvailable, factoryID ) VALUES
                    ('TELEPHONE' , 'IPHONE 12', 1200, '2020-10-08', 'good phone, for sure...', 1, 1),
                    ('TV' , 'miTV 2', 1000, '2020-10-08', 'good tv, for sure...', 0, 3),
                    ('TELEPHONE' , 'Galaxy Note 9', 1300, '2010-12-10', 'good phone, for sure...', 1, 2),
                    ('RADIO' , 'radio', 250, '2020-10-08', 'good radio, for sure...', 1, 4),
                    ('POWERBOAT' , 'vroom-vroom', 7000, '2019-06-12', 'good boat, for sure...', 0, 2),
                    ('BATTERY' , 'ecol', 50, '2020-10-08', 'good battery, for sure...', 1, 4),
                    ('TELEPHONE' , 'RedMi Note 14', 700, '2022-08-04', 'good phone, for sure...', 1, 3),
                    ('FRIDGE' , 'AA 988322', 550, '2021-09-03', 'good fridge, for sure...', 1, 2),
                    ('LAPTOP' , 'MacBook PRO 10', 3000, '2017-11-11', 'good laptop, for sure...', 0, 1),
                    ('TELEPHONE' , 'Galaxy Fold', 820, '2006-01-01', 'good phone, for sure...', 0, 2);
                    """;
            statement.executeUpdate ( createDevices );
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public static void generateFactories () {
        try {
            Statement statement = connection.createStatement ( );
            String createFactories = """
                    INSERT INTO Factory (name, country) VALUES
                    ('APPLE', 'USA'),
                    ('SAMSUNG', 'SOUTH KOREA'),
                    ('XIAOMI', 'CHINA'),
                    ('SONY', 'JAPAN');
                    """;
            statement.executeUpdate ( createFactories );
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public static Factory getFactory ( Device device ) {
        Factory factory = new Factory ( );
        try {
            Statement statement = connection.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String temp = "SELECT * FROM Factory WHERE factoryID = " + device.getFactoryID ( );
            ResultSet resultSet = statement.executeQuery ( temp );
            resultSet.first ( );
            factory.setFactoryID ( Integer.parseInt ( resultSet.getString ( "factoryID" ) ) );
            factory.setCountry ( resultSet.getString ( "country" ) );
            factory.setName ( resultSet.getString ( "name" ) );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return factory;
    }


    public static Device getDevice ( int deviceID ) {
        Device device = new Device ( );
        Factory factory = new Factory ( );
        try {
            Statement statement = connection.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String getInformation = "SELECT * FROM Device WHERE deviceID = " + deviceID;
            ResultSet resultSet = statement.executeQuery ( getInformation );
            while ( resultSet.next ( ) ) {
                device.setDeviceID ( Integer.parseInt ( resultSet.getString ( "deviceID" ) ) );
                device.setType ( resultSet.getString ( "type" ) );
                device.setName ( resultSet.getString ( "name" ) );
                device.setPrice ( Integer.parseInt ( resultSet.getString ( "price" ) ) );
                device.setManufactureDate ( Date.valueOf ( resultSet.getString ( "manufactureDate" ) ) );
                device.setDescription ( resultSet.getString ( "description" ) );
                device.setAvailable ( Boolean.parseBoolean ( resultSet.getString ( "isAvailable" ) ) );
                device.setFactoryID ( Integer.parseInt ( resultSet.getString ( "factoryID" ) ) );

                factory = getFactory ( device );
                statement.close ( );
            }
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        System.out.println ( device );
        System.out.println ( factory );
        return device;
    }

    public static void updateData ( Device device ) {
        String description = "blablabla";
        try {
            String str = "UPDATE Device SET type = ?, name = ?, price = ?, manufactureDate = ?," +
                    "description = ?, isAvailable = ?, factoryID = ? WHERE deviceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement ( str );
            preparedStatement.setString ( 1 , device.getType ( ) );
            preparedStatement.setString ( 2 , device.getName ( ) );
            preparedStatement.setInt ( 3 , device.getPrice ( ) );
            preparedStatement.setDate ( 4 , device.getManufactureDate ( ) );
            preparedStatement.setString ( 5 , description );
            preparedStatement.setBoolean ( 6 , device.getAvailable ( ) );
            preparedStatement.setInt ( 7 , device.getFactoryID ( ) );
            preparedStatement.setInt ( 8 , device.getDeviceID ( ) );
            preparedStatement.executeUpdate ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public static void dropDevice ( int deviceID ) {
        try {
            Statement statement = connection.createStatement ( );
            String dropDevice = "DELETE FROM Device WHERE deviceID = " + deviceID;
            statement.executeUpdate ( dropDevice );
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }

    public static List<Device> getAllDevicesByFactory ( int factoryID ) {
        List<Device> devices = new ArrayList<> ( );
        Device device;
        try {
            Statement statement = connection.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String str = "SELECT * FROM Factory, Device WHERE Factory.factoryID = Device.factoryID AND Factory.factoryID = " + factoryID;
            ResultSet resultSet = statement.executeQuery ( str );
            while ( resultSet.next ( ) ) {
                int deviceId = Integer.parseInt ( resultSet.getString ( "deviceID" ) );
                device = getDevice ( deviceId );
                devices.add ( device );
            }
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
        return devices;
    }

    public static void getQuantityAndSum () {
        try {
            Statement statement = connection.createStatement (
                    ResultSet.TYPE_FORWARD_ONLY ,
                    ResultSet.CONCUR_READ_ONLY );
            String str = "SELECT COUNT(deviceID) , SUM(price), factoryID  FROM Device GROUP BY factoryID";
            ResultSet resultSet = statement.executeQuery ( str );
            while ( resultSet.next ( ) ) {
                int count = Integer.parseInt ( resultSet.getString ( "count(deviceID)" ) );
                int sum = Integer.parseInt ( resultSet.getString ( "sum(price)" ) );
                int factoryID = Integer.parseInt ( resultSet.getString ( "factoryID" ) );
                System.out.println ( count + " " + sum + " " + factoryID );
            }
            statement.close ( );
        } catch ( SQLException e ) {
            e.printStackTrace ( );
        }
    }
}
