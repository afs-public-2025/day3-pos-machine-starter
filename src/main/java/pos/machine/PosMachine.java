package pos.machine;

import java.util.List;
import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
    Map<String, Integer> itemsMap = new HashMap<>();
    ItemsLoader db = new ItemsLoader();
    List<Item> dbItems = db.loadAllItems();
    int total = 0;

    public String printReceipt(List<String> barcodes) throws Exception {
        String receipt = "***<store earning no money>Receipt***\n";
        try{
            countItems(barcodes);
        } catch (Exception e){throw e;}

        for (String barcode: itemsMap.keySet()) {
            receipt += generateReceiptPerItem(barcode, Objects.requireNonNull(dbItems.stream().filter(item -> item.getBarcode().equals(barcode)).findFirst().orElse(null)), itemsMap.get(barcode));
        }

        receipt += "----------------------\n " +
                "Total: " + total + "(yuan)\n" +
                "**********************";

        return receipt;
    }
        return null;
    public void countItems(List<String> barcodes) throws Exception {
        for (String barcode : barcodes) {

            if (!isValid(barcode)) {
                throw new Exception("Invalid barcode!");
            }

            if (itemsMap.containsKey(barcode)){
                itemsMap.compute(barcode, (k, currentValue) -> currentValue + 1);
            } else {
                itemsMap.put(barcode, 1);
            }
        }
    }
    }
}
