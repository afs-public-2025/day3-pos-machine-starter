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
    public Item getItemByBarcode(String barcode) {
        return itemList.stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }
    public String printReceipt(List<String> barcodes) {
        return null;
    }
}
