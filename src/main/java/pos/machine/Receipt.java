package pos.machine;

import java.util.List;

public class Receipt {
    private List<ReceiptItem> receiptItems;
    private int totalPrice;

    public Receipt(int totalPrice, List<ReceiptItem> receiptItems) {
        this.totalPrice = totalPrice;
        this.receiptItems = receiptItems;
    }
}
