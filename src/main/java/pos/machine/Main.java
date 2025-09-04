package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pos.machine.ItemsLoader.loadAllItems;

public class Main {
    public static void main(String[] args) throws Exception {
        PosMachine posMachine = new PosMachine();
        System.out.println(posMachine.printReceipt(loadBarcodes()));

    }

    private static List<String> loadBarcodes() {
        return Arrays.asList("ITEM00000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004");
    }
}
