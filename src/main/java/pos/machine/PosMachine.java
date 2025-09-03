package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ItemList> itemLists = new ArrayList<>();
        countItems(barcodes, itemLists);
        calculatePrice(itemLists);
        return null;
    }

    public void countItems(List<String> barcodes, List<ItemList> itemLists) {
        List<Item> items = ItemsLoader.loadAllItems();
        for (String barcode : barcodes) {
            boolean flag = true;
            if(!itemLists.isEmpty()) {
                for(ItemList itemList : itemLists) {
                    if(itemList.getBarcode().equals(barcode)) {
                        itemList.addCount();
                        flag = false;
                        break;
                    }
                }
            }
            if(flag){
                for (Item item : items) {
                    if(item.getBarcode().equals(barcode)) {
                        ItemList itemList = new ItemList(barcode, item.getName(), item.getPrice());
                        itemLists.add(itemList);
                    }
                }
            }
        }
        System.out.println(itemLists.size());
    }

    public void calculatePrice(List<ItemList> itemLists) {
        for(ItemList itemList : itemLists) {
            itemList.setSubtotal(itemList.getPrice() * itemList.getCount());
            System.out.println(itemList.getSubtotal());
        }
    }
}
