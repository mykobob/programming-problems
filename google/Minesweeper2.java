import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.Queue;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class Minesweeper2 {
    static char[][] mat, mat2;
    static int[][] countMat, countMat2;

    static PrintWriter out;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("C-small-attempt8.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("Minesweeper.out")));
        int times = in.nextInt();
        for (int ii = 1; ii <= times; ii++) {
            int row = in.nextInt(), col = in.nextInt(), mines = in.nextInt();
            mat = new char[row][col];
            mat2 = new char[row][col];
            countMat = new int[row][col];
            countMat2 = new int[row][col];
            for (int i = 0; i < row; i++) {
                Arrays.fill(mat[i], '.');
                Arrays.fill(mat2[i], '*');
                Arrays.fill(countMat2[i], -1);
            }

            int r = 0;
            int c = 0;
            int count = 0, i = 0;
            while (count < mines) {
                int nr = r + dr[i], nc = c + dc[i];

//                System.out.println("nr = " + nr + " nc = " + nc);
                mat[r][c] = '*';
                r = nr;
                c = nc;
                count++;
            }

        }
    }
}