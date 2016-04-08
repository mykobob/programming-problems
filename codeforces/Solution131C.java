import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution131C {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution131C"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int boys = i(testCases.nextToken()), girls = i(testCases.nextToken()), t = i(testCases.nextToken());
        long[][] combo = new long[boys + girls + 1][boys + girls + 1];
        for (int i = 0; i < combo.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    combo[i][j] = 1;
                } else {
                    combo[i][j] = combo[i - 1][j] + combo[i - 1][j - 1];
                }
//                System.out.print(combo[i][j] + " ");
            }
//            System.out.println();
        }
        long total = 0;
        for (int i = 4; i < t; i++) {
            total += combo[boys][i] * combo[girls][t - i];
        }
        System.out.println(total);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
