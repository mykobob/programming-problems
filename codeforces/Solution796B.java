import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution796B {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt(), M = in.nextInt(), k = in.nextInt();
        Set<Integer> holes = new HashSet<>();
        for (int i = 0; i < M; i++) {
            holes.add(in.nextInt());
        }
        int[][] swaps = new int[k][2];
        for (int i = 0; i < k; i++) {
            swaps[i][0] = in.nextInt();
            swaps[i][1] = in.nextInt();
        }
        int bone = 1;
        for (int i = 0; i < k; i++) {
            if (holes.contains(bone)) {
                break;
            }
            if (swaps[i][0] == bone || swaps[i][1] == bone) {
                bone = swaps[i][0] + swaps[i][1] - bone;
            }
        }
        System.out.println(bone);
        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution796B.out")));
            } catch (Exception e) {

            }
        }

        public void caseAns(int num, int ans) {
            caseAns(num, String.valueOf(ans));
        }

        public void caseAns(int num, String ans) {
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

        public FAST() throws Exception {
            in = new BufferedReader(new InputStreamReader(System.in));
            // in = new BufferedReader(new FileReader("Solution796B.in"));
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
