package hw26.repository;

import hw26.entity.Client;

import java.util.List;

public interface ClientRepository extends GenericRepository<Client> {
    List<Client> getClientsWithAmountOfOrdersGreater ( int amount );

    int removeAllClientsYoungerThan ( int age );

    List<Client> getClientsWithSumOfOrdersGreaterAndAmountOfGoodsInOrderGreater ( int amountOfOrders , int goodsLimit );
}
