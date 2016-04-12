import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CountingSheep {
    public static void main(String... bob) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("CountingSheep.out")));
         BufferedReader in = new BufferedReader(new FileReader("A-large.in"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            int num = i(in.readLine());
            long ans = solve(num);
            out.printf("Case #%d: %s\n", i + 1, ans == -1 ? "INSOMNIA" : ans);
        }
        out.close();
    }

    public static long solve(int num) {
        if (num == 0) {
            return -1;
        }
        boolean[] digits = new boolean[10];
        long add = num;
        int count = 0;
        long times = 0;
        do {
            long tmp = add;
            ++times;
            while (tmp > 0) {
                int digit = (int) (tmp % 10);
                if (!digits[digit]) {
                    ++count;
                    digits[digit] = true;
                }
                tmp /= 10;
            }
            add += num;
        } while (count != 10);
        return times * num;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
