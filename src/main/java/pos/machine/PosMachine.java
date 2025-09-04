package pos.machine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<Item> allItems = loadAllItems();
        Map<String, Long> barcodeQuantities = countBarcodes(barcodes);
        
        StringBuilder receipt = new StringBuilder();
        receipt.append(generateReceiptHeader());
        
        long totalPrice = generateItemsContent(receipt, barcodeQuantities, allItems);
        receipt.append(generateReceiptFooter(totalPrice));
        
        return receipt.toString();
    }
    
    private List<Item> loadAllItems() {
        return ItemsLoader.loadAllItems();
    }
    
    private Map<String, Long> countBarcodes(List<String> barcodes) {
        return barcodes.stream()
                .collect(Collectors.groupingBy(
                    barcode -> barcode, 
                    LinkedHashMap::new,
                    Collectors.counting()
                ));
    }
    
    private Item findItemByBarcode(String barcode, List<Item> items) {
        return items.stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .orElse(null);
    }
    
    private long calculateSubtotal(Item item, long quantity) {
        return quantity * item.getPrice();
    }
    
    private long generateItemsContent(StringBuilder receipt, Map<String, Long> barcodeQuantities, List<Item> allItems) {
        long totalPrice = 0;
        
        for (Map.Entry<String, Long> entry : barcodeQuantities.entrySet()) {
            String barcode = entry.getKey();
            Long quantity = entry.getValue();
            
            Item item = findItemByBarcode(barcode, allItems);
            if (item != null) {
                long subtotal = calculateSubtotal(item, quantity);
                totalPrice += subtotal;
                receipt.append(generateItemLine(item, quantity, subtotal));
            } else {
                throw new IllegalArgumentException("Invalid barcode: " + barcode);
            }
        }
        
        return totalPrice;
    }
    
    private String generateItemLine(Item item, long quantity, long subtotal) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                item.getName(), quantity, item.getPrice(), subtotal);
    }
    
    private String generateReceiptHeader() {
        return "***<store earning no money>Receipt***\n";
    }
    
    private String generateReceiptFooter(long totalPrice) {
        return String.format("----------------------\nTotal: %d (yuan)\n**********************", totalPrice);
    }
}
