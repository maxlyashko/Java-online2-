package hw26;

import hw26.entity.Client;
import hw26.entity.Goods;
import hw26.entity.Order;
import hw26.repository.ClientRepository;
import hw26.repository.GoodsRepository;
import hw26.repository.OrderRepository;
import hw26.repository.hibernate.ClientRepositoryImpl;
import hw26.repository.hibernate.GoodsRepositoryImpl;
import hw26.repository.hibernate.OrderRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class HibernateRunner {
    private static final Random random = new Random ( );
    private static final ClientRepository clientRepository = new ClientRepositoryImpl ( );
    private static final GoodsRepository goodsRepository = new GoodsRepositoryImpl ( );
    private static final OrderRepository orderRepository = new OrderRepositoryImpl ( );

    public static void main ( String[] args ) {
        Client client = createAndSaveClient ( );
        clientRepository.getAll ( ).forEach ( System.out::println );
        Goods goods = createAndSaveGoods ( );
        goodsRepository.getAll ( ).forEach ( System.out::println );
        Order order = createAndSaveOrder ( client , goods );
        orderRepository.getAll ( ).forEach ( System.out::println );
        System.out.println ( "\nOrders with discount: " + orderRepository.getOrdersWithDiscount ( ) );
        System.out.println ( "\nClients with amount of orders greater: " + clientRepository.getClientsWithAmountOfOrdersGreater ( 1 ) );
        System.out.println ( "\nClients with age more then limit: " + clientRepository.removeAllClientsYoungerThan ( 5 ) );
        System.out.println ( "\nClients with orderSum and goodsInOrder more then limit: "
                + clientRepository.getClientsWithSumOfOrdersGreaterAndAmountOfGoodsInOrderGreater ( 900 , 0 ) );
    }

    private static Client createAndSaveClient () {
        Client client = new Client ( "Tester" , "Testerov" , LocalDate.of ( 2022 ,
                random.nextInt ( 1 , 12 ) , random.nextInt ( 1 , 30 ) ) );
        Client client1 = new Client ( "Tester1" , "Testerov1" , LocalDate.of ( 2022 ,
                random.nextInt ( 1 , 12 ) , random.nextInt ( 1 , 30 ) ) );
        clientRepository.save ( client );
        clientRepository.save ( client1 );
        List<Client> clientList = clientRepository.getAll ( );
        client = clientList.get ( clientList.size ( ) - 1 );
        return client;
    }

    private static Goods createAndSaveGoods () {
        Goods goods = new Goods ( BigDecimal.valueOf ( 2000 ) , "Test goods" , "Test category" );
        Goods goods1 = new Goods ( BigDecimal.valueOf ( 2000 ) , "Test goods" , "Test category" );
        goodsRepository.save ( goods );
        goodsRepository.save ( goods1 );
        List<Goods> goodsList = goodsRepository.getAll ( );
        return goodsList.get ( goodsList.size ( ) - 1 );
    }

    private static Order createAndSaveOrder ( Client client , Goods goods ) {
        Order order = new Order ( LocalDateTime.now ( ) , goods.getPrice ( ) , random.nextInt ( 15 ) );
        Order order1 = new Order ( LocalDateTime.now ( ) , goods.getPrice ( ) , random.nextInt ( 15 ) );

        order.setGoods ( List.of ( goods ) );
        order.setClient ( client );

        order1.setGoods ( List.of ( goods ) );
        order1.setClient ( client );

        orderRepository.save ( order1 );
        orderRepository.save ( order );
        List<Order> orderList = orderRepository.getAll ( );
        return orderList.get ( orderList.size ( ) - 1 );
    }
}
