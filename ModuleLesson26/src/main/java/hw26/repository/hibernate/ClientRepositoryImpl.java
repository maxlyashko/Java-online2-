package hw26.repository.hibernate;

import hw26.config.HibernateUtils;
import hw26.entity.Client;
import hw26.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl extends AbstractHibernateRepository<Client> implements ClientRepository {
    protected void init () {
        aClass = Client.class;
    }

    private static final EntityManager entityManager = HibernateUtils.getEntityManager ( );

    private static List<Client> clientListConverter ( Query query ) {
        List<Client> clients = new ArrayList<> ( );
        List<Client> list = query.getResultList ( );
        for (Object o : list) {
            Object[] row = (Object[]) o;
            Client client = (Client) row[0];
            clients.add ( client );
        }
        return clients;
    }


    @Override
    public List<Client> getClientsWithAmountOfOrdersGreater ( int amount ) {
        Session session = entityManager.unwrap ( Session.class );
        Transaction transaction = session.beginTransaction ( );
        Query query = entityManager.createQuery ( "SELECT client, COUNT (id)" +
                "from orders GROUP BY client HAVING COUNT(id) >" + amount );
        List<Client> clients = clientListConverter ( query );
        transaction.commit ( );
        return clients;
    }

    @Override
    public int removeAllClientsYoungerThan ( int age ) {
        LocalDate date = LocalDate.of ( LocalDate.now ( ).getYear ( ) - age , LocalDate.now ().getMonth (), LocalDate.now ().getDayOfMonth () );
        Session session = entityManager.unwrap ( Session.class );
        Transaction transaction = session.beginTransaction ();
        Query query = entityManager.createQuery ( "DELETE clients where dateOfBirth < '" + date + "'");
        int result = query.executeUpdate ();
        transaction.commit ();
        return result;
    }

    @Override
    public List<Client> getClientsWithSumOfOrdersGreaterAndAmountOfGoodsInOrderGreater ( int amountOfOrders , int goodsLimit ) {
        Session session = entityManager.unwrap ( Session.class );
        Transaction transaction = session.beginTransaction ( );
        Query query = entityManager.createQuery ( "SELECT client, COUNT(id) from orders" +
                " WHERE size(goods) > " + goodsLimit +
                " GROUP BY client HAVING SUM(totalPrice) > " + amountOfOrders);
        List<Client> clients = clientListConverter ( query );
        transaction.commit ( );
        return clients;
    }
}
