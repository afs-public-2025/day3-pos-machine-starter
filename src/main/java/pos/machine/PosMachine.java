package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

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

    private List<String> validBarcodes(List<String> barcodes) {
        return barcodes.stream().filter(barcode -> uniqueBarcodes.contains(barcode)).collect(Collectors.toList());
    }

    private Map<String, Integer> countItems(List<String> barcodes) {
        Map<String, Integer> itemCount = new HashMap<>();
        List<String> validBarcodeList = validBarcodes(barcodes);
        for (String barcode: validBarcodeList) {
            itemCount.put(barcode, itemCount.getOrDefault(barcode, 0));
        }
        return itemCount;
    }

    public String printReceipt(List<String> barcodes) {
        return "";
    }
}
