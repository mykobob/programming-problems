import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Pleasant {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Pleasant"));
        Scanner in = new Scanner(System.in);
        StringTokenizer testCases = new StringTokenizer(in.nextLine());
        int R = i(testCases.nextToken());
        int C = i(testCases.nextToken());
        int[][] mat = new int[2 * R + 1][2 * C + 1];
        for (int i = 0; i < R + C + 1; i++) {
            StringTokenizer data = new StringTokenizer(in.nextLine());
            int j = i % 2 == 0 ? 1 : 0;
            while (data.hasMoreTokens()) {
                mat[i][j] = i(data.nextToken());
                j += 2;
            }
        }

        int[][] dp = new int[2 * R + 1][2 * C + 1];
        for (int i = 2; i < mat.length; i+= 2) {
            dp[0][i] = dp[0][i - 2] + mat[0][i - 1];
        }
        for (int i = 2; i < mat[0].length; i+=2) {
            dp[i][0] = dp[i - 2][0] + mat[i - 1][0];
        }
        for (int i = 2; i < mat.length; i+= 2) {
            for (int j = 2; j < mat[i].length; j+= 2) {
                dp[i][j] = Math.max(dp[i-2][j] + mat[i-1][j], dp[i][j - 2] + mat[i][j - 1]);
            }
        }
//        print (dp);
        System.out.println(dp[dp.length - 1][dp[0].length - 1]);
    }

    public static void print (int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
