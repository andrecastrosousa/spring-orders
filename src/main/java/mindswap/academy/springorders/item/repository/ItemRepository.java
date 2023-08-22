package mindswap.academy.springorders.item.repository;

import mindswap.academy.springorders.item.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
