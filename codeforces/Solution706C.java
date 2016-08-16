import java.io.*;
import java.util.StringTokenizer;

public class Solution706C {
    public static final long MAX = Long.MAX_VALUE / 2;
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt();
        long[] cost = new long[N];
        for (int i = 0; i < N; i++) {
            cost[i] = in.nextInt();
        }
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            strs[i] = in.nextLine();
        }
        long[][] dp = new long[N][2]; // 0 is no reverse and 1 is reversed
        for (int i = 0; i < N; i++) {
            dp[i][0] = dp[i][1] = MAX;
        }

        dp[0][0] = 0;
        dp[0][1] = cost[0];
        boolean failed = false;
        for (int i = 1; i < N; i++) {
            String prev = strs[i - 1];
            String test = strs[i];
            if (!after(prev, false, test, false)) { // prev before test
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][0]);
            }
            if (!after(prev, false, test, true)) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][0] + cost[i]);
            }
            if (!after(prev, true, test, false)) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 1][1]);
            }
            if (!after(prev, true, test, true)) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 1][1] + cost[i]);
            }
            if (dp[i][0] == MAX && dp[i][1] == MAX) {
                failed = true;
                break;
            }
        }
        if (failed) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(dp[N - 1][0], dp[N - 1][1]));
        }

        out.close();
    }

    public static boolean after(String a, boolean aReverse, String b, boolean bReverse) {
        int aIndex = 0, aIncr = 1, aEnd = a.length();
        if (aReverse) {
            aIndex = a.length() - 1;
            aIncr = -1;
            aEnd = -1;
        }

        int bIndex = 0, bIncr = 1, bEnd = b.length();
        if (bReverse) {
            bIndex = b.length() - 1;
            bIncr = -1;
            bEnd = -1;
        }

        while (aIndex != aEnd && bIndex != bEnd) {
            if (a.charAt(aIndex) > b.charAt(bIndex)) {
                return true;
            } else if (a.charAt(aIndex) < b.charAt(bIndex)) {
                return false;
            } else {
                aIndex += aIncr;
                bIndex += bIncr;
            }
        }
        return aIndex != aEnd;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution706C.out")));
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

        public FAST() {
            in = new BufferedReader(new InputStreamReader(System.in));
            // in = new BufferedReader(new FileReader("Solution706C.in"));
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
