import pos.machine.PosMachine;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PosMachine PosMachine = new PosMachine();
        List<String> barcodes = List.of(
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000001",
                "ITEM000001",
                "ITEM000004",
                "ITEM000004",
                "ITEM000004"
        );
        System.out.println(PosMachine.printReceipt(barcodes));
        System.out.println();
//        System.out.println(PosMachine.getReceiptEntry(PosMachine.getItem("ITEM000004"),  PosMachine.getQuantities(barcodes).get("ITEM000004")));
    }
}
