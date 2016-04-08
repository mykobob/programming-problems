import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("C"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken()) * 2, M = i(testCases.nextToken()) * 3;
        int numConflicts = Math.min(N, M) / 6;
        while(numConflicts > 0) {
            if (N <= M) {
                do {
                    N += 2;
                } while (N % 3 == 0);
            } else if (N > M) {
                do {
                    M += 3;
                } while (M % 2 == 0);
            }
            numConflicts--;
        }
        System.out.println(Math.max(N, M));
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
