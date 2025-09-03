package pos.machine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args){
        PosMachine posMachine = new PosMachine();
        List<String> barcodes = new ArrayList<>(Arrays.asList(
                "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000",
                "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004"
        ));
        posMachine.printReceipt(barcodes);
    }
}
