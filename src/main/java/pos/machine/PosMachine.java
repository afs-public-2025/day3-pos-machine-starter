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
    
}
