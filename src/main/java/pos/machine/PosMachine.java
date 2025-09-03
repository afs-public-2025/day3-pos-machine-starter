package pos.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        // 加载所有商品
        List<Item> allItems = ItemsLoader.loadAllItems();

        // 计算所有商品个数
        Map<String, Integer> countedItems = countItems(barcodes);

        return null;
    }

    private Map<String, Integer> countItems(List<String> barcodes) {
        Map<String, Integer> itemCounts = new HashMap<>();
        barcodes.forEach(barcode -> itemCounts.put(barcode, itemCounts.getOrDefault(barcode, 0) + 1));
        return itemCounts;
    }

    private Item findItemByBarcode(String barcode, List<Item> allItems) {
        if (Objects.isNull(allItems)) {
            return null;
        }
        return allItems.stream()
                .filter(item -> barcode.equals(item.getBarcode()))
                .findFirst()
                .orElse(null);
    }

}