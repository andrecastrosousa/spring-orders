package mindswap.academy.springorders.order.repository;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.model.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    OrderItem findByOrderIdAndItemId(Long orderId, Long itemId);

    @Modifying
    @Query(value = "ALTER TABLE order_items AUTO_INCREMENT = 1", nativeQuery = true)
    @Transactional
    void reset();
}
