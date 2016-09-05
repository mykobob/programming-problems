import java.io.*;
import java.util.StringTokenizer;

public class Solution709C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        String str = in.nextLine();
        StringBuilder ans = new StringBuilder(str);
        int startIndex = 0;
        while (startIndex < ans.length() && ans.charAt(startIndex) == 'a') {
            startIndex++;
        }
        boolean changed = false;
        for (int i = startIndex; i < ans.length(); i++) {
            if (str.charAt(i) == 'a') {
                break;
            }
            changed = true;
            ans.setCharAt(i, (char) (ans.charAt(i) - 1));
        }
        if (!changed) {
            ans.setCharAt(ans.length() - 1, shift(ans.charAt(ans.length() - 1)));
        }
        System.out.println(ans);
        out.close();
    }

    public static char shift(char ch) {
        return ch - 1 < 'a' ? 'z' : (char) (ch - 1);
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution709C.out")));
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
            // in = new BufferedReader(new FileReader("Solution709C.in"));
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
