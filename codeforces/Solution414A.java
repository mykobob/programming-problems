import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution414A {

    static List<Integer> primes = new ArrayList<>();

    static {

    }

    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution414A"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken()), K = i(testCases.nextToken());
        int pairs = N / 2;
        if (pairs > K) {
            System.out.println(-1);
        } else {

        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
