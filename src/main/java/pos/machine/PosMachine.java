package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    private Set<String> uniqueBarcodes = new HashSet<>();
    private Map<String, String> barcodeToName = new HashMap<>();
    private Map<String, Integer> barcodeToUnitPrice = new HashMap<>();
    private int total;

    PosMachine() {
        for (Item item: ItemsLoader.loadAllItems()) {
            String barcode = item.getBarcode();
            String name = item.getName();
            int price = item.getPrice();
            uniqueBarcodes.add(barcode);
            barcodeToName.put(barcode, name);
            barcodeToUnitPrice.put(barcode, price);
        }
        total = 0;
    }

    private List<String> validBarcodes(List<String> barcodes) {
        return barcodes.stream().filter(barcode -> uniqueBarcodes.contains(barcode)).collect(Collectors.toList());
    }

    private Map<String, Integer> countItems(List<String> barcodes) {
        Map<String, Integer> itemCount = new TreeMap<>();
        List<String> validBarcodeList = validBarcodes(barcodes);
        for (String barcode: validBarcodeList) {
            itemCount.put(barcode, itemCount.getOrDefault(barcode, 0)+1);
        }
        return itemCount;
    }

    private int computeSubtotal(int unitPrice, int quantity) {
        int subtotal = unitPrice*quantity;
        this.total += subtotal;
        return subtotal;
    }

    private String createReceiptEntry(String barcode, int quantity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ");
        stringBuilder.append(barcodeToName.get(barcode));
        stringBuilder.append(", Quantity: ");
        stringBuilder.append(quantity);
        stringBuilder.append(", Unit price: ");
        int unitPrice = barcodeToUnitPrice.get(barcode);
        stringBuilder.append(unitPrice);
        stringBuilder.append(" (yuan), Subtotal: ");
        stringBuilder.append(computeSubtotal(unitPrice, quantity));
        stringBuilder.append(" (yuan)\n");
        return stringBuilder.toString();
    }

    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> itemCount = countItems(barcodes);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***<store earning no money>Receipt***\n");
        for (String barcode: itemCount.keySet()) {
            int quantity = itemCount.get(barcode);
            stringBuilder.append(createReceiptEntry(barcode, quantity));
        }
        stringBuilder.append("----------------------\n");
        stringBuilder.append("Total: ");
        stringBuilder.append(this.total);
        stringBuilder.append(" (yuan)\n");
        stringBuilder.append("**********************");

        // Reset POS machine total after creating receipt
        this.total = 0;
        return stringBuilder.toString();
    }
}

/*
***<store earning no money>Receipt***
Name: Coca-Cola, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)
Name: Sprite, Quantity: 2, Unit price: 3 (yuan), Subtotal: 6 (yuan)
Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)
----------------------
Total: 24 (yuan)
**********************
 */
