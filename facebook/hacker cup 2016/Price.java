import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Price {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader in = new BufferedReader(new FileReader("the_price_is_correct.txt"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        BufferedWriter out = new BufferedWriter(new FileWriter("priceOutput.txt"));
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken());
            int P = i(data.nextToken());
            data = new StringTokenizer(in.readLine());
            int[] prices = new int[N];
            long[] prefix = new long[N];
            for (int j = 0; j < N; j++) {
                prices[j] = i(data.nextToken());
                if (j == 0) {
                    prefix[j] = prices[j];
                } else {
                    prefix[j] = prefix[j - 1] + prices[j];
                }
            }
//            System.out.println(Arrays.toString(prices));
//            System.out.println(Arrays.toString(prefix));
            long total = 0;
            for (int j = 0; j < N; j++) {
                int low = j;
                int high = N - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    long sum = prefix[mid] - prefix[j] + prices[j];
                    if (sum < P) { // we can try bigger
                        low = mid + 1;
                    } else if (sum > P) { // we exceeded, have to go smaller
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
//                System.out.println(low + " " + high);
//                System.out.println(low - j);
                total += low - j;
            }
            String ans = String.format("Case #%d: %d\n", i + 1, total);
            System.out.println(ans);
            out.write(ans);
        }
        out.close();
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
