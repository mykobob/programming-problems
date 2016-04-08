import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Spinnas {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Spinnas"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int R = i(testCases.nextToken()), C = i(testCases.nextToken());
        int[][] num = new int[R][C];
        for (int i = 0; i < R; i++) {
            String str = in.readLine();
            for (int j = 0; j < str.length(); j++) {
                num[i][j] = str.charAt(j) - '0';
            }
        }
        int best = Integer.MIN_VALUE;
        int r = -1, c = -1;
//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                int ans = spiral(i, j, num) + 1;
//                if (ans > best) {
//                    best = ans;
//                    r = i;
//                    c = j;
//                }
//            }
//        }
        System.out.println(r + "," + c+ ":" + best);
        System.out.println(spiral(2, 7, num));
    }

    static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};

    static int[] bdr = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] bdc = {1, 1, 1, 0, -1, -1, -1, 0};

    public static int spiral (int r, int c, int[][] num) {
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < 8; i += 2) {
            int ans = helper2(r, c, i, 0, num, num[r][c]);
            if (ans > best) {
                best = ans;
                System.out.println("i2 " + i + " " + best);
            }
            ans = helper(r, c, i, 0, num, num[r][c]);
            if (ans > best) {
                best = ans;
                System.out.println("i1 " + i + " " + best);
            }
        }
        return best;
    }

    public static int helper2 (int r, int c, int index, int len, int[][] num, int compare) {
        if (!inBounds(r, c, num)) {
            return 0;
        }
        System.out.println(r + " " + c + " " + len + " " + index);
        int length = len;
        int moveInd = index - 1 < 0 ? 7 : index - 1;
        for (int i = 0; i < len; i++) {
            if (!inBounds(r, c, num)) {
                return length;
            }
            if (num[r][c] != compare) {
//                System.out.println("bad");
//                System.out.println(num[r][c] + " stuff " + r + " " + c);
//                System.out.println(bdr[moveInd] + " " + bdc[moveInd] + " " + index);
                return length;
            }
            r += bdr[moveInd];
            c += bdc[moveInd];
            length++;
        }
        if (len != 0) {
            r -= bdr[moveInd];
            c -= bdc[moveInd];
        }
        System.out.println(r + " " + c + " " + len);
        return length + helper2(r + bdr[index], c + bdc[index], (index + 2) % 8, len + 1, num, compare);
    }

    public static int helper (int r, int c, int index, int len, int[][] num, int compare) {
        if (!inBounds(r, c, num)) {
            return 0;
        }
        int length = len;
        for (int i = 0; i < len; i++) {
            if (!inBounds(r, c, num)) {
                return length;
            }
            if (num[r][c] != compare) {
                return length;
            }
            r += dr[index + 1];
            c += dc[index + 1];
            length++;
        }
        if (len != 0) {
            r -= dr[index + 1];
            c -= dc[index + 1];
        }
        return length + helper (r + dr[index], c + dc[index], (index + 2) % 8, len + 1, num, compare);
    }

    public static boolean inBounds(int r, int c, int[][] num) {
        return r >= 0 && r < num.length && c >= 0 && c < num[r].length;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
