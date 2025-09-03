package pos.machine;

public class Main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
        String receipt = posMachine.printReceipt(
                java.util.Arrays.asList(
                        "ITEM000000",
                        "ITEM000000",
                        "ITEM000000",
                        "ITEM000000",
                        "ITEM000001",
                        "ITEM000001",
                        "ITEM000004",
                        "ITEM000004",
                        "ITEM000004"
                )
        );
        System.out.println(receipt);
    }
}
