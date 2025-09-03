package pos.machine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PosMachine {
    private static final Map<String,Item> db=new HashMap<>();

    public static void main(String[] args) {
        db.put("ITEM000000",new Item("ITEM000000","Coca-Cola",3));
        db.put("ITEM000001",new Item("ITEM000001","Sprite",3));
        db.put("ITEM000004",new Item("ITEM000004","Battery",2));

    }
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private Map<String,Integer> conbineSameItemAndCalQuantity(List<String> barcodes){
        Map<String,Integer> mp=new HashMap<>();
        for(String barcode:barcodes){
            mp.put(barcode,mp.getOrDefault(barcode,0)+1);
        }
        return mp;
    }
    private Map<String,Item> getRecordsFromDB(Map<String,Integer> barcodeMap){
        return null;
    }
    private String buildReceipt(Map<String,Integer> barcodeMap,Map<String,Item> recordMap){
        return null;
    }
}
