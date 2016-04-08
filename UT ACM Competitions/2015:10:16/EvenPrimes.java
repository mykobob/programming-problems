import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EvenPrimes {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("EvenPrimes"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            int n = i(in.readLine());
            if (n <= 1) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
