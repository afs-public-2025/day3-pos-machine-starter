package pos.machine;

public class ReceiptItem {
    private final String name;
    private final int quantity;
    private final int unitPrice;
    private final int subtotal;

    public ReceiptItem(String name, int quantity, int unitPrice, int subtotal) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubtotal() {
        return subtotal;
    }
}