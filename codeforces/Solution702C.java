import java.io.*;
import java.util.StringTokenizer;

public class Solution702C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt(), M = in.nextInt();
        int[] cities = new int[N];
        for (int i = 0; i < N; i++) {
            cities[i] = in.nextInt();
        }

        int[] towers = new int[M];
        for (int i = 0; i < M; i++) {
            towers[i] = in.nextInt();
        }

        long low = 0;
        long high = 2000000000L;
        while (low < high) {
            long mid = (low + high) / 2;
            if (works(mid, cities, towers)) {
                // try a smaller r
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low);

        out.close();
    }

    private static boolean works(long range, int[] cities, int[] towers) {
        int cityPtr = 0, towerPtr = 0;
        while (true) {
            if (cityPtr == cities.length) {
                return true;
            }
            if (towerPtr == towers.length) {
                break;
            }
            if (towers[towerPtr] - range <= cities[cityPtr] && cities[cityPtr] <= towers[towerPtr] + range) {
                cityPtr++;
            } else {
                towerPtr++;
            }
        }
        return false;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution702C.out")));
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
            // in = new BufferedReader(new FileReader("Solution702C.in"));
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
