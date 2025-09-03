package pos.machine;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> barcodeOccurrence = getBarcodesOccurrence(barcodes);
        if(!isAllItemsExist(barcodeOccurrence)) {
            throw new Error("Some barcode does not exist in database");
        }
        List<Item> items = retrieveItemsInfo(barcodeOccurrence);
        return buildReceipt(barcodeOccurrence, items);
    }

    private Map<String, Integer> getBarcodesOccurrence(List<String> barcodes) {
        return barcodes.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new,
                        Collectors.summingInt(e -> 1)));

    }

    private boolean isAllItemsExist(Map<String, Integer> barcodeOccurrence) {
        // Get all item records for database
        List<Item> itemRecords = ItemsLoader.loadAllItems();

        Set<String> existingBarcodes = itemRecords.stream()
                .map(Item::getBarcode)
                .collect(Collectors.toSet());

        // Check if all barcode scanned exists in the database
        return barcodeOccurrence.keySet().stream()
                .allMatch(existingBarcodes::contains);
    }

    private List<Item> retrieveItemsInfo(Map<String, Integer> barcodeOccurrence) {
        // Get all item records for database
        List<Item> itemRecords = ItemsLoader.loadAllItems();

        // Filter out items with barcode scanned
        return itemRecords.stream()
                .filter(record -> barcodeOccurrence.containsKey(record.getBarcode())).toList();
    }

    private String buildReceipt(Map<String, Integer> barcodeOccurrence, List<Item> items) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("***<store earning no money>Receipt***").append('\n');
        // Build receipt entry for each type of items
        for(String barcode: barcodeOccurrence.keySet()) {
            Optional<Item> optionalBarcodeMatchingItem = items.stream()
                    .filter(item -> item.getBarcode().equals(barcode))
                    .findFirst();
            if(optionalBarcodeMatchingItem.isEmpty()) {
                throw new Error("No matching item found for barcode: " + barcode);
            }
            Item barcodeMatchingItem = optionalBarcodeMatchingItem.get();
            receipt.append(buildReceiptEntry(barcodeMatchingItem, barcodeOccurrence.get(barcode)));
            receipt.append('\n');
        }
        // Get the total amount for the receipt
        receipt.append("----------------------").append("\n");
        receipt.append(String.format("Total: %d (yuan)", calculateTotalAmount(barcodeOccurrence, items))).append('\n');
        receipt.append("**********************");
        return receipt.toString();
    }

    private String buildReceiptEntry(Item item, int occurrence) {
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)",
                item.getName(), occurrence, item.getPrice(), calculateSubtotalAmount(item, occurrence));
    }

    private int calculateSubtotalAmount(Item item, int occurrence) {
        return item.getPrice() * occurrence;
    }

    private int calculateTotalAmount(Map<String, Integer> barcodeOccurrence, List<Item> items) {
        return items.stream()
                .mapToInt(item -> item.getPrice() * barcodeOccurrence.get(item.getBarcode()))
                .sum();
    }
}
