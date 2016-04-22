import java.io.*;
import java.util.*;

public class RankAndFile {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            Map<Integer, Integer> counts = new HashMap<>();
            for (int j = 0; j < 2 * N - 1; j++) {
                for (int k = 0; k < N; k++) {
                    int next = in.nextInt();
                    if (!counts.containsKey(next)) {
                        counts.put(next, 0);
                    }
                    counts.put(next, counts.get(next) + 1);
                }
            }
            List<Integer> missing = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() % 2 == 1) {
                    missing.add(entry.getKey());
                }
            }
            Collections.sort(missing);
            StringBuilder ans = new StringBuilder();
            ans.append(missing.get(0));
            for (int j = 1; j < missing.size(); j++) {
                ans.append(" ");
                ans.append(missing.get(j));
            }
            out.caseStr(i + 1, ans.toString());
        }

        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("RankAndFile.out")));
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
             in = new BufferedReader(new FileReader("B-large-practice.in"));
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

        public String nextLine() throws IOException {
            return in.readLine();
        }
    }
}
