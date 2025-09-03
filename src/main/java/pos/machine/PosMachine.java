package pos.machine;

import java.util.List;
import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
    Map<String, Integer> itemsMap = new HashMap<>();
    ItemsLoader db = new ItemsLoader();
    List<Item> dbItems = db.loadAllItems();
    int total = 0;
        return null;
    }
}
