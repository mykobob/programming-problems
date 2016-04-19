import java.io.*;
import java.util.StringTokenizer;

public class LastWord {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String str = in.nextLine();
            StringBuilder ans = new StringBuilder();
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (ans.length() == 0) {
                    ans.append(ch);
                } else {
                    char begin = ans.charAt(0);
                    char end = ans.charAt(j - 1);
                    if (ch < begin) {
                        ans.append(ch);
                    } else {
                        ans.insert(0, ch);
                    }
                }
            }
            out.caseStr(i + 1, ans.toString());
        }
        out.close();

    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("LastWord.out")));
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
//            in = new BufferedReader(new InputStreamReader(System.in));
            try {
                in = new BufferedReader(new FileReader("A-large-practice.in"));
            } catch (FileNotFoundException e) {}
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
