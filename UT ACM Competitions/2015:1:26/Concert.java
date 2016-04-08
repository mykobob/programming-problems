import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Concert {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Concert"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int R = i(data.nextToken()), C = i(data.nextToken());
            data = new StringTokenizer(in.readLine());
            int r = i(data.nextToken()), c = i(data.nextToken());
            int[][] nums = new int[R][C];
            for (int j = 0; j < R; j++) {
                data = new StringTokenizer(in.readLine());
                for (int k = 0; k < C; k++) {
                    nums[j][k] = i(data.nextToken());
                }
            }
            System.out.println(val(nums, r, c));
        }
    }

    public static int val(int[][] nums, int r, int c) {
        if (!inBounds(nums, r, c)) {
            return 0;
        }
        if (nums[r][c] == 0) {
            return 0;
        }
//        System.out.println(r + " " + c);
        int val = nums[r][c];
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                nums[r][c] = 0;
                val += val (nums, r + dr, c + dc) ;
            }
        }
        return val;
    }

    public static boolean inBounds(int[][] nums, int r, int c) {
        return r >= 0 && r < nums.length && c >= 0 && c < nums[r].length;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
