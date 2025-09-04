package pos.machine;

import java.util.*;

public class PosMachine {
    public String printReceipt(List<String> barcodes) throws Exception {
        Map<String, Integer> quantityMap = countBarcode(barcodes);
        List<Item> items = findItemsBatch(quantityMap);
        return generateReceipt(items, quantityMap);
    }

    private String generateReceipt(List<Item> items, Map<String, Integer> quantityMap) {
        StringBuilder sb = new StringBuilder();
        int total = 0;
        sb.append("***<store earning no money>Receipt***\n");
        for (Item item: items) {
            int subTotal = quantityMap.get(item.getBarcode()) * item.getPrice();
            sb.append(String.format(
                    "Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                    item.getName(), quantityMap.get(item.getBarcode()), item.getPrice(), subTotal
            ));
            total += subTotal;
        }
        sb.append("----------------------\n");
        sb.append(String.format(
                "Total: %d (yuan)\n",
                total
        ));
        sb.append("**********************");
        return sb.toString();
    }

    private List<Item> findItemsBatch(Map<String, Integer> quantityMap) throws Exception {
        List<Item> items = new ArrayList<>();
        List<Item> dataBase = ItemsLoader.loadAllItems();
        for (Map.Entry<String, Integer> entry: quantityMap.entrySet()) {
            boolean flag = false;
            String barcode = entry.getKey();
            for (Item data: dataBase) {
                if(barcode.equals(data.getBarcode())) {
                    items.add(data);
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                throw new Exception("barcode: " + barcode + " not found in database");
            }
        }
        return items;
    }

    private Map<String, Integer> countBarcode(List<String> barcodes) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String barcode: barcodes) {
            map.merge(barcode, 1, Integer::sum);
        }
        return map;
    }
}
