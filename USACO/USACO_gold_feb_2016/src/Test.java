import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Test {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Test.out")));
        // BufferedReader in = new BufferedReader(new FileReader("Test.in"));
//        StringTokenizer testCases = new StringTokenizer(in.readLine());
        String[] str = "spots(1,5,24) spots(2,1,18) spots(2,2,16) spots(2,3,20) spots(2,4,23) spots(3,3,12) spots(3,4,29) spots(3,5,30) spots(3,6,27) spots(4,1,14) spots(4,4,5) spots(4,6,31) spots(5,2,7) spots(5,3,36) spots(5,6,3) spots(6,1,9) spots(6,4,34) spots(6,5,1) spots(6,6,2) spots(5,5,4) spots(4,3,6) spots(6,2,8) spots(5,1,10) spots(4,2,11) spots(3,2,13) spots(3,1,15) spots(1,1,17) spots(1,2,19) spots(1,4,21) spots(1,3,22) spots(1,6,25) spots(2,6,26) spots(2,5,28) spots(4,5,32) spots(5,4,33) spots(6,3,35)".split(" ");
        int n = 6;
        int[][] num = new int[n][n];
        for (String s : str) {
            String[] tmp = s.substring(6, s.length() - 1).split(",");
            num[i(tmp[0]) - 1][i(tmp[1]) - 1] = i(tmp[2]);
        }
        for (int[] ints : num) {
            for (int anInt : ints) {
                System.out.printf("%2d ", anInt);
            }
            System.out.println();
        }
        pursue(5, 4, num);
    }

    public static void pursue(int r, int c, int[][] nums) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int rr = r + dr, cc = c + dc;
                if (rr < 0 || rr >= nums.length || cc < 0 || cc >= nums[rr].length) {
                    continue;
                }
                if (nums[rr][cc] != nums[r][c] + 1) {
                    continue;
                }
                System.out.printf("%d -> (%d, %d)\n", nums[r][c], r, c);
                pursue(rr, cc, nums);
                return;
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
