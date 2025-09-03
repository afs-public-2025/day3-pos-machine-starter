package pos.machine;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class PosMachine {
    private Set<String> uniqueBarcodes = new HashSet<>();
    private Map<String, String> barcodeToName = new HashMap<>();
    private Map<String, Integer> barcodeToUnitPrice = new HashMap<>();

    PosMachine() {
        for (Item item: ItemsLoader.loadAllItems()) {
            String barcode = item.getBarcode();
            String name = item.getName();
            int price = item.getPrice();
            uniqueBarcodes.add(barcode);
            barcodeToName.put(barcode, name);
            barcodeToUnitPrice.put(barcode, price);
        }
    }

    public String printReceipt(List<String> barcodes) {

    }
}
