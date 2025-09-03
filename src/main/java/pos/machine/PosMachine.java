package pos.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private Map<String,Integer> countItemQuantity(List<String> barcodes){
        Map<String, Integer> quantityMap = new HashMap<>();
        for (String barcode : barcodes) {
            quantityMap.put(barcode, quantityMap.getOrDefault(barcode, 0) + 1);
        }
        return quantityMap;
    }
}
