package pos.machine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public List<Item> itemList =  ItemsLoader.loadAllItems();

    public boolean checkAllBarcodesValid(List<String> barcodes) {
        return barcodes.stream()
                .allMatch(barcode -> itemList.stream()
                        .anyMatch(item -> item.getBarcode().equals(barcode)));
    }
    public String printReceipt(List<String> barcodes) {
        return null;
    }
}
