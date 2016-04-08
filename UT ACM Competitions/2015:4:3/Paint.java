import java.util.Scanner;

/**
 * Created by Michael on 4/3/15.
 */
public class Paint {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        for (int i = 0; i < tests; i++) {
            int N = in.nextInt(), M = in.nextInt(), Q = in.nextInt();
            char[][] mat = new char[N][M];
            for (int j = 0; j < N; j++) {
                String tmp = in.next();
                for (int k = 0; k < M; k++) {
                    mat[j][k] = tmp.charAt(k);
                }
            }
            for (int j = 0; j < Q; j++) {
                int r = in.nextInt(), c = in.nextInt();
                char compare = in.next().charAt(0);
                if(mat[r][c] != compare) {
                    flood(mat, r, c, mat[r][c], compare);
                }
//                for (int k = 0; k < N; k++) {
//                    System.out.println(mat[k]);
//                }
            }
            for (int j = 0; j < N; j++) {
                System.out.println(mat[j]);
            }
//            System.out.println();
        }
    }

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void flood(char[][] tmp, int r, int c, char replaced, char compare) {
        if(!inBounds(tmp, r, c)) {
            return;
        }
        char thisOne = tmp[r][c];
        if(thisOne != replaced) {
            return;
        }
        tmp[r][c] = compare;
        for (int i = 0; i < 4; i++) {
            flood(tmp, r + dr[i], c + dc[i], replaced, compare);
        }
    }

    public static boolean inBounds(char[][] mat, int r, int c) {
        return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
    }
}
