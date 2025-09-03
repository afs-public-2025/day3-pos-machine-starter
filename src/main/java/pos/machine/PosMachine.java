package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    private List<Item> allItems = ItemsLoader.loadAllItems();
    public String printReceipt(List<String> barcodes) {
        Map<String,Integer> quantityMap = countItemQuantity(barcodes);
        List<Item> items = findItemsByBarcodes(quantityMap.keySet());
        return generateReceipt(quantityMap,items);
    }
    private Map<String,Integer> countItemQuantity(List<String> barcodes){
        Map<String, Integer> quantityMap = new LinkedHashMap<>();
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
    private int calculateSubtotalPrice(Item item,int quantity){
        return item.getPrice() * quantity;
    }

    private String generateReceiptLine(Item item,int quantity,int subtotal){
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
                item.getName(), quantity, item.getPrice(), subtotal);
    }
    private String generateReceipt(Map<String,Integer> quantityMap,List<Item> items){
        StringBuilder receipt = new StringBuilder();
        int total = 0;
        receipt.append("***<store earning no money>Receipt***\n");
        for(Item item:items){
            int itemQuantity = quantityMap.get(item.getBarcode());
            int subtotal = calculateSubtotalPrice(item,itemQuantity);
            total += subtotal;
            receipt.append(generateReceiptLine(item,itemQuantity,subtotal) + "\n");
        }
        receipt.append("----------------------\n");
        receipt.append(String.format("Total: %d (yuan)\n",total));
        receipt.append("**********************");
        return receipt.toString();
    }

}
