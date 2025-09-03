package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Map<String, ReceiptItem> receiptItemMap = getReceiptItemMapByBarcodes(barcodes);

        return null;
    }
    public Map<String, ReceiptItem> getReceiptItemMapByBarcodes(List<String> barcodes){
        Map<String, Item> itemMap = getItemMap(barcodes);
        isItemExist(itemMap,barcodes);
        return buildReceiptItemMap(itemMap, barcodes);
    }
    public Map<String, Item> getItemMap(List<String> barcodes){
        List<Item> items = ItemsLoader.loadAllItems();
        Map<String, Item> itemMap = new HashMap<>();
        for (Item item : items) {
            itemMap.put(item.getBarcode(), item);
        }
        return itemMap;
    }
    public void isItemExist(Map<String, Item> itemMap,List<String> barcodes){
        for (String barcode : barcodes) {
            if (!itemMap.containsKey(barcode)) {
                throw new RuntimeException();
            }
        }
    }
    public Map<String,ReceiptItem> buildReceiptItemMap(Map<String, Item> itemMap,List<String> barcodes){
        Map<String, ReceiptItem> receiptItemMap = new HashMap<>();
        for (String barcode : barcodes) {
            Item item = itemMap.get(barcode);
            ReceiptItem receiptItem = receiptItemMap.get(barcode);
            if (receiptItem!=null){
                receiptItem.setQuantity(receiptItem.getQuantity()+1);
                receiptItemMap.put(item.getName(),receiptItem);
            } else {
                ReceiptItem newReceiptItem = new ReceiptItem(item);
                receiptItemMap.put(item.getName(),newReceiptItem);
            }
        }
        return receiptItemMap;
    }

}
