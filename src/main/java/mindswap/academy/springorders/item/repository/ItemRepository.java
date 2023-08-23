package mindswap.academy.springorders.item.repository;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Modifying
    @Query(value = "ALTER TABLE items AUTO_INCREMENT = 1", nativeQuery = true)
    @Transactional
    void reset();
}
