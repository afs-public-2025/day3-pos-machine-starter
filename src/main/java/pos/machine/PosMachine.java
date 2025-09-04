package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) throws Exception {
        Map<String, Integer> quantities = getQuantities(barcodes);
        List<Item> items = getItemsInfo(quantities);
        return createReceipt(quantities, items);
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
        receipt.append("***<store earning no money>Receipt***\n");
        for (Item item: items) {
            receipt.append(getReceiptEntry(item, itemQuantity.get(item.getBarcode())));
        }
        receipt.append("----------------------\n");
        receipt.append(String.format("Total: %d (yuan)\n", getTotal(itemQuantity, items)));
        receipt.append("**********************");
        return receipt.toString();
    }

    public String getReceiptEntry(Item item, int Quantity) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n", item.getName(), Quantity, item.getPrice(), getSubtotal(item, Quantity));
    }

    public int getSubtotal(Item item, int Quantity) {
        return item.getPrice()*Quantity;
    }

    public int getTotal(Map<String, Integer> itemQuantity, List<Item> items) {
        int total = 0;
        for (Item item: items) {
            total += getSubtotal(item, itemQuantity.get(item.getBarcode()));
        }
        return total;
    }
}
