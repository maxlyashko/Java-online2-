package hw26.repository.jdbc;

import hw26.entity.Goods;
import hw26.repository.GoodsRepository;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsRepositoryImpl extends AbstractJDBCRepository implements GoodsRepository {
    private final String createGoodsSQL = "INSERT INTO goods (goods_id, price, title, category) VALUES (?,?,?,?)";
    private final String selectAllGoodsSQL = "SELECT * FROM goods";
    private final String selectGoodsByIdSQL = "SELECT * FROM goods WHERE goods_id = ?";

    @SneakyThrows
    @Override
    public Goods getById ( String id ) {
        try (Connection connection = createConnection ( );
             PreparedStatement statement = connection.prepareStatement ( selectGoodsByIdSQL )) {
            statement.setString ( 1 , id );
            try (ResultSet resultSet = statement.executeQuery ( )) {
                if (resultSet.next ( )) {
                    return extractGoodsFromResultSet ( resultSet );
                }
                return null;
            }
        }
    }

    @SneakyThrows
    @Override
    public List<Goods> getAll () {
        List<Goods> goods = new ArrayList<> ( );
        try (Connection connection = createConnection ( );
             Statement statement = connection.createStatement ( );
             ResultSet resultSet = statement.executeQuery ( selectAllGoodsSQL )) {
            while ( resultSet.next ( ) ) {
                goods.add ( extractGoodsFromResultSet ( resultSet ) );
            }
            return goods;
        }
    }

    @SneakyThrows
    @Override
    public void save ( Goods goods ) {
        try (Connection connection = createConnection ( );
             PreparedStatement statement = connection.prepareStatement ( createGoodsSQL )) {
            statement.setString ( 1 , goods.getId ( ) );
            statement.setBigDecimal ( 2 , goods.getPrice ( ) );
            statement.setString ( 3 , goods.getTitle ( ) );
            statement.setString ( 4 , goods.getCategory ( ) );
            statement.execute ( );
        }
    }

    private Goods extractGoodsFromResultSet ( ResultSet resultSet ) throws SQLException {
        Goods goods = new Goods ( );
        goods.setId ( resultSet.getString ( "goods_id" ) );
        goods.setPrice ( resultSet.getBigDecimal ( "price" ) );
        goods.setTitle ( resultSet.getString ( "title" ) );
        goods.setCategory ( resultSet.getString ( "category" ) );
        return goods;
    }
}