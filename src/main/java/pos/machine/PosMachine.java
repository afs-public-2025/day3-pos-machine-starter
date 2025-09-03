package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        // 从DB加载所有商品
        List<Item> allItems = ItemsLoader.loadAllItems();

        // 所有商品计数
        Map<String, Integer> countedItems = countItems(barcodes);

        // 获取收据列表
        List<ReceiptItem> receiptItems = getReceiptItems(countedItems, allItems);

        //

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

    /*
        Build ReceiptItem List by totalPrice
     */
    private List<ReceiptItem> getReceiptItems(Map<String, Integer> countItems, List<Item> allItems) {
        return countItems.entrySet().stream()
                .map(e -> {
                    String barcode = e.getKey();
                    Integer count = e.getValue();
                    Item item = findItemByBarcode(barcode, allItems);
                    if (Objects.nonNull(item)) {
                        Integer totalPrice = item.getPrice() * count;
                        return new ReceiptItem(item.getName(), count, item.getPrice(), totalPrice);
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    private int calculateTotal(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .mapToInt(ReceiptItem::getSubtotal)
                .sum();
    }




}