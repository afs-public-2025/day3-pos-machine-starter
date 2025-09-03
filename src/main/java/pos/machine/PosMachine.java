package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = decodeToItems(barcodes);
        Receipt receipt = calculateCost(receiptItems);
        return null;
    }

    private Receipt calculateCost(List<ReceiptItem> receiptItems) {
        int totalPrice = 0;
        for (ReceiptItem item : receiptItems) {
            totalPrice += item.getSubTotal();
        }
        return new Receipt(totalPrice, receiptItems);
    }

    public List<ReceiptItem> decodeToItems(List<String> barcodes) {
        if(barcodes == null || barcodes.isEmpty()) {
            throw new IllegalArgumentException("Barcodes list cannot be null or empty");
        }
        List<Item> items = ItemsLoader.loadAllItems();
        Map<String, Integer> barcodeCountMap = new HashMap<>();
        for (String barcode : barcodes) {
            barcodeCountMap.put(barcode, barcodeCountMap.getOrDefault(barcode, 0) + 1);
        }

        Map<String, Item> itemMap = new HashMap<>();
        for (Item item : items) {
            itemMap.put(item.getBarcode(), item);
        }

        List<ReceiptItem> receiptItemList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : barcodeCountMap.entrySet()) {
            String barcode = entry.getKey();
            Integer quantity = entry.getValue();

            Item item = itemMap.get(barcode);
            if (item != null) {
                ReceiptItem receiptItem = new ReceiptItem(
                        item.getName(),
                        quantity,
                        item.getPrice(),
                        item.getPrice() * quantity
                );
                receiptItemList.add(receiptItem);
            }
        }

        return receiptItemList;
    }
}
