import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Heist {
    public static void main(String... bob) throws Exception {
//        Scanner in = new Scanner(new File("heist.txt"));
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("heist.out");
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            memo = new HashMap<String, Integer>();
            int times = in.nextInt();
            int numItems = in.nextInt();
            int[] lower = new int[times];
            int[] higher = new int[times];
            for (int j = 0; j < times; j++) {
                lower[j] = in.nextInt();
                higher[j] = in.nextInt();
            }

            Item[] items = new Item[numItems];
            for (int j = 0; j < numItems; j++) {
                items[j] = new Item(in.nextInt(), in.nextInt(), j);
            }
            int ans = solve(items, lower, higher, 0, 0, 0);
            System.out.println(ans);
            out.println(ans);
//            out.println(memo);
        }
        out.close();
        System.exit(0);
    }

    static Map<String, Integer> memo = new HashMap<String, Integer>();

    public static int solve(Item[] items, int[] lower, int[] higher, int index, int totalValue, int timesUsed) {
        if (index == lower.length) {
            return totalValue;
        }

        String tmp = index + " " + Integer.toBinaryString(timesUsed);
        if (memo.containsKey(tmp)) {
            return memo.get(tmp);
        }

        int best = -(1 << 25);

        for (Item item : items) {
            if (!item.picked) {
                for (int i = 0; i < lower.length; i++) {
                    int low = lower[i];
                    int high = higher[i];
                    if(((timesUsed >> i) & 1) == 0) {
                        if (high - low > item.length) {
                            int value = solve(items, lower, higher, index + 1, totalValue, timesUsed);
                            item.picked = true;
                            timesUsed ^= 1 << i;
                            value = Math.max(value, solve(items, lower, higher, index + 1, totalValue + item.value, timesUsed));
                            best = Math.max(best, value);
                            timesUsed ^= 1 << i;
                            item.picked = false;
                        }
                    }
                }
            }
        }
        best = best == -(1 << 25) ? 0 : best;
        memo.put(tmp, best);
        return best;
    }

    static class Item {
        int length;
        int value;
        boolean picked;
        int index;

        public Item(int l, int v, int i) {
            length = l;
            value = v;
            picked = false;
            index = i;
        }

    }

}
