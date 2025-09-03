package pos.machine;


import java.util.*;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;

    }
    private Map<String , Integer> countBarcodes(List<String> barcodes){
        Map<String, Integer> barcodeCountMap= new HashMap<>();
        for (String barcode : barcodes) {
            if (barcodeCountMap.containsKey(barcode)) {
                barcodeCountMap.put(barcode, barcodeCountMap.get(barcode) + 1);
            } else {
                barcodeCountMap.put(barcode, 1);
            }
        }
        return barcodeCountMap;
    }

}

