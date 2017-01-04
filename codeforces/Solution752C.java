import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution752C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        String instructions = in.nextLine();

        Map<Character, Integer> dirCounts = resetMap();
        int sequenceCount = 0;

        for (int i = 0; i < T; i++) {
            char cur = instructions.charAt(i);
            if (dirCounts.get(pair(cur)) != 0) {
                sequenceCount++;
//                System.out.println(i + " " + cur);
                dirCounts = resetMap();
            }

            dirCounts.compute(cur, (k, v) -> v + 1);
        }
        System.out.println(sequenceCount + 1);


        out.close();
    }

    private static Map<Character, Integer> resetMap() {
        Map<Character, Integer> dirCounts = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            dirCounts.put(orig[i], 0);
        }
        return dirCounts;
    }

    static char[] orig = {'U', 'D', 'R', 'L'};
    static char[] pair = {'D', 'U', 'L', 'R'};

    private static char pair(char ch) {
        for (int i = 0; i < 4; i++) {
            if (orig[i] == ch) {
                return pair[i];
            }
        }
        return 'a';
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution752C.out")));
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
            // in = new BufferedReader(new FileReader("Solution752C.in"));
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
