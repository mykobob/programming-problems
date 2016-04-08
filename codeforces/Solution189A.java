import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution189A {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution189A"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        int a = i(testCases.nextToken());
        int b = i(testCases.nextToken());
        int c = i(testCases.nextToken());
        int[] stuff = {a, b, c};
        int[] dp = new int[N + 1];
        for (int i = 0; i < stuff.length; i++) {
            if (stuff[i] <= N) {
                dp[stuff[i]] = 1;
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < stuff.length; j++) {
                if (i - stuff[j] >= 0) {
                    if (dp[i - stuff[j]] != 0) {
                        dp[i] = Math.max(dp[i], dp[i - stuff[j]] + 1);
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
