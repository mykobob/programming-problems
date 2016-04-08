import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution448D {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution448D"));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int N = i(str.nextToken());
        int M = i(str.nextToken());
        long K = l(str.nextToken());

        long low = 0, high = (long)N * M + 1;
        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = getCount(N, M, mid);
//            System.out.println(count + " " + K);
            if (count < K) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println(low);
    }

    private static long getCount(int N, int M, long val) {
//        System.out.printf("%d -> %d, %d\n", val, row, col);
        long count = 0;
        for (int i = 1; i <= N; i++) {
            long lessThan = (val) / i;
            count += Math.min(lessThan, M);
//            System.out.println("lessThan = " + lessThan);
//            System.out.println("count = " + count);
        }
        return count;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }

    public static long l(String str) {
        return Long.parseLong(str);
    }
}
