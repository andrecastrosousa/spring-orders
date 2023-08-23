package mindswap.academy.springorders.order.repository;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(value = "ALTER TABLE orders AUTO_INCREMENT = 1", nativeQuery = true)
    @Transactional
    void reset();
}
