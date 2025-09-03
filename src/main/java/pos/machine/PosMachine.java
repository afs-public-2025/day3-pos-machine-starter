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
        Map<String,Item> ans=new HashMap<>();
        for(String barcode:barcodeMap.keySet()){
            ans.put(barcode,getRecordFromDB(barcode));
        }
        return ans;
    }
    private Item getRecordFromDB(String barcode){
        Item item=db.get(barcode);
        if (item==null) throw new RuntimeException();
        return item;
    }
    private String buildReceipt(Map<String,Integer> barcodeMap,Map<String,Item> recordMap){
        StringBuilder ans=new StringBuilder();
        ans.append("***<store earning no money>Receipt***\n");
        for(String barcode:barcodeMap.keySet()){
            String tmp=buildReceiptLine(barcodeMap.get(barcode),recordMap.get(barcode));
            ans.append(tmp);
        }
        ans.append("----------------------\n");
        ans.append(calTotalPriceAndBuildTotalLine(barcodeMap,recordMap));
        ans.append("**********************\n");
        return ans.toString();
    }
    private String buildReceiptLine(Integer barcodeTimes,Item record){
        return "Name: "+record.getName()+", Quantity: "+barcodeTimes+", Unit price: "+record.getPrice()+", Subtotal: "+(record.getPrice()*barcodeTimes)+" (yuan)\n";
    }
    private String calTotalPriceAndBuildTotalLine(Map<String,Integer> barcodeMap,Map<String,Item> recordMap){
        int total=0;
        for(String barcode:barcodeMap.keySet()){
            total+=recordMap.get(barcode).getPrice()*barcodeMap.get(barcode);
        }
        return "Total: "+total+" (yuan)\n";
    }
}
