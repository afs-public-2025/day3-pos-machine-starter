package pos.machine;

public class Main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
        String receipt = posMachine.printReceipt(
                java.util.Arrays.asList(
                        "ITEM000001",
                        "ITEM000001",
                        "ITEM000001",
                        "ITEM000003",
                        "ITEM000005",
                        "ITEM000005",
                        "ITEM000005"
                )
        );
        System.out.println(receipt);
    }
}
