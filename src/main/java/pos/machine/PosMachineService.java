package pos.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachineService {
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
            ReceiptItem receiptItem = receiptItemMap.get(item.getName());
            if (receiptItem!=null){
                receiptItem.setQuantity(receiptItem.getQuantity()+1);
                receiptItemMap.put(item.getName(),receiptItem);
            } else {
                ReceiptItem newReceiptItem = new ReceiptItem(item,1);
                receiptItemMap.put(item.getName(),newReceiptItem);
            }
        }
        return receiptItemMap;
    }


    public String buildReceipt(Map<String,ReceiptItem> receiptItemMap){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***<store earning no money>Receipt***\n");
        stringBuilder.append(printReceiptEntry(receiptItemMap));
        stringBuilder.append("----------------------\n");
        stringBuilder.append(calculateTotalAmount(receiptItemMap));
        stringBuilder.append("**********************");
        return stringBuilder.toString();
    }
    public String printReceiptEntry(Map<String,ReceiptItem> receiptItemMap){
        return receiptItemMap.values().stream()
                .map(item -> {
                    return String.format(
                            "Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                            item.getName(), item.getQuantity(), item.getUnitPrice(), item.getSubtotal());
                }).collect(Collectors.joining(""));
    }
    public String calculateTotalAmount(Map<String,ReceiptItem> receiptItemMap){
        int totalAmount = receiptItemMap.values().stream()
                .mapToInt(ReceiptItem::getSubtotal)
                .sum();
        return String.format("Total: %d (yuan)\n", totalAmount);
    }
}
