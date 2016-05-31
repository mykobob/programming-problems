import java.io.*;
import java.util.StringTokenizer;

public class Solution670D2 {

    static int N;
    static int K;
    static int[] needed;
    static int[] have;

    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        N = in.nextInt();
        K = in.nextInt();
        long left = 0;
        long right = Integer.MAX_VALUE;
        needed = new int[N];
        for (int i = 0; i < N; i++) needed[i] = in.nextInt();
        have = new int[N];
        for (int i = 0; i < N; i++) {
            have[i] = in.nextInt();
        }

        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (valid(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);


        out.close();
    }

    public static boolean valid(long amount) {
        long extra = K;
        for (int i = 0; i < N; i++) {
            long need = amount * needed[i] - have[i];
            if (need > 0) {
                extra -= need;
                if (extra < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution670D2.out")));
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
            // in = new BufferedReader(new FileReader("Solution670D2.in"));
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
