package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        PosMachineService posMachineService = new PosMachineService();
        Map<String, ReceiptItem> receiptItemMap = posMachineService.buildReceiptItemMap(barcodes);
        String s = posMachineService.buildReceipt(receiptItemMap);
        System.out.println(s);
        return s;
    }
}
