package pos.machine;

public class ReceiptItem {
    private String name;
    private int quantity;
    private int unitPrice;
    private int subTotal;

    public ReceiptItem(String name, int quantity, int unitPrice, int subTotal) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
