import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LazyLoading {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int numWeights = in.nextInt();
            int[] weights = new int[numWeights];
            for (int j = 0; j < numWeights; j++) {
                weights[j] = in.nextInt();
            }
            Arrays.sort(weights);
            int heavy = weights.length - 1;
            int lightOnes = 0;
            int trips = 0;

            while (lightOnes <= heavy) {
                int requiredItems = (int) Math.ceil(50.0 / weights[heavy]) - 1;
                lightOnes += requiredItems;
                heavy--;
                trips++;
                if (heavy >= 0 && weights[heavy] * (heavy - lightOnes + 1) < 50) {
                    break;
                }
            }
            out.caseAns(i + 1, trips);
        }

        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("LazyLoading.out")));
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

        public FAST() throws FileNotFoundException {
//            in = new BufferedReader(new InputStreamReader(System.in));
             in = new BufferedReader(new FileReader("lazy_loading.txt"));
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
