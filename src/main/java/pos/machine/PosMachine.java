package pos.machine;

import java.util.stream.Collectors;
import java.util.*;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {

        return null;

    }
    private Map<String , Integer> countBarcodes(List<String> barcodes){
        Map<String, Integer> barcodeCountMap= new HashMap<>();
        for (String barcode : barcodes) {
            if (barcodeCountMap.containsKey(barcode)) {
                barcodeCountMap.put(barcode, barcodeCountMap.get(barcode) + 1);
            } else {
                barcodeCountMap.put(barcode, 1);
            }
        }
        return barcodeCountMap;
    }

    private List<ItemInformation> getItemsInformation(Map<String, Integer> barcodeCountMap,List<Item> allItems){
        List<ItemInformation> itemsInformation=new ArrayList<>();
        Set<String> knownBarcodes = allItems.stream()
                .map(Item::getBarcode)
                .collect(Collectors.toSet());

        barcodeCountMap.keySet().forEach(barcode -> {
            if (!knownBarcodes.contains(barcode)) {
                throw new IllegalArgumentException("Item not found for barcode: " + barcode);
            }
        });

        for (Item item : allItems) {
            String barcode = item.getBarcode();
            if (barcodeCountMap.containsKey(barcode)) {
                int quantity = barcodeCountMap.get(barcode);
                itemsInformation.add(new ItemInformation(item, quantity));
            }
        }
        return itemsInformation;
    }

    private  void calculateSubtotal(List<ItemInformation> itemsInformation){
        for(ItemInformation itemInformation: itemsInformation){
            itemInformation.setSubtotal(itemInformation.getItem().getPrice()*itemInformation.getQuantity());


        }
    }

    private int calculateTotal(List<ItemInformation> itemsInformation){
        int total=0;
        for(ItemInformation itemInformation: itemsInformation){
            total+=itemInformation.getSubtotal();
        }
        return total;
    }



    private static  class ItemInformation {
        private final Item item;
        private final int quantity;
        private int subtotal;

        public ItemInformation(Item ITEM, int QUANTITY) {
            this.item = ITEM;
            this.quantity = QUANTITY;
        }

        public Item getItem() {
            return item;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(int subtotal) {
            this.subtotal = subtotal;
        }
    }
}

