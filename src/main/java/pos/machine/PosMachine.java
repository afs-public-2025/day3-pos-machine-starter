package pos.machine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public List<Item> itemList =  ItemsLoader.loadAllItems();

    public boolean checkAllBarcodesValid(List<String> barcodes) {
        return barcodes.stream()
                .allMatch(barcode -> itemList.stream()
                        .anyMatch(item -> item.getBarcode().equals(barcode)));
    }

    public Map<String, Integer> createOccureneceMap(List<String> barcodes) {
        Map<String, Integer> occurrenceMap = new LinkedHashMap<>();
        for (String barcode : barcodes) {
            occurrenceMap.put(barcode, occurrenceMap.getOrDefault(barcode, 0) + 1);
        }
        return occurrenceMap;
    }

    public int getSubtotal(Item item, int quantity) {
        return item.getPrice() * quantity;
    }

    public String getItemDisplayLine(Item item, int quantity, int subtotal) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                item.getName(), quantity, item.getPrice(), subtotal);
    }

    public Item getItemByBarcode(String barcode) {
        return itemList.stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }

    public String transformOccurenceMapToReceipt(Map<String, Integer> occurrenceMap) {
        StringBuilder receipt = new StringBuilder("***<store earning no money>Receipt***\n");
        int total = 0;

        for (Map.Entry<String, Integer> entry : occurrenceMap.entrySet()) {
            String barcode = entry.getKey();
            int quantity = entry.getValue();
            Item item = getItemByBarcode(barcode);

            if (item != null) {
                int subtotal = getSubtotal(item, quantity);
                total += subtotal;
                receipt.append(getItemDisplayLine(item, quantity, subtotal));
            }
        }

        receipt.append("----------------------\n");
        receipt.append(String.format("Total: %d (yuan)\n", total));
        receipt.append("**********************");
        return receipt.toString();
    }

    public String printReceipt(List<String> barcodes) {
        if (!checkAllBarcodesValid(barcodes)){
            throw new RuntimeException("There are invalid barcode(s).");
        }
        Map<String, Integer> occurrenceMap = createOccureneceMap(barcodes);
        return transformOccurenceMapToReceipt(occurrenceMap);
    }
}
