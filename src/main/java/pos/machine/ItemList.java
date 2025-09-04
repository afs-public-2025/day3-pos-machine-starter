package pos.machine;

public class ItemList {
    private String barcode;
    private String name;
    private int price;
    private int count;
    private int subtotal;

    public ItemList(String barcode, String name, int price) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.count = 1;
        this.subtotal = price;
    }

    public void addCount() {
        this.count++;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
