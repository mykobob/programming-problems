import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 4/10/15.
 */
public class Infinite {
    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(new File("infinite.in"));
        Scanner in = new Scanner(new File("B-small-attempt2.in"));
        PrintWriter out = new PrintWriter("infinite.out");
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int numPpl = in.nextInt();
            in.nextLine();
            String[] strData = in.nextLine().split(" ");
            List<Integer> data = new ArrayList<>();
            for (int j = 0; j < numPpl; j++) {
                data.add(Integer.parseInt(strData[j]));
            }
            memo = new HashMap<>();
//            if(i == 3) {
//                int ans = getBestTime(data, 0);
//                System.out.println(ans);
//            } else {
//                int ans = getBestTime(data, 0);
//                System.out.println(ans);
//            }
//            System.out.println(memo);
            int ans = getBestTime(data, 0);
            System.out.printf("Case #%d: %d\n", i + 1, ans);
            out.printf("Case #%d: %d\n", i + 1, ans);
        }
        out.close();
    }

    static Map<String, Integer> memo;

    public static int getBestTime(List<Integer> cakes, int time) {
//        if(time == 50) {
//            System.exit(0);
//        }
        String key = cakes.toString();
//        if(memo.containsKey(key)) {
//            return memo.get(key);
//        }
        if(allZero(cakes)) {
//            System.out.println();
            return time;
        }
//        System.out.println(cakes);
        int best = 1 << 25;
        List<Integer> orig = new ArrayList<>(cakes);
        addNum(cakes, -1);
        int tmp = getBestTime(cakes, time + 1);
        best = Math.min(best, tmp);
        cakes = new ArrayList<>(orig);

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < cakes.size(); i++) {
            int num = cakes.get(i);
            if(seen.add(num)) {
                int upper = (int)Math.sqrt(num);
                for (int divisor = 2; divisor <= upper; divisor++) {
                    if(num % divisor == 0) {
                        int quotient = num/divisor;
                        for (int k = quotient; k < num; k+=quotient) {
                            int offset = k;
//                            if(num - offset == 0) {
//                                System.out.println("Wtf");
//                                System.out.println(offset);
//                            }
                            cakes.add(num - offset);
                            cakes.set(i, offset);
                            int timeForSubproblem = getBestTime(cakes, time + 1);
                            best = Math.min(best, time + timeForSubproblem);
                            cakes.remove(cakes.size() - 1);
                            cakes.set(i, num);
                        }
                    }
                }
//                if (num >= 2 && num % 2 == 0) {
//                    // only optimal to split in half
//                    cakes.add(num / 2);
//                    cakes.set(i, num / 2);
//                    int timeForSubproblem = getBestTime(cakes, time + 1);
//                    best = Math.min(best, time + timeForSubproblem);
//                    cakes.remove(cakes.size() - 1);
//                    cakes.set(i, num);
//                }
            }
//            for (int j = 2; j < Math.sqrt(num); j++) {
//                if(num % j == 0) {
//                    cakes.add(num - j);
//                    for (int k = j; k < num; k+=j) {
//                        cakes.set(i, k);
//                        cakes.set(cakes.size() - 1, num - k);
//                        best = Math.max(best, getBestTime(cakes, time + 1));
//                    }
//                    cakes.set(i, num);
//                    cakes.remove(cakes.size() - 1);
//                }
//            }
        }

//        memo.put(key, best);

        return best;
    }

    public static void addNum(List<Integer> cakes, int val) {
        for (int i = 0; i < cakes.size(); i++) {
            int newVal = cakes.get(i) + val;
            if(newVal >= 0) {
                cakes.set(i, newVal);
            }
        }
    }

    public static boolean allZero(List<Integer> cakes) {
        for (int i = 0; i < cakes.size(); i++) {
            if(cakes.get(i) != 0)
                return false;
        }
        return true;
    }
}
