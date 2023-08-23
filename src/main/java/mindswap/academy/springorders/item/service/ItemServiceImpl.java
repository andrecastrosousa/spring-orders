package mindswap.academy.springorders.item.service;

import mindswap.academy.springorders.item.converter.ItemConverter;
import mindswap.academy.springorders.item.dto.ItemCreateDto;
import mindswap.academy.springorders.item.dto.ItemDto;
import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    ItemConverter itemConverter;

    @Autowired
    ItemServiceImpl(ItemRepository itemRepository, ItemConverter itemConverter) {
        this.itemRepository = itemRepository;
        this.itemConverter = itemConverter;
    }

    @Override
    public ItemDto create(ItemCreateDto itemCreateDto) {
        Item item = itemConverter.toEntityFromCreateDto(itemCreateDto);
        System.out.println(item);
        itemRepository.save(item);
        return itemConverter.toDto(item);
    }

    @Override
    public List<ItemDto> getAll() {
        List<ItemDto> itemDtos = new ArrayList<>();
        itemRepository.findAll().forEach(item -> itemDtos.add(itemConverter.toDto(item)));
        return itemDtos;
    }

    @Override
    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        return itemConverter.toDto(item);
    }

    @Override
    public ItemDto update(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());

        return itemConverter.toDto(item);
    }

    @Override
    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        itemRepository.delete(item);
    }
}
