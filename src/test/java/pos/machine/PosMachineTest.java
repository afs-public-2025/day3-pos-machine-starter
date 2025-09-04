package pos.machine;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Nested
class PosMachineTest {

    @Test
    public void should_return_receipt() {
        PosMachine posMachine = new PosMachine();

        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)\n" +
                "Name: Sprite, Quantity: 2, Unit price: 3 (yuan), Subtotal: 6 (yuan)\n" +
                "Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)\n" +
                "----------------------\n" +
                "Total: 24 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.printReceipt(loadBarcodes()));
    }

    @Test void more_items_receipt() {
        PosMachine posMachine = new PosMachine();
        List<String> testBarcodes = Arrays.asList("ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004"
            ,"ITEM000000", "ITEM000001", "ITEM000001");
        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 5, Unit price: 3 (yuan), Subtotal: 15 (yuan)\n" +
                "Name: Sprite, Quantity: 4, Unit price: 3 (yuan), Subtotal: 12 (yuan)\n" +
                "Name: Battery, Quantity: 3, Unit price: 2 (yuan), Subtotal: 6 (yuan)\n" +
                "----------------------\n" +
                "Total: 33 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.printReceipt(testBarcodes));
    }

    @Test
    public void one_item_receipt() {
        PosMachine posMachine = new PosMachine();
        List<String> testBarcodes = Arrays.asList("ITEM000000");
        String expected = "***<store earning no money>Receipt***\n" +
                "Name: Coca-Cola, Quantity: 1, Unit price: 3 (yuan), Subtotal: 3 (yuan)\n" +
                "----------------------\n" +
                "Total: 3 (yuan)\n" +
                "**********************";

        assertEquals(expected, posMachine.printReceipt(testBarcodes));
    }

    @Test
    public void should_return_error() {
        PosMachine posMachine = new PosMachine();
        List<String> testBarcodes = Arrays.asList("ITEM000000", "ITEM000003");

        Error error = assertThrows(Error.class, () -> posMachine.printReceipt(testBarcodes));
        assertEquals("Some barcode does not exist in database", error.getMessage());
    }


    private static List<String> loadBarcodes() {
        return Arrays.asList("ITEM000000", "ITEM000000", "ITEM000000", "ITEM000000", "ITEM000001", "ITEM000001", "ITEM000004", "ITEM000004", "ITEM000004");
    }
}
