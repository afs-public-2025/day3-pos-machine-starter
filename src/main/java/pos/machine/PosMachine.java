package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }
    private List<Item> allItems = ItemsLoader.loadAllItems();
    private Map<String,Integer> countItemQuantity(List<String> barcodes){
        Map<String, Integer> quantityMap = new HashMap<>();
        for (String barcode : barcodes) {
            quantityMap.put(barcode, quantityMap.getOrDefault(barcode, 0) + 1);
        }
        return quantityMap;
    }
    private List<Item> findItemsByBarcodes(Set<String> barcodes){
        Map<String, Item> itemMap = new HashMap<>();
        for (Item item : allItems) {
            itemMap.put(item.getBarcode(), item);
        }
        List<Item> items = new ArrayList<>();
        for (String barcode : barcodes) {
            Item item = itemMap.get(barcode);
            if (item == null) {
                throw new RuntimeException("The following barcodes do not exist: " + barcode);
            }
            items.add(item);
        }
        return items;
    }
    private Integer calculateSubtotalPrice(Item item,int quantity){
        return item.getPrice() * quantity;
    }

    private String generateReceiptLine(Item item,int quantity,int subtotal){
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
                item.getName(), quantity, item.getPrice(), subtotal);
    }

}
