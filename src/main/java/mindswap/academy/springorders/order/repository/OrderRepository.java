package mindswap.academy.springorders.order.repository;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Modifying
    @Query(value = "ALTER TABLE orders AUTO_INCREMENT = 1", nativeQuery = true)
    @Transactional
    void reset();
}
