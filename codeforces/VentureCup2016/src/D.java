import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("D"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        int[] nums = new int[N];
        StringTokenizer data = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = i(data.nextToken());
        }

    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
