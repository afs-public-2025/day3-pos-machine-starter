package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = decodeToItems(barcodes);
        Receipt receipt = calculateCost(receiptItems);
        String receiptString = renderReceipt(receipt);
        return receiptString;
    }

    private String renderReceipt(Receipt receipt) {
        String itemsReceipt = generateItemsReceipt(receipt);
        String receiptString = generateReceipt(itemsReceipt,receipt);
        return receiptString;
    }

    private String generateReceipt(String itemsReceipt, Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append(itemsReceipt);
        sb.append(String.format("Total: %d (yuan)\n", receipt.getTotalPrice()));
        sb.append("**********************");
        return sb.toString();
    }

    private String generateItemsReceipt(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        sb.append("***<store earning no money>Receipt***\n");
        for (ReceiptItem item : receipt.getReceiptItems()) {
            sb.append(String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                    item.getName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getSubTotal()));
        }
        sb.append("----------------------\n");
        return sb.toString();
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
