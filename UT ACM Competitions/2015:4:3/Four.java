import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Michael on 4/3/15.
 */
public class Four {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        in.nextLine();
        for (int i = 0; i < times; i++) {
            int RED = 0, BLUE = 0;
            char[][] mat = new char[6][7];
            int[] lowest = new int[7];
            for (int j = 0; j < 6; j++) {
                String tmp = in.nextLine();
                for (int k = 0; k < 7; k++) {
                    mat[j][k] = tmp.charAt(k);
                    if(mat[j][k] == 'R') {
                        RED++;
                    } else if(mat[j][k] == 'B') {
                        BLUE++;
                    }
                    if(mat[j][k] == '.') {
                        lowest[k] = Math.max(lowest[k], j);
                    }
                }
            }
//            System.out.println(Arrays.toString(lowest));
            char turn = 'B';
            if(RED < BLUE) { // blues turn
                turn = 'R';
            } else if(RED == BLUE) {
                turn = 'R';
            }
            long tmp = getBestScore(mat, lowest, turn);
//            long ans = getBestScore(mat, lowest, turn);
//            System.out.println(tmp);
        }
    }

    public static long getBestScore(char[][] mat, int[] lowest, char check) {
        long best = -1;
        int col = -1;
        for (int i = 0; i < lowest.length; i++) {
            int spot = lowest[i];
            char tmp = mat[spot][i];
            mat[spot][i] = check;
            long tmpScore = getScore(mat, check);
            if(tmpScore > best) {
                best = tmpScore;
                col = i;
            }
            mat[spot][i] = tmp;
        }
        System.out.println(check + " " + (col + 1));
        mat[lowest[col]][col] = check;
//        System.out.println(best);
        for (int i = 0; i < 6; i++) {
            System.out.println(mat[i]);
        }
        return best;
    }

    public static long getScore(char[][] mat, char check) {
        long totalScore = 0;
        for (int i = 0; i < mat.length; i++) { // rows
            for (int j = 0; j < mat[i].length; j++) { // cols
                if(mat[i][j] == check) {
                    int total = search(mat, 0, 1, i, j, check);
                    totalScore += Math.pow(total, 3);
                    j += total - 1;
                }
            }
        }
//        System.out.println(totalScore);

        for (int i = 0; i < mat[0].length; i++) { // cols
            for (int j = 0; j < mat.length; j++) { // rows
                if(mat[j][i] == check) {
                    int total = search(mat, 1, 0, j, i, check);
                    totalScore += Math.pow(total, 3);
                    j += total - 1;
                }
            }
        }
//        System.out.println(totalScore);

        return totalScore;
    }

    public static int search(char[][] mat, int dr, int dc, int r, int c, char check) {
        if(!inBounds(mat, r, c)) {
            return 0;
        }
        if(mat[r][c] != check) {
            return 0;
        }
        return 1 + search(mat, dr, dc, r + dr, c + dc, check);
    }

    public static boolean inBounds(char[][] mat, int r, int c) {
        return r >= 0 && r < mat.length && c >= 0 && c < mat[r].length;
    }
}
