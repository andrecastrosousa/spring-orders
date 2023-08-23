package mindswap.academy.springorders.order.repository;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByOrderIdAndItemId(Long orderId, Long itemId);

    @Modifying
    @Query(value = "ALTER TABLE order_items AUTO_INCREMENT = 1", nativeQuery = true)
    @Transactional
    void reset();
}
