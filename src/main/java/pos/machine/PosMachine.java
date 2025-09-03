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




}
