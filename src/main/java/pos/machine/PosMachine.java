package pos.machine;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        StringBuilder resultStr = new StringBuilder("***<store earning no money>Receipt***\n");
        long totalPrice = 0;
        List<Item> itemInfoList= ItemsLoader.loadAllItems();
        Map<String, Long> barcodesNumberMap = barcodes.stream().collect(Collectors.groupingBy(e -> e, LinkedHashMap::new,Collectors.counting()));
        for(Map.Entry<String, Long> entry :barcodesNumberMap.entrySet()){
            for(Item item:itemInfoList){
                if(item.getBarcode().equals(entry.getKey())){
                    totalPrice+=entry.getValue()*item.getPrice();
                    resultStr.append("Name: ").append(item.getName()).append(", Quantity: ").append(entry.getValue()).append(", Unit price: ").append(item.getPrice()).append(" (yuan), Subtotal: ").append(entry.getValue()*item.getPrice()).append(" (yuan)\n");
                }
            }
        }
        resultStr.append("----------------------\nTotal: ").append(totalPrice).append(" (yuan)\n").append("**********************");
        return resultStr.toString();
    }
}
