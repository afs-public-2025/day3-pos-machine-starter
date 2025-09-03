package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }
    private Map<String,Integer> getItemsQuantity(List<String> barcodes){
        Map<String,Integer> itemsQuantity = new HashMap<String,Integer>();
        barcodes.forEach(barcode->{
            if(itemsQuantity.containsKey(barcode)){
                itemsQuantity.put(barcode,itemsQuantity.get(barcode)+1);
            }else{
                itemsQuantity.put(barcode,1);
            }
        });
        return itemsQuantity;
    }
    private String findItemNameByBarcode(List<Item> items, String barcode){
        return items.stream().filter(item->item.getBarcode().equals(barcode)).findFirst().get().getName();
    }
    private int findPriceByBarcode(List<Item> items, String barcode){
        return items.stream().filter(item->item.getBarcode().equals(barcode)).findFirst().get().getPrice();
    }
    private List<ReceiptItem> generateReceiptItemDetails(List<Item> items,Map<String,Integer> itemsQuantity){
        List<ReceiptItem> receiptItemsDetails = new ArrayList<ReceiptItem>();
        itemsQuantity.forEach((barcode,quantity)->{
            String name = findItemNameByBarcode(items,barcode);
            int price = findPriceByBarcode(items,barcode);
            ReceiptItem receiptItem = new ReceiptItem(name,price,quantity,price*quantity);
            receiptItemsDetails.add(receiptItem);
        });
        return receiptItemsDetails;
    }
    private int getTotal(List<ReceiptItem> receiptItemsDetails){
        int total = 0;
        for (ReceiptItem item : receiptItemsDetails){
            total+=item.getSubtotal();
        }
        return total;
    }

}
