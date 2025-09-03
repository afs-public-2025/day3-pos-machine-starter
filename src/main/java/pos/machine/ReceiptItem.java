package pos.machine;

public class ReceiptItem {
    private Item item;
    private int quantity = 0;
    ReceiptItem(Item item,int quantity){
        setItem(item);
        setQuantity(quantity);
    }
    public String getName() {
        return item.getName();
    }
    public int getUnitPrice() {
        return item.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }
    public int getSubtotal() {
        return item.getPrice() * quantity;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
