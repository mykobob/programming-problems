import java.io.*;
import java.util.*;

public class Solution776C {
    public static void main(String...bob) throws Exception {
        FAST in = new FAST();
        int n = in.nextInt();
        int k = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        long[] prefix = new long[n+1];
        for (int i = 1; i < prefix.length;i++) 
            prefix[i] = prefix[i-1] + a[i-1];
        
        Map<Long, Integer> freq = new HashMap<>();
        freq.put(0L, 1);
        long totalLists = 0;
        for (int i = 0; i < n; i++) {
            long curSum = prefix[i+1];
            int loopCount = 0;
            for (long value = 1; value <= 1e15; value *= k) {
                long valueToFind = curSum - value;
                if (freq.containsKey(valueToFind)) {
                    totalLists += freq.get(valueToFind);
                }
                loopCount++;
                if (k == 1 || k == -1) {
                    if (k == -1 && loopCount >= 2) {
                        break;
                    }
                    if (k == 1 && loopCount >= 1) {
                        break;
                    }
                }
            }
            freq.compute(curSum, (key,v) -> v == null ? 1 : v + 1);
        }
        System.out.println(totalLists);

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
