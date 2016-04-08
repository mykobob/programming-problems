import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class SolutionB {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("SolutionB"));
        int n = i(in.readLine());
        long mod = (long) (1e9 + 7);
        long ans = 0;
        for (int i = 0; i < n; i++) {
//            ans += (3 * 3 * 3 - 7) * ((3 * n - 3) == 0 ? 1 : (3 * n - 3) * (27) - 20 * (n));
            ans += 20 * (long)Math.pow(3, (3 * n - 3)) - (long)Math.pow(20, n);
            ans %= mod;
        }
        System.out.println((ans + (long)Math.pow(20, n)) % mod);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
