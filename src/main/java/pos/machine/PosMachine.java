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
        List<String> barcodes = new ArrayList<>(itemQuantity.keySet());
        Collections.sort(barcodes);
        for (String barcode: barcodes) {
            System.out.println(barcode);
            items.add(getItem(barcode));
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

    public String createReceipt(Map<String, Integer> itemQuantity, List<Item> items) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("***<store earning no money>Receipt***");
        receipt.append("----------------------");
        for (Item item: items) {
            receipt.append(getReceiptEntry(item, itemQuantity.get(item.getName())));
        }
        receipt.append("**********************");
        return receipt.toString();
    }

    public String getReceiptEntry(Item item, int Quantity) {
//                "Name: Coca-Cola, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)\n" +
//                "Name: Sprite, Quantity: 2, Unit price: 3 (yuan), Subtotal: 6 (yuan)\n" +
//                "Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)\n" +;
        String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n");
        return "";
    }
}
