package mindswap.academy.springorders.item.converter;


import com.fasterxml.jackson.databind.ObjectMapper;

import mindswap.academy.springorders.item.dto.ItemCreateDto;
import mindswap.academy.springorders.item.dto.ItemDto;
import mindswap.academy.springorders.item.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {

    @Autowired
    ObjectMapper objectMapper;

    public Item fromDto(ItemDto createDto) {
        return objectMapper.convertValue(createDto, Item.class);
    }

    public ItemDto toDto(Item item) {
        return objectMapper.convertValue(item, ItemDto.class);
    }

    public Item toEntityFromCreateDto(ItemCreateDto itemCreateDto) {
        return objectMapper.convertValue(itemCreateDto, Item.class);
    }
}
