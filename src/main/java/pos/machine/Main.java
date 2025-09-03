package pos.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入数组长度:");
        int n = Integer.parseInt(reader.readLine());
        List<String> barcodes = new ArrayList<>();
        System.out.println("请输入数组元素:");
        for (int i = 0; i < n; i++) {
            barcodes.add(reader.readLine());
        }
        PosMachine posMachine = new PosMachine();
        String printReceipt = posMachine.printReceipt(barcodes);
        System.out.println(printReceipt);
    }
}
