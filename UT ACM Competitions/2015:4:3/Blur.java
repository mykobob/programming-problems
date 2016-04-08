import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Michael on 4/3/15.
 */
public class Blur {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int times = Integer.parseInt(in.readLine());
        for (int i = 0; i < times; i++) {
            String[] tmp = in.readLine().split(" ");
            int N = i(tmp[0]), M = i(tmp[1]), K = i(tmp[2]);
            int[][] mat = new int[N][M];
            int[][] prefixSum = new int[N][M];
            for (int j = 0; j < N; j++) {
                String[] data = in.readLine().split(" ");
                for (int k = 0; k < M; k++) {
                    mat[j][k] = i(data[k]);
                    if(k == 0) {
                        prefixSum[j][k] = mat[j][k];
                    } else {
                        prefixSum[j][k] += prefixSum[j][k - 1] + mat[j][k];
                    }
                }
            }
            for (int j = 0; j < N; j++) {
                System.out.println(Arrays.toString(prefixSum[j]));
            }
            long[][] ans = new long[N][M];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    ans[j][k] = mat[j][k];
                }
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(K != 0) {
                        for (int l = j - K; l <= j + K; l++) {
                            if (l < 0 || l >= N) {
                                continue;
                            }
                            int upper = Math.min(M - 1, k + K - 1);
                            int lower = Math.max(-1, k - K - 1);
                            System.out.println(l + " " + upper);
                            if (lower == -1) {
                                ans[j][k] += prefixSum[l][upper];
//                                System.out.println("HI " + upper);
                            } else {
                                ans[j][k] += prefixSum[l][upper] - prefixSum[l][lower];
//                                System.out.println("HI2");
                            }
                        }
//                        ans[j][k] = Math.round((double) ans[j][k] / ((2 * K + 1) * (2 * K + 1)))
                        ans[j][k] = Math.round((double) ans[j][k]);
                    }
                    System.out.println();
                }
            }

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(ans[j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static boolean inBounds(int r, int c, int N, int M) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
