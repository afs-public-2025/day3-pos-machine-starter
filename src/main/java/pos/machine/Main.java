package pos.machine;

/**
 * POS机系统的主类，用于演示收银功能
 */
public class Main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();

        String[] barcodes = new String[]{
            "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000",  // 4个可乐
            "ITEM000001", "ITEM000001",  // 2个雪碧
            "ITEM000004", "ITEM000004", "ITEM000004"  // 3个电池
        };

        String receipt = posMachine.printReceipt(barcodes);
        System.out.println("================================");
        System.out.println(receipt);
        System.out.println("================================");

    }
}
