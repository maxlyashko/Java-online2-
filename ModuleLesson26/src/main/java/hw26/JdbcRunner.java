package hw26;

import hw26.entity.Client;
import hw26.entity.Goods;
import hw26.entity.Order;
import hw26.repository.ClientRepository;
import hw26.repository.GoodsRepository;
import hw26.repository.OrderRepository;
import hw26.repository.jdbc.ClientRepositoryImpl;
import hw26.repository.jdbc.GoodsRepositoryImpl;
import hw26.repository.jdbc.OrderRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class JdbcRunner {
    private static final ClientRepository clientRepository = new ClientRepositoryImpl ( );
    private static final GoodsRepository goodsRepository = new GoodsRepositoryImpl ( );
    private static final OrderRepository orderRepository = new OrderRepositoryImpl ( goodsRepository , clientRepository );

    public static void main ( String[] args ) {
        Client client = createAndSaveClient ( );
        clientRepository.getAll ( ).forEach ( System.out::println );
        Goods goods = createAndSaveGoods ( );
        goodsRepository.getAll ( ).forEach ( System.out::println );
        Order order = createAndSaveOrder ( client , goods );
        orderRepository.getAll ( ).forEach ( System.out::println );

        System.out.println ( orderRepository.getOrdersWithDiscount ( ) );
        System.out.println ( clientRepository.getClientsWithAmountOfOrdersGreater ( 1 ) );
        System.out.println ( clientRepository.removeAllClientsYoungerThan ( 7 ) );
        System.out.println ( clientRepository.getClientsWithSumOfOrdersGreaterAndAmountOfGoodsInOrderGreater ( 900 , 0 ) );
    }

    private static Client createAndSaveClient () {
        Client client = new Client ( UUID.randomUUID ( ).toString ( ) , "Tester" , "Testerov" , LocalDate.of ( 2010 , 9 , 21 ) , Collections.emptyList ( ) );
        clientRepository.save ( client );
        List<Client> clientList = clientRepository.getAll ( );
        client = clientList.get ( clientList.size ( ) - 1 );
        return client;
    }

    private static Goods createAndSaveGoods () {
        Goods goods = new Goods ( UUID.randomUUID ( ).toString ( ) , BigDecimal.valueOf ( 2000 ) , "Test goods" , "Test category" , List.of ( ) );
        goodsRepository.save ( goods );
        List<Goods> goodsList = goodsRepository.getAll ( );
        return goodsList.get ( goodsList.size ( ) - 1 );
    }

    private static Order createAndSaveOrder ( Client client , Goods goods ) {
        Random random = new Random ( );
        Order order = new Order ( UUID.randomUUID ( ).toString ( ) , LocalDateTime.now ( ) , goods.getPrice ( ) , random.nextInt ( 15 ) , client , List.of ( goods ) );
        orderRepository.save ( order );
        List<Order> orderList = orderRepository.getAll ( );
        return orderList.get ( orderList.size ( ) - 1 );
    }
}