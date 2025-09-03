package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
        List<String> barcodes = Arrays.asList(
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000001",
                "ITEM000001",
                "ITEM000004",
                "ITEM000004",
                "ITEM000004");
        System.out.println(posMachine.printReceipt(barcodes));
    }
}
