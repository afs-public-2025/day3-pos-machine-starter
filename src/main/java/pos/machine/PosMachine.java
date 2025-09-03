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
        return null;
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
