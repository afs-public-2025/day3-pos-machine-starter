package pos.machine;

import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        // 加载所有商品
        List<Item> allItems = ItemsLoader.loadAllItems();

        return null;
    }


}