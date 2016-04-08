import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Capacity {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Capacity"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken()), K = i(data.nextToken());
            Item[] items = new Item[N];
            for (int j = 0; j < N; j++) {
                data = new StringTokenizer(in.readLine());
                items[j] = new Item(i(data.nextToken()), i(data.nextToken()));
            }
            int[][] dp = new int[N + 1][K + 1];

            for (int n = 1; n <= N; n++) {
                for (int w = 1; w <= K; w++) {

                    // don't take item n
                    int option1 = dp[n-1][w];

                    // take item n
                    int option2 = Integer.MIN_VALUE;
                    if (items[n - 1].size <= w)
                        option2 = items[n - 1].value + dp[n-1][w-items[n - 1].size];

                    // select better of two options
                    dp[n][w] = Math.max(option1, option2);
                }
            }
            System.out.println(dp[N][K]);
        }
    }

    static class Item {
        int size, value;

        public Item(int size, int value) {
            this.size = size;
            this.value = value;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
