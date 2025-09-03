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
        System.out.println("收据详情：\n");
        System.out.println("***<store earning no money>Receipt***");
        String receipt = posMachine.printReceipt(barcodes);
        System.out.println(receipt);
        System.out.println("================================");

    }
}
