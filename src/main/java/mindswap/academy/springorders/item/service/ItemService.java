package mindswap.academy.springorders.item.service;

import mindswap.academy.springorders.item.dto.ItemCreateDto;
import mindswap.academy.springorders.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemCreateDto item);

    List<ItemDto> getAll();

    ItemDto findById(Long id);

    ItemDto update(Long id, ItemDto itemDto);

    void delete(Long id);
}
