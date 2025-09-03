package pos.machine;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private Map<String, Integer> getBarcodesOccurrence(List<String> barcodes) {
        return barcodes.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

    private boolean isAllItemsExist(Map<String, Integer> barcodeOccurrence) {
        List<Item> itemRecords = ItemsLoader.loadAllItems();

        Set<String> existingBarcodes = itemRecords.stream()
                .map(Item::getBarcode)
                .collect(Collectors.toSet());

        return barcodeOccurrence.keySet().stream()
                .allMatch(existingBarcodes::contains);
    }

    private Item[] retrieveItemsInfo(Map<String, Integer> barcodeOccurrence) {
        return null;
    }

    private String buildReceipt(Map<String, Integer> barcodeOccurrence, Item[] items) {
        return null;
    }

    private String printReceiptEntry(Item item, int occurrence) {
        return null;
    }

    private int calculateSubtotalAmount(Item item, int occurrence) {
        return -1;
    }

    private int calculateTotalAmount(Map<String, Integer> barcodeOccurrence, Item[] items) {
        return -1;
    }
}
