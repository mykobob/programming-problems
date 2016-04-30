import java.io.*;
import java.util.StringTokenizer;

public class Solution665C {
    public static void main(String... bob) throws Exception {
        FAST sonic = new FAST();
        WriteMe out = new WriteMe();
        String str = sonic.nextLine();
        int N = str.length();
        StringBuilder ans = new StringBuilder(str);
        if (str.length() == 1) {
            System.out.println(str);
        } else {
            int index = 0;
            while (index < N) {
                int tmp = index;
                while (tmp < N && ans.charAt(tmp) == ans.charAt(index)) {
                    ++tmp;
                }
                char replace = 'a';
                while (replace == ans.charAt(index) || (index > 0 && replace == ans.charAt(index - 1)) || (tmp < N && replace == ans.charAt(tmp))) {
                    // borders and my actual character
                    ++replace;
                }
                for (int i = index + 1; i < tmp; i+=2) {
                    ans.setCharAt(i, replace);
                }
                index = tmp;
            }
            System.out.println(ans);
        }

        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution665C.out")));
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
            // in = new BufferedReader(new FileReader("Solution665C.in"));
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
