package mindswap.academy.springorders.item.controller;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.item.converter.ItemConverter;
import mindswap.academy.springorders.item.dto.ItemCreateDto;
import mindswap.academy.springorders.item.dto.ItemDto;
import mindswap.academy.springorders.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/items")
public class ItemController {
    ItemService itemService;

    @Autowired
    ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDto> list() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ItemDto get(@PathVariable("id") Long itemId) {
        return itemService.findById(itemId);
    }

    @PostMapping
    @Transactional
    public ItemDto post(@RequestBody ItemCreateDto itemCreateDto) {
        return itemService.create(itemCreateDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ItemDto put(@PathVariable("id") Long itemId, @RequestBody ItemDto itemDto) {
        return itemService.update(itemId, itemDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long itemId) {
        itemService.delete(itemId);
    }
}
