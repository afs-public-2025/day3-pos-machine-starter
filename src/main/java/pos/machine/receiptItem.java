package pos.machine;

public class receiptItem {
    private Item item;
    private int quantity;
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

}
