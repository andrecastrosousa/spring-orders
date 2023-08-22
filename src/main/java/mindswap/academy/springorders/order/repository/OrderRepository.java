package mindswap.academy.springorders.order.repository;

import mindswap.academy.springorders.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
