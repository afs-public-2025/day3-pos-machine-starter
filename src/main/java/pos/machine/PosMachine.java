package pos.machine;

import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private Map<String, Integer> getBarcodesOccurrence(List<String> barcodes) {
        return null;
    }

    private boolean isAllItemsExist(Map<String, Integer> barcodeOccurrence) {
        return false;
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
