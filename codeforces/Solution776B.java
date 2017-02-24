import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution776B {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        boolean[] isPrime = sieve(T);
//        System.out.println(Arrays.toString(isPrime));
        printAnswers(isPrime);
        out.close();
    }

    private static void printAnswers(boolean[] isPrime) {
//        int[] colors = new int[isPrime.length];
//        for (int i = 2; i < isPrime.length; i++) {
//            if (isPrime[i]) {
//                for (int j = i; j * j < isPrime.length; j++) {
//                    colors[i*j] += 1;
//                }
//            }
//        }
        StringBuilder out = new StringBuilder();
        int prime = 0, composite = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                out.append(1);
                prime |= 1;
            } else {
                out.append(2);
                composite |= 1;
            }
            out.append(" ");
        }
        System.out.println(prime + composite);
        System.out.println(out);
    }

    private static boolean[] sieve (int T) {
        boolean[] isPrime = new boolean[T + 2];
        for (int i = 2; i < T + 2; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i*i < T + 2; i++) {
            if (isPrime[i]) {
                for (int j = i; i*j < T + 2; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        return isPrime;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution776B.out")));
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
            // in = new BufferedReader(new FileReader("Solution776B.in"));
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
