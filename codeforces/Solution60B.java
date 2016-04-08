import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution60B {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt(), N = in.nextInt(), M = in.nextInt();
        char[][][] mat = new char[K][N][M];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                String next = in.next();
                mat[i][j] = next.toCharArray();
            }
        }
        int r = in.nextInt() - 1, c = in.nextInt() - 1;
        int val = dfs(mat, 0, r, c);
        System.out.println(val);
    }

    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[] dr = {-1, 0, 1, 0, 0, 0};
    static int[] dc = {0, -1, 0, 1, 0, 0};

    public static int dfs(char[][][] mat, int h, int r, int c) {
        if (!inBounds(mat, h, r, c)) {
            return 0;
        }
        if (mat[h][r][c] == '#') {
            return 0;
        }
        int val = 1;
        mat[h][r][c] = '#';
        for (int i = 0; i < 6; i++) {
            val += dfs(mat, h + dh[i], r + dr[i], c + dc[i]);
        }
        return val;
    }

    private static boolean inBounds(char[][][] mat, int h, int r, int c) {
        return h >= 0 && h < mat.length && r >= 0 && r < mat[h].length && c >= 0 && c < mat[h][r].length;
    }
}
