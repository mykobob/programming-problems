import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 1/8/15.
 */
public class Resolution {
    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("resolution2.txt"));
        PrintWriter out = new PrintWriter("resolution2.out");
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            visited = new HashSet<>();
            int[] target = {in.nextInt(), in.nextInt(), in.nextInt()};
            int numItems = in.nextInt();
            Item[] items = new Item[numItems];
            for (int j = 0; j < numItems; j++) {
                items[j] = new Item(in.nextInt(), in.nextInt(), in.nextInt());
            }
            boolean solve = solve(0, target, items);
            System.out.printf("Case #%d: %s\n", i + 1, solve ? "yes" : "no");
//            System.out.println(solve(0, target, items));
            out.printf("Case #%d: %s\n", i + 1, solve ? "yes" : "no");
        }
        out.close();
    }

    public static boolean solve(int index, int[] total, Item[] items) {
        if(total[0] == 0 && total[1] == 0 && total[2] == 0)
            return true;
        if(index == items.length) {
            return false;
        }
        String state = index + " " + Arrays.toString(total);
        if(visited.contains(state))
            return true;
        boolean solve = solve(index + 1, total, items);
        if(solve)
            return solve;
        Item check = items[index];
        for (int i = 0; i < 3; i++) {
            total[i] -= check.args[i];
        }
        solve = solve(index + 1, total, items);
        for (int i = 0; i < 3; i++) {
            total[i] += check.args[i];
        }
        return solve;
    }

    static Set<String> visited;

    static class Item {
        int[] args;
        public Item(int p, int c, int f) {
            args = new int[3];
            args[0]= p;
            args[1] = c;
            args[2] = f;
        }
    }
}
