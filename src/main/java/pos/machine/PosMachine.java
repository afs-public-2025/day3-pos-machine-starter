package pos.machine;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {

    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
      List<String> barcodes = new ArrayList<>(
        Arrays.asList("ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001",
          "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004"));
      String result = posMachine.printReceipt(barcodes);
      System.out.println(result);
    }

    public String printReceipt(List<String> barcodes) {
        return buildReceipt(buildComplexItems(countBarcode(barcodes)));
    }

    private Map<String,Long> countBarcode(List<String> barcodes) {
        return barcodes.stream().collect(Collectors.groupingBy(b -> b, Collectors.counting()));
    }

    private String buildReceipt(List<ComplexItem> complexItems) {
        StringBuilder receipt = new StringBuilder("***<store earning no money>Receipt ***\n");
        int total = 0;
        for (ComplexItem complexItem : complexItems) {
            Item item = complexItem.getItem();
            int quantity = complexItem.getQuantity();
            int subTotal = calculateTotal(quantity, item.getPrice());
            total += subTotal;
            receipt.append(String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
              item.getName(), quantity, item.getPrice(), subTotal));
        }
        receipt.append("----------------------\n");
        receipt.append(String.format("Total: %d (yuan)\n", total));
        receipt.append("**********************");
        return receipt.toString();
    }

    private int calculateTotal(int price,int quantity) {
        return price * quantity;
    }

    private List<ComplexItem> buildComplexItems(Map<String, Long> unitBarcode) {
        return unitBarcode.entrySet().stream()
          .map(entry -> {
              Item item = findItemByBarcode(entry.getKey());
              return new ComplexItem(item, entry.getValue().intValue());
          }).collect(Collectors.toList());
    }

    private Item findItemByBarcode(String barcode) {
        List<Item> items = ItemsLoader.loadAllItems();
        return items.stream().filter(item -> item.getBarcode().equals(barcode)).findFirst().orElse(null);
    }
}

