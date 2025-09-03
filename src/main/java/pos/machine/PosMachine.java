package pos.machine;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class PosMachine {
    public String printReceipt(String[] barcodes) {

        List<Item> allItems = ItemsLoader.loadAllItems();
        Map<String, Integer> barcodeCount = countBarcodes(barcodes);

        StringBuilder receipt = new StringBuilder();

        int total = 0;

        for (String barcode : barcodeCount.keySet()) {
            try {
                Item item = getItemByBarcode(allItems, barcode);
                int quantity = barcodeCount.get(barcode);
                int subtotal = item.getPrice() * quantity;
                total += subtotal;
                receipt.append(formatReceiptItem(item, quantity, subtotal));
                receipt.append("\n");
            } catch (ItemNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        receipt.append("----------------------\n");
        receipt.append("Total: ").append(total).append(" (yuan)");
        return receipt.toString();
    }

    private Map<String, Integer> countBarcodes(String[] barcodes) {
        Map<String, Integer> count = new LinkedHashMap<>();
        for (String barcode : barcodes) {
            count.put(barcode, count.getOrDefault(barcode, 0) + 1);
        }
        return count;
    }

    private Item getItemByBarcode(List<Item> items, String barcode) {
        for (Item item : items) {
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        throw new ItemNotFoundException("找不到条形码为 " + barcode + " 的商品");
    }

    private String formatReceiptItem(Item item, int quantity, int subtotal) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
                item.getName(), quantity, item.getPrice(), subtotal);
    }
}
