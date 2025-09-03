package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pos.machine.ItemsLoader.loadAllItems;

public class PosMachine {


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



}
