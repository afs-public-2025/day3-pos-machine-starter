package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public static void main(String[] args) {
        List<String> barcodes=List.of("ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000001",
                "ITEM000001",
                "ITEM000004",
                "ITEM000004",
                "ITEM000004");
        System.out.println(printReceipt(barcodes));

    }
    public static String printReceipt(List<String> barcodes) {
        Map<String, Item> itemsMap = loadItems();
        Map<String, Integer> itemCount = countItems(barcodes);
        return createReceipt(itemsMap, itemCount);
    }

    private static String createReceipt(Map<String, Item> itemsMap, Map<String, Integer> itemCount) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("***<store earning no money>Receipt***\n");

        int total = calculateTotal(itemsMap, itemCount);

        for (String barcode : itemCount.keySet()) {
            Item item = itemsMap.get(barcode);
            if (item != null) {
                int quantity = itemCount.get(barcode);
                int subtotal = item.getPrice() * quantity;
                receipt.append(String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                        item.getName(), quantity, item.getPrice(), subtotal));
            }
        }

        receipt.append("----------------------\n");
        receipt.append(String.format("Total: %d (yuan)\n", total));
        receipt.append("**********************");

        return receipt.toString();
    }
    private static int calculateTotal(Map<String, Item> itemsMap, Map<String, Integer> itemCount) {
        int total = 0;

        for (String barcode : itemCount.keySet()) {
            Item item = itemsMap.get(barcode);
            if (item != null) {
                int quantity = itemCount.get(barcode);
                total += item.getPrice() * quantity;
            }
        }

        return total;
    }

    private static Map<String, Integer> countItems(List<String> barcodes) {
        Map<String, Integer> itemCount = new HashMap<>();

        for (String barcode : barcodes) {
            itemCount.put(barcode, itemCount.getOrDefault(barcode, 0) + 1);
        }
        return itemCount;
    }

    private static Map<String, Item> loadItems() {
        List<Item> itemsList = ItemsLoader.loadAllItems();
        Map<String, Item> itemsMap = new HashMap<>();

        for (Item item : itemsList) {
            itemsMap.put(item.getBarcode(), item);
        }
        return itemsMap;
    }

}
