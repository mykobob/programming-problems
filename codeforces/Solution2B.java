import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution2B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution2B"));
        StringTokenizer sizeStr = new StringTokenizer(in.readLine());
        int n = i(sizeStr.nextToken());
        BigInteger[][] mat = new BigInteger[n][n];
        BigInteger[][] dp = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                mat[i][j] = new BigInteger(data.nextToken());
            }
        }
        dp[0][0] = mat[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1].multiply(mat[0][i]);
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0].multiply(mat[i][0]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                BigInteger top = dp[i - 1][j].multiply(mat[i][j]);
                BigInteger left = dp[i][j - 1].multiply(mat[i][j]);
                int topCount = zeros(top.toString());
                int leftCount = zeros(left.toString());
//                System.out.println(i + " " + j + " -> " + topCount + " " + leftCount);
                if (topCount > leftCount) {
                    dp[i][j] = left;
                } else if (leftCount > topCount){
                    dp[i][j] = top;
                } else {
//                    System.out.println("tie");
                    if (oddCount(top) > (oddCount(left))) {
                        dp[i][j] = top;
                    } else {
                        dp[i][j] = left;
                    }
                }
            }
        }
//        print(dp);
        System.out.println(zeros(dp[n - 1][n - 1].toString()));
        StringBuilder out = new StringBuilder();
        int r = n - 1, c = n - 1;
        while (!(r == 0 && c == 0)) {
            if (r == 0) {
                c--;
                out.insert(0, 'R');
            } else if (c == 0) {
                r--;
                out.insert(0, 'D');
            } else {
                BigInteger result = dp[r][c].divide(mat[r][c]);
                if (result.equals(dp[r - 1][c])) {
                    r--;
                    out.insert(0, 'D');
                } else {
                    c--;
                    out.insert(0, 'R');
                }
            }
        }
        System.out.println(out);
    }

    public static int oddCount(BigInteger num) {
        int count = 0;
        while (num.compareTo(BigInteger.ZERO) != 0) {
            BigInteger[] stuff = num.divideAndRemainder(BigInteger.TEN);
            num = stuff[0];
            if (stuff[1].intValue() % 2 == 1) {
                count++;
            }
        }
        return count;
    }

    public static void print(BigInteger[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.printf("%8s", mat[i][j]);
            }
            System.out.println();
        }
    }

    public static int zeros(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return str.length() - 1 - i;
            }
        }
        return str.length();
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
