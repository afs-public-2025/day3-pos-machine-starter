package pos.machine;

public class ComplexItem {
  private Item item;
  private int quantity;

  public ComplexItem(Item item, int quantity) {
    this.item = item;
    this.quantity = quantity;
  }

  public Item getItem() {
    return item;
  }

  public int getQuantity() {
    return quantity;
  }
}
