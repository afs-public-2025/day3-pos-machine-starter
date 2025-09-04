package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pos.machine.ItemsLoader.loadAllItems;

public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(generateHeader());
        List<String> uniqueBarcodes = distinct(barcodes);
        for (String barcode : uniqueBarcodes) {
            receipt.append(generateLine(barcodes, barcode));
        }
        receipt.append(generateFooter(getTotalPrice(barcodes)));
        return receipt.toString();
    }

    private String generateHeader() {
        return "***<store earning no money>Receipt ***\n";
    }

    private String generateFooter(int totalPrice) {
        return String.format("----------------------\nTotal: %d (yuan)\n**********************", totalPrice);
    }

    private String getName(String barcode) {
        return loadAllItems().stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .map(Item::getName)
                .orElse(null);
    }

    private int getPrice(String barcode) {
        return loadAllItems().stream()
                .filter(item -> item.getBarcode().equals(barcode))
                .findFirst()
                .map(Item::getPrice)
                .orElse(0);}

    private int getQuantity(List<String> barcodes, String barcode) {
        int count = 0;
        for (String item : barcodes) {
            if (item.equals(barcode)){
                count++;
            }
        }
        return count;
    }

    private int getSubPrice(int price, int quantity) {
        return price * quantity;
    }

    private int getTotalPrice(List<String> barcodes) {
        int total = 0;
        List<String> uniqueBarcodes = distinct(barcodes);
        for (String barcode : uniqueBarcodes) {
            int quantity = getQuantity(barcodes, barcode);
            int price = getPrice(barcode);
            total += getSubPrice(price, quantity);
        }
        return total;
    }

    private List<String> distinct(List<String> barcodes) {
        List<String> uniqueBarcodes = new ArrayList<>();
        for (String barcode : barcodes) {
            if (!uniqueBarcodes.contains(barcode)) {
                uniqueBarcodes.add(barcode);
            }
        }
        return uniqueBarcodes;
    }

    private String generateLine(List<String> barcodes, String barcode) {
        int quantity = getQuantity(barcodes, barcode);
        int price = getPrice(barcode);
        String name = getName(barcode);
        int subTotal = getSubPrice(price, quantity);
        return String.format("Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                name, quantity, price, subTotal);
    }


}
