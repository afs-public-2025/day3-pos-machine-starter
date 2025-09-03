package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    private final Set<String> uniqueBarcodes = new HashSet<>();
    private final Map<String, String> barcodeToName = new HashMap<>();
    private final Map<String, Integer> barcodeToUnitPrice = new HashMap<>();
    private int total;

    PosMachine() {
        List<Item> items = ItemsLoader.loadAllItems();
        uniqueBarcodes.addAll(
                items.stream()
                        .map(Item::getBarcode)
                        .collect(Collectors.toSet())
        );
        barcodeToName.putAll(
                items.stream()
                        .collect(Collectors.toMap(Item::getBarcode, Item::getName))
        );
        barcodeToUnitPrice.putAll(
                items.stream()
                        .collect(Collectors.toMap(Item::getBarcode, Item::getPrice))
        );
        total = 0;
    }

    private List<String> validBarcodes(List<String> barcodes) {
        List<String> illegalBarcodes = barcodes.stream().filter(barcode -> !uniqueBarcodes.contains(barcode))
                .collect(Collectors.toList());
        if (!illegalBarcodes.isEmpty()) {
            throw new IllegalArgumentException("Illegal barcodes: " + String.join(", ",illegalBarcodes));
        }
        return barcodes.stream().filter(uniqueBarcodes::contains).collect(Collectors.toList());
    }

    private Map<String, Integer> countItems(List<String> barcodes) {
        // Use treemap to preserve order
        Map<String, Integer> itemCount = new TreeMap<>();
        try {
            List<String> validBarcodeList = validBarcodes(barcodes);
            for (String barcode: validBarcodeList) {
                itemCount.put(barcode, itemCount.getOrDefault(barcode, 0)+1);
            }
        }
        catch (IllegalArgumentException error) {
            System.out.println("Exception occurred: " + error);
        }
        return itemCount;
    }

    private int computeSubtotal(int unitPrice, int quantity) {
        int subtotal = unitPrice*quantity;
        this.total += subtotal;
        return subtotal;
    }

    private String createReceiptHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***<store earning no money>Receipt***\n");
        return stringBuilder.toString();
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

    private String createReceiptBody(Map<String, Integer> itemCount) {
        StringBuilder stringBuilder = new StringBuilder();
        itemCount.forEach((barcode, quantity) ->
                stringBuilder.append(createReceiptEntry(barcode, quantity)));
        return stringBuilder.toString();
    }

    private String createReceiptSummary() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("----------------------\n");
        stringBuilder.append("Total: ");
        stringBuilder.append(this.total);
        stringBuilder.append(" (yuan)\n");
        stringBuilder.append("**********************");
        return stringBuilder.toString();
    }

    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> itemCount = countItems(barcodes);
        if (itemCount.isEmpty()) {
            System.out.println("Empty barcode list, no receipt can be provided.");
            return "";
        }
        // Construct receipt
        StringBuilder stringBuilder = new StringBuilder();
        String header = createReceiptHeader();
        String body = createReceiptBody(itemCount);
        String summary = createReceiptSummary();
        System.out.println(header);
        System.out.println(body);
        System.out.println(summary);
        stringBuilder.append(header);
        stringBuilder.append(body);
        stringBuilder.append(summary);
        // Reset POS machine total after creating receipt
        this.total = 0;
        return stringBuilder.toString();
    }
}