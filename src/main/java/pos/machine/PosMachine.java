package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        // 从DB加载所有商品
        List<Item> allItems = ItemsLoader.loadAllItems();

        // 对输入进行校验，不合法barcode抛异常
        validateBarcodes(barcodes, allItems);

        // 所有商品计数
        Map<String, Integer> countedItems = countItems(barcodes);

        // 获取收据列表
        List<ReceiptItem> receiptItems = getReceiptItems(countedItems, allItems);

        // 计算商品总价
        int total = calculateTotal(receiptItems);

        // 转换收据列表为字符串
        return generateReceipt(receiptItems, total);
    }

    private void validateBarcodes(List<String> barcodes, List<Item> allItems) {
        List<String> validBarcodes = allItems.stream()
                .map(Item::getBarcode)
                .collect(Collectors.toList());

        // 检查每个输入的条形码是否有效
        for (String barcode : barcodes) {
            if (!validBarcodes.contains(barcode)) {
                throw new IllegalArgumentException("Invalid barcode: " + barcode);
            }
        }
    }

    private Map<String, Integer> countItems(List<String> barcodes) {
        // use LinkedMap to keep items order
        Map<String, Integer> itemCounts = new LinkedHashMap<>();
        barcodes.forEach(barcode -> itemCounts.put(barcode, itemCounts.getOrDefault(barcode, 0) + 1));
        return itemCounts;
    }

    private Item findItemByBarcode(String barcode, List<Item> allItems) {
        if (Objects.isNull(allItems)) {
            return null;
        }
        return allItems.stream()
                .filter(item -> barcode.equals(item.getBarcode()))
                .findFirst()
                .orElse(null);
    }

    /*
        Build ReceiptItem List by totalPrice
     */
    private List<ReceiptItem> getReceiptItems(Map<String, Integer> countItems, List<Item> allItems) {
        return countItems.entrySet().stream()
                .map(e -> {
                    String barcode = e.getKey();
                    Integer count = e.getValue();
                    Item item = findItemByBarcode(barcode, allItems);
                    if (Objects.nonNull(item)) {
                        int totalPrice = item.getPrice() * count;
                        return new ReceiptItem(item.getName(), count, item.getPrice(), totalPrice);
                    }
                    return null;
                }).collect(Collectors.toList());
    }

    private int calculateTotal(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .mapToInt(ReceiptItem::getSubtotal)
                .sum();
    }


    private String generateReceipt(List<ReceiptItem> receiptItems, int total) {
        StringBuffer receipt = new StringBuffer();
        receipt.append("***<store earning no money>Receipt***\n");
        receiptItems.forEach(item -> receipt.append(String.format(
                "Name: %s, Quantity: %d, Unit price: %d (yuan), Subtotal: %d (yuan)\n",
                item.getName(), item.getQuantity(), item.getUnitPrice(), item.getSubtotal()
        )));
        receipt.append("----------------------\n").append(String.format("Total: %d (yuan)\n", total))
                .append("**********************");
        return receipt.toString();
    }

}