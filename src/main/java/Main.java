import pos.machine.PosMachine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PosMachine PosMachine = new PosMachine();
        System.out.println(PosMachine.getQuantities(List.of(new String[]{
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000000",
                "ITEM000001",
                "ITEM000001",
                "ITEM000004",
                "ITEM000004",
                "ITEM000004"
        })));
    }
}
