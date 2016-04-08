import java.util.Scanner;

/**
 * Created by Michael on 5/17/15.
 */
public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt(), B = in.nextInt(), MOD = in.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = in.nextInt();
        }

        int[][][] dp = new int[N][M][B];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < B; j++) {
                dp[0][i][j] = 1;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < B; k++) {
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < j; m+=A[i]) {
                            dp[i][j][k] += dp[i-1][l][m] % MOD;
                        }
                    }
                }
            }
        }
        System.out.println(dp[N-1][M-1][B-1]);

    }
}
