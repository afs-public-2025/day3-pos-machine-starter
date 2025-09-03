package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {

    public static String printReceipt(List<String> barcodes) {
        Map<String, Item> itemsMap = loadItems();
        Map<String, Integer> itemCount = countItems(barcodes);
        return createReceipt(itemsMap, itemCount);
    }

    private static String createReceipt(Map<String, Item> itemsMap, Map<String, Integer> itemCount) {
        return null;
    }

    private static Map<String, Integer> countItems(List<String> barcodes) {
        return null;
    }

    private static Map<String, Item> loadItems() {
        return null;
    }

}
