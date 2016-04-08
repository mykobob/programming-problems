import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Balance {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Balance"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken());
            int[] vals = new int[N];
            data = new StringTokenizer(in.readLine());
            long avg = 0;
            for (int j = 0; j < N; j++) {
                vals[j] = i(data.nextToken());
                avg += vals[j];
            }
            avg /= N;
            int move = 0;
            int remove = 0;
            for (int j = 0; j < N; j++) {
                remove += (avg - vals[j]);
                if (avg > vals[j]) {
                    move += Math.abs(avg - vals[j]);
                }
            }
            System.out.printf("%d %d\n", Math.abs(remove), move);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
