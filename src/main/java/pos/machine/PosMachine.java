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




}
