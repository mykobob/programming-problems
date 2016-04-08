import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution377A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] data = in.readLine().split(" ");
        int N = i(data[0]), M = i(data[1]), K = i(data[2]);
        char[][] mat = new char[N][M];
        for (int i = 0; i < N; i++) {
            mat[i] = in.readLine().toCharArray();
        }
        int r = -1, c = -1;
        int total = 0;
        dance:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == '.') {
                    r = i;
                    c = j;
                    total++;
                }
            }
        }
//        System.out.println(r + " " + c);
        cover = total - K;
//        System.out.println(cover);
        recur(mat, r, c);
//        printMat(mat);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mat[i][j] == '@') {
                    mat[i][j] = '.';
                } else if (mat[i][j] == '.') {
                    mat[i][j] = 'X';
                }
            }
        }
        printMat(mat);
    }

    public static void printMat(char[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int cover = 0;
    public static void recur(char[][] mat, int r, int c) {
        if (r < 0 || r >= mat.length || c < 0 || c >= mat[r].length) {
            return;
        }
        if (cover == 0) {
            return;
        }
        if (mat[r][c] == '#' || mat[r][c] == '@') {
            return;
        }
        mat[r][c] = '@';
        cover--;
//        printMat(mat);
//        System.out.println();
//        System.out.println(cover + " " + r + " " + c);
        for (int i = 0; i < 4; i++) {
            recur(mat, r + dr[i], c + dc[i]);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
