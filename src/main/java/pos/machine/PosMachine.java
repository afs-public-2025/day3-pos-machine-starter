package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    private static final Map<String,Item> db=new HashMap<>();
    static {
        db.put("ITEM000000",new Item("ITEM000000","Coca-Cola",3));
        db.put("ITEM000001",new Item("ITEM000001","Sprite",3));
        db.put("ITEM000004",new Item("ITEM000004","Battery",2));
    }

    public static void main(String[] args) {
        PosMachine posMachine=new PosMachine();

        List<String> barcodes=new ArrayList<>(Arrays.asList("ITEM000000","ITEM000000","ITEM000000","ITEM000000","ITEM000001","ITEM000001",
                "ITEM000004","ITEM000004","ITEM000004"));

        System.out.println(posMachine.printReceipt(barcodes));
    }
    public String printReceipt(List<String> barcodes) {
        Map<String,Integer> barcodeMap=conbineSameItemAndCalQuantity(barcodes);
        Map<String,Item> recordMap=getRecordsFromDB(barcodeMap);
        return buildReceipt(barcodeMap,recordMap);
    }

    private Map<String,Integer> conbineSameItemAndCalQuantity(List<String> barcodes){
        Map<String,Integer> mp;
        mp=barcodes.stream()
                .collect(Collectors.toMap(
                        barcode -> barcode,
                        barcode -> 1,
                        (existing, newValue) -> existing + 1
                ));
        return mp;
    }
    private Map<String,Item> getRecordsFromDB(Map<String,Integer> barcodeMap){
        Map<String,Item> ans;
        ans = barcodeMap.keySet().stream()
                .collect(Collectors.toMap(
                        barcode -> barcode,
                        this::getRecordFromDB
                ));
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
        ans.append(barcodeMap.keySet().stream()
                .map(barcode -> buildReceiptLine(barcodeMap.get(barcode), recordMap.get(barcode)))
                .collect(Collectors.joining()));
        ans.append("----------------------\n");
        ans.append(calTotalPriceAndBuildTotalLine(barcodeMap,recordMap));
        ans.append("**********************\n");
        return ans.toString();
    }
    private String buildReceiptLine(Integer barcodeTimes,Item record){
        return "Name: "+record.getName()+", Quantity: "+barcodeTimes+", Unit price: "+record.getPrice()+", Subtotal: "+(record.getPrice()*barcodeTimes)+" (yuan)\n";
    }
    private String calTotalPriceAndBuildTotalLine(Map<String,Integer> barcodeMap,Map<String,Item> recordMap){
        int total= barcodeMap.entrySet().stream()
                .mapToInt(entry -> {
                    String barcode = entry.getKey();
                    int quantity = entry.getValue();
                    return recordMap.get(barcode).getPrice() * quantity;
                })
                .sum();
        return "Total: "+total+" (yuan)\n";
    }
}
