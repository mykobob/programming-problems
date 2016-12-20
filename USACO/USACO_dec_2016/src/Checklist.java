import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Checklist {
    public static void main(String... bob) throws Exception {
        solveDp();
//        solveRecursive();
    }

    private static void solveRecursive() throws FileNotFoundException {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int H = in.nextInt(), G = in.nextInt();
        int[][] hols = new int[H][2];
        int[][] guers = new int[G][2];
        for (int i = 0; i < H; i++) {
            hols[i][0] = in.nextInt();
            hols[i][1] = in.nextInt();
        }

        for (int i = 0; i < G; i++) {
            guers[i][0] = in.nextInt();
            guers[i][1] = in.nextInt();
        }

        long ans = solve(hols, 1, guers, 0, hols[0]);
        System.out.println(ans);
        out.writeln(String.valueOf(ans));
        out.close();
    }

    private static Map<String, Long> memo = new HashMap<>();
    private static long solve(int[][] hols, int h, int[][] guers, int g, int[] cur) {
        if (h == hols.length) {
            if (g == guers.length) {
                return 0;
            } else {
                return -1;
            }
        }
//        int key = h * 2000 + g;
        String key = h + " " + g + " " + Arrays.toString(cur);
        Long tmp2 = null;
        if (memo.containsKey(key)) {
            tmp2 = memo.get(key);
            return tmp2;
        }

        long ans = Long.MAX_VALUE;
        if (h < hols.length) {
            long tmp = solve(hols, h + 1, guers, g, hols[h]);
            if (tmp != -1) {
                tmp += dist(cur, hols[h]);
                ans = Math.min(ans, tmp);
            }
        }

        if (g < guers.length) {
            long tmp = solve(hols, h, guers, g + 1, guers[g]);
            if (tmp != -1) {
                tmp += dist(cur, guers[g]);
                ans = Math.min(ans, tmp);
            }
        }
        memo.put(key, ans);

        return ans;
    }


    public static void solveDp() throws FileNotFoundException {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int H = in.nextInt(), G = in.nextInt();
        int[][] hols = new int[H][2];
        int[][] guers = new int[G][2];
        for (int i = 0; i < H; i++) {
            hols[i][0] = in.nextInt();
            hols[i][1] = in.nextInt();
        }

        for (int i = 0; i < G; i++) {
            guers[i][0] = in.nextInt();
            guers[i][1] = in.nextInt();
        }

        long[][] dp = new long[G + H][H];
        for (int i = 0; i < G; i++) {
            for (int j = 0; j < H; j++) {
                dp[i][j] = Long.MAX_VALUE / 2;
            }
        }
        dp[0][0] = 0; // for H
        for (int i = 1; i < G; i++) {
            dp[i][0] = dp[i - 1][0] + dist(guers[i], guers[i-1]);
        }
        for (int i = 1; i < H; i++) {
            dp[0][i] = dp[0][i - 1] + dist(hols[i], hols[i-1]);
        }

        for (int i = 1; i < G; i++) {
            for (int j = 1; j < H; j++) {
                long a = dp[i][j - 1] + dist(guers[i], hols[j - 1]);
                long b = dp[i][j - 1] + dist(hols[j], hols[j - 1]);
                long c = dp[i - 1][j] + dist(guers[i], guers[i - 1]);
                long d = dp[i - 1][j] + dist(hols[j], guers[i - 1]);
                dp[i][j] = Math.min(a, Math.min(b, Math.min(c, d)));
            }
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[G-1][H-1]);


        out.close();
    }

    private static long dist(int[] cur, int[] other) {
        long xs = Math.abs(other[0] - cur[0]);
        long ys = Math.abs(other[1] - cur[1]);
        long dist = xs * xs + ys * ys;
        return dist;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
            } catch (Exception e) {

            }
        }

        public void caseInt(int num, int ans) {
            caseStr(num, String.valueOf(ans));
        }

        public void caseStr(int num, String ans) {
            out.printf("Case #%d: %s\n", num, ans);
        }

        public void writeln(String str) {
            out.println(str);
        }

        public void write(String str) {
            out.print(str);
        }

        public void close() {
            out.close();
        }
    }

    static class FAST {
        private BufferedReader in;
        private StringTokenizer str;

        public FAST() throws FileNotFoundException {
//            in = new BufferedReader(new InputStreamReader(System.in));
             in = new BufferedReader(new FileReader("checklist.in"));
        }

        private String next() {
            while (str == null || !str.hasMoreElements()) {
                try {
                    str = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                }
            }
            return str.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() throws Exception {
            return in.readLine();
        }
    }
}
