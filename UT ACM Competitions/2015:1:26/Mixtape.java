import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mixtape {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Mixtape"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken()), B = i(data.nextToken());
            int[] price = new int[N];
            int[] nums = new int[N];
            data = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                price[j] = i(data.nextToken());
            }
            data = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                nums[j] = i(data.nextToken());
            }
            long val = 0;
            for (int j = 0; j < N; j++) {
                val += price[j] * nums[j];
            }
            System.out.println(val >= B ? "YES" : "NO");
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
