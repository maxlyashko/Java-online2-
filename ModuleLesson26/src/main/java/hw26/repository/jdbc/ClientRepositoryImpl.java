package hw26.repository.jdbc;

import hw26.entity.Client;
import hw26.entity.Order;
import hw26.repository.ClientRepository;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl extends AbstractJDBCRepository implements ClientRepository {
    private final String CREATE_USER_SQL = "INSERT INTO clients (client_id, first_name, last_name, date_of_birth) VALUES (?,?,?,?)";
    private final String SELECT_ALL_CLIENTS_SQL = "SELECT * FROM clients";
    private final String SELECT_CLIENT_BY_ID_SQL = "SELECT * FROM clients WHERE client_id = ?";

    @SneakyThrows
    @Override
    public Client getById ( String id ) {
        try (Connection connection = createConnection ( );
             PreparedStatement statement = connection.prepareStatement ( SELECT_CLIENT_BY_ID_SQL )) {
            statement.setString ( 1 , id );
            try (ResultSet resultSet = statement.executeQuery ( )) {
                if (resultSet.next ( )) {
                    return extractClientFromResultSet ( resultSet );
                }
                return null;
            }
        }
    }

    @SneakyThrows
    @Override
    public List<Client> getAll () {
        List<Client> clients = new ArrayList<> ( );
        try (Connection connection = createConnection ( );
             Statement statement = connection.createStatement ( );
             ResultSet resultSet = statement.executeQuery ( SELECT_ALL_CLIENTS_SQL )) {
            while ( resultSet.next ( ) ) {
                clients.add ( extractClientFromResultSet ( resultSet ) );
            }
            return clients;
        }
    }

    @SneakyThrows
    @Override
    public void save ( Client client ) {
        try (Connection connection = createConnection ( );
             PreparedStatement statement = connection.prepareStatement ( CREATE_USER_SQL )) {
            statement.setString ( 1 , client.getId ( ) );
            statement.setString ( 2 , client.getFirstName ( ) );
            statement.setString ( 3 , client.getLastName ( ) );
            statement.setDate ( 4 , Date.valueOf ( client.getDateOfBirth ( ) ) );
            statement.execute ( );
        }
    }

    private Client extractClientFromResultSet ( ResultSet resultSet ) throws SQLException {
        Client client = new Client ( );
        client.setId ( resultSet.getString ( "client_id" ) );
        client.setFirstName ( resultSet.getString ( "first_name" ) );
        client.setLastName ( resultSet.getString ( "last_name" ) );
        client.setDateOfBirth ( resultSet.getDate ( "date_of_birth" ).toLocalDate ( ) );
        return client;
    }

    @SneakyThrows
    @Override
    public List<Client> getClientsWithAmountOfOrdersGreater ( int amount ) {
        String temp = "SELECT client_id FROM orders GROUP BY client_id HAVING COUNT (order_id) > " + amount;
        List<Client> clients = new ArrayList<> ( );

        try (Connection connection = createConnection ( );
             Statement statement = connection.createStatement ( );
             ResultSet resultSet = statement.executeQuery ( temp )) {
            while ( resultSet.next ( ) ) {
                Client client = getById ( resultSet.getString ( "client_id" ) );
                clients.add ( client );
            }
            return clients;
        }
    }

    @SneakyThrows
    @Override
    public int removeAllClientsYoungerThan ( int age ) {
        LocalDate date = LocalDate.of ( LocalDate.now ( ).getYear ( ) - age , LocalDate.now ().getMonth (), LocalDate.now ().getDayOfMonth () );
        String temp = "DELETE FROM clients WHERE date_of_birth < " + date;
        Connection connection = createConnection ( );
        Statement statement = connection.prepareStatement ( temp );
        return statement.executeUpdate ( temp );
    }

    @SneakyThrows
    @Override
    public List<Client> getClientsWithSumOfOrdersGreaterAndAmountOfGoodsInOrderGreater ( int amountOfOrders , int goodsLimit ) {
        String temp = "SELECT client_id from orders " +
                "WHERE order_id IN (SELECT order_id from orders_goods GROUP BY order_id HAVING COUNT(goods_id) >" + goodsLimit + " ) " +
                "GROUP BY client_id " +
                "HAVING SUM(total_price) > " + amountOfOrders;

        List<Client> clients = new ArrayList<> ( );
        try (Connection connection = createConnection ( );
             Statement statement = connection.createStatement ( );
             ResultSet resultSet = statement.executeQuery ( temp )) {
            while ( resultSet.next ( ) ) {
                Client client = getById ( resultSet.getString ( "client_id" ) );
                clients.add ( client );
            }
            return clients;
        }
    }
}