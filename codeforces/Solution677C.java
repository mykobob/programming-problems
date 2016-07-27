import java.io.*;
import java.util.StringTokenizer;

public class Solution677C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        String str = in.nextLine();
        long MOD = (long) (1e9 + 7);
        long ans = 1;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int zeroBits = zeroBits(ch);
            long localAns = (long) Math.pow(3, zeroBits);
            ans *= localAns;
            ans %= MOD;
        }
        System.out.println(ans);

        out.close();
    }

    private static int zeroBits(char ch) {
        int val = getVal(ch);
        int bitCount = Integer.bitCount(val);
        return 6 - bitCount;
    }

    private static int getVal(char ch) {
        if (Character.isDigit(ch)) {
            return ch - '0';
        }
        if (Character.isUpperCase(ch)) {
            return ch - 'A' + 10;
        }
        if (Character.isLowerCase(ch)) {
            return ch - 'a' + 36;
        }
        if (ch == '-') {
            return 62;
        } else {
            return 63;
        }
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution677C.out")));
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
            // in = new BufferedReader(new FileReader("Solution677C.in"));
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
