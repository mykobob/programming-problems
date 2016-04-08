import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AngryCows {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("AngryCows"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        int[] x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = i(in.readLine());
        }
        int[] left = new int[N];
        int[] right = new int[N];
        for (int i = 1; i < N; i++) {
            left[i] = Math.max(left[i-1] + 1, x[i] - x[i-1]);
        }
        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1] + 1, x[i + 1] - x[i]);
        }
        int best = 1 << 30;
        for (int i = 0; i < N; i++) {
            best = Math.min(best, Math.max(left[i], right[i]));
        }
        System.out.println(Arrays.toString(left) + " " + Arrays.toString(right));
        System.out.println(best);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
