package pos.machine;

public class ReceiptItem {
    private Item item;
    private int quantity;
    private int subtotal;

    public ReceiptItem(Item item,int quantity,int subtotal) {
        this.item = item;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

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

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}