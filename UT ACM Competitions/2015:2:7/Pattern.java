import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Michael on 2/7/15.
 */
public class Pattern {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input (4).txt"));
        PrintWriter out = new PrintWriter("pattern.out");
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            int[][] mat = new int[N][M];
            int K = in.nextInt();
            count = 0;
            int ans = genAllQuilts(mat, 0, 0, K);
            System.out.println(ans);
            out.println(ans);
//            System.out.println("count = " + count);
        }
        out.close();
    }

    static int count = 0;
    private static int genAllQuilts(int[][] mat, int r, int c, int k) {
        if(r == mat.length && c == 0) {
            count++;
            if(isValid(mat)) {
                return 1;
            } else {
                return 0;
            }
        }
        int sum = 0;
        for (int i = 0; i < k; i++) {
            int before = mat[r][c];
            mat[r][c] = i;
            int nextC = c + 1;
            int nextR = r;
            if(nextC == mat[0].length) {
                nextR = r + 1;
                nextC = 0;
            }
            sum += genAllQuilts(mat, nextR, nextC, k);
            mat[r][c] = before;
        }
        return sum;
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    private static boolean isValid(int[][] mat) {
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                for (int k = 0; k < 4; k++) {
                    if(inBounds(r + dr[k], c + dc[k], mat)) {
                        if(mat[r][c] == mat[r + dr[k]][c + dc[k]]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean inBounds(int r, int c, int[][] mat) {
        return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
    }
}
