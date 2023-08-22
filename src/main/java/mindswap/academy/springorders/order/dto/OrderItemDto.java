package mindswap.academy.springorders.order.dto;

import mindswap.academy.springorders.item.model.Item;

public class OrderItemDto {
    private Item item;
    private int quantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
