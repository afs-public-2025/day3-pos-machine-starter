package pos.machine;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
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

    
    private String generateItemLine(Item item, long quantity, long subtotal) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)%n",
                item.getName(), quantity, item.getPrice(), subtotal);
    }
    
    private String generateReceiptHeader() {
        return "***<store earning no money>Receipt***\n";
    }
    
    private String generateReceiptFooter(long totalPrice) {
        return String.format("----------------------%nTotal: %d (yuan)%n**********************", totalPrice);
    }
}
