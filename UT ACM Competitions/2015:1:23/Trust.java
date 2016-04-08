import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Michael on 1/23/15.
 */
public class Trust {
    public static void main(String... bob) throws Exception {
//        Scanner in = new Scanner(new File("trust.txt"));
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("trust.out");
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int numBags = in.nextInt();
            int totalMoney = in.nextInt();
            int min = in.nextInt();
            int max = in.nextInt();

            int minMoney = min * numBags;
            int maxMoney = max * numBags;
            System.out.printf("%d %d %d %d ", numBags, totalMoney, minMoney, maxMoney);
            System.out.println(minMoney <= totalMoney && totalMoney <= maxMoney ? "NO" : "YES");
            out.println(minMoney <= totalMoney && totalMoney <= maxMoney ? "NO" : "YES");
        }
        out.close();
        System.exit(0);
    }
}
