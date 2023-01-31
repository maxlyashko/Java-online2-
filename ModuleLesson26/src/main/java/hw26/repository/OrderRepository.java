package hw26.repository;

import hw26.entity.Order;

import java.util.List;


public interface OrderRepository extends GenericRepository<Order> {
    List<Order> getOrdersWithDiscount ();
}
