package hw26.repository.hibernate;

import hw26.config.HibernateUtils;
import hw26.entity.Order;
import hw26.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl extends AbstractHibernateRepository<Order> implements OrderRepository {
    protected void init () {
        aClass = Order.class;
    }

    private static final EntityManager entityManager = HibernateUtils.getEntityManager ( );

    @Override
    public List<Order> getOrdersWithDiscount () {
        Session session = entityManager.unwrap ( Session.class );
        Transaction transaction = session.beginTransaction ( );
        Query query = entityManager.createQuery ( " FROM orders where discount > 0" );
        List<Order> orders = new ArrayList<> ( );
        List<Order> list = query.getResultList ( );
        for (Order o : list) {
            Order order = new Order ( );
            order.setId ( o.getId ( ) );
            order.setDate ( o.getDate ( ) );
            order.setDiscount ( o.getDiscount ( ) );
            order.setTotalPrice ( o.getTotalPrice ( ) );
            order.setClient ( o.getClient ( ) );
            orders.add ( order );
        }
        transaction.commit ( );
        return orders;
    }
}
