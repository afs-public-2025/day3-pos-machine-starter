package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public Map<String, Integer> getQuantities(List<String> barcodes) {
        Map<String, Integer> itemQuantity = new HashMap<>();
        for (String barcode: barcodes) {
            itemQuantity.putIfAbsent(barcode, Collections.frequency(barcodes, barcode));
        }
        return itemQuantity;
    }

    public List<Item> getItemsInfo(Map<String, Integer> itemQuantity) throws Exception {
        List<Item> items = new ArrayList<>();
        List<Item> itemInfoList = pos.machine.ItemsLoader.loadAllItems();
        for (Map.Entry<String, Integer> entry: itemQuantity.entrySet()) {
            items.add(getItem(entry.getKey()));
        }
        return items;
    }

    public Item getItem(String barcode) throws Exception {
        List<Item> itemInfoList = pos.machine.ItemsLoader.loadAllItems();
        for (Item item: itemInfoList) {
            if (barcode.equals(item.getBarcode())) {
                return item;
            }
        }
        throw new Exception();
    }
}
