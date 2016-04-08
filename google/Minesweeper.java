import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class Minesweeper {
    static char[][] mat, mat2;
    static int[][] countMat, countMat2;

    static PrintWriter out;

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int[] dr2 = {0, 1, 1};
    static int[] dc2 = {1, 1, 0};

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
            int count = 0, i= 0;
            while(count < mines){
                int nr = r + dr[i], nc = c + dc[i];
                if(outOfBounds(nr, nc) || mat[nr][nc] == '*')
                {
                    i = (i+1)%4;
                    continue;
                }
//                System.out.println("nr = " + nr + " nc = " + nc);
                mat[r][c] = '*';
                r = nr;
                c = nc;
                count++;
            }

            Queue<Point> frontier = new LinkedList<Point>();
            Set<Point> visited = new HashSet<Point>();
            frontier.add(new Point(0,0));
            while (visited.size() < (row*col-mines) && !frontier.isEmpty()) {
                Point next = frontier.poll();
                if(mat2[next.x][next.y] == '.')
                    continue;
                visited.add(next);
                mat2[next.x][next.y] = '.';
//                printMat2();
//                System.out.println();
                for (int jj = 0; jj < 3; jj++) {
                    Point tmp = new Point(next.x + dr2[jj], next.y + dc2[jj]);
                    if(outOfBounds(tmp.x, tmp.y)) {
                        continue;
                    }
                    frontier.add(tmp);
                }
            }

//            System.out.println(tmpMines + " " + frontier);
//            for (int i = 0; i < row; i++) {
//                System.out.println(mat[i]);
//            }
//            System.out.println();
            boolean good = false, good2 = false;
            int goodR = 0, goodC = 0, goodR2 = 0, goodC2 = 0;
            for (int rr = 0;rr<row;rr++) {
                for (int cc = 0; cc < col; cc++) {
                    if(mat[rr][cc] == '*')
                    {
                        countMat[rr][cc] = -1;
                    }
                    else {
                        countMat[rr][cc] = getCount(mat, rr, cc);
                        if (countMat[rr][cc] == 0) {
                            goodR = rr;
                            goodC = cc;
                            good = true;
                        }
                        if (row * col - 1 == mines) {
                            if (mat[rr][cc] != '*') {
                                goodR = rr;
                                goodC = cc;
                            }
                        }
                    }
                }
            }
            for(Point p : visited){
                countMat2[p.x][p.y] = getCount(mat2, p.x, p.y);
                if(countMat2[p.x][p.y] == 0){
                    goodR2 = p.x;
                    goodC2 = p.y;
                    good2 = true;
                }
            }
//            printCount();
//            printCount2();
            System.out.printf("Case #%d:%n", ii);
            out.printf("Case #%d:%n", ii);
            if (good || good2) {
                if(good) {
                    if(touchesZero(countMat)) {
                        mat[goodR][goodC] = 'c';
                        printMat();
                    }
                    else{
                        System.out.println("Impossible1");
                        out.println("Impossible");
                        printMat();
                        printCount();
                    }
                }
                else{
                    if(touchesZero(countMat2)) {
                        mat2[goodR2][goodC2] = 'c';
                        printMat2();
                    }
                    else{
                        System.out.println("Impossible2");
                        out.println("Impossible");
                        printMat2();
                        printCount2();
                    }
                }
//                System.out.printf("%d %d%n", countMines(), mines);
//                if(numMines != mines)
//                    break;
            } else {
                System.out.println("Impossible3");
                out.println("Impossible");
                printMat();
//                System.out.printf("%dx%d %d %d%n", row, col, mines, countMines());
            }
//            printCount();
//            System.out.println();
//            printCount2();
        }
        out.close();
    }

    private static void printMat2() {
        for(char[] ii : mat2){
            System.out.println(ii);
            out.println(ii);
        }
    }

    private static boolean touchesZero(int[][] mat){
        for(int ii = 0;ii<mat.length;ii++){
            outer:
            for (int jj = 0; jj < mat[ii].length; jj++) {
                if(mat[ii][jj] != -1){
                    for(int kk = ii-1;kk<=ii+1;kk++)
                        for (int ll = jj-1; ll <= jj+1; ll++) {
                            if(!outOfBounds(kk, ll))
                                if(mat[kk][ll] == 0)
                                    continue outer;
                        }
                    return false;
                }
            }
        }
        return true;
    }

    private static int getCount(char[][] mat, int r, int c) {
        int count = 0;
        for (int ii = r - 1; ii <= r + 1; ii++)
            for (int jj = c - 1; jj <= c + 1; jj++) {
                if(ii == r && jj == c)
                    continue;
                if (!outOfBounds(ii, jj))
                    count += mat[ii][jj] == '*' ? 1 : 0;
            }
        return count;
    }
    private static void printCount(){
        for(int[] ii : countMat){
            System.out.println(Arrays.toString(ii));
        }
    }
    private static void printCount2(){
        for(int[] ii : countMat2){
            System.out.println(Arrays.toString(ii));
        }
    }
    private static void printMat(){
        for(char[] ch : mat) {
            System.out.println(ch);
            out.println(ch);
        }
    }

    private static boolean outOfBounds(int r, int c) {
        return r < 0 || r >= mat.length || c < 0 || c >= mat[r].length;
    }
    private static int countMines(){
        int num = 0;
        for(char[] ch : mat)
            for(char c : ch)
                if(c == '*')
                    num++;
        return num;
    }
}
