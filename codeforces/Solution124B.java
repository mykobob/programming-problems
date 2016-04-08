import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution124B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution124B"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken()), K = i(testCases.nextToken());
        String[] nums = new String[N];
        for (int i = 0; i < N; i++) {
            nums[i] = in.readLine();
        }
        int[] rule = new int[K];
        int ans = solve (0, new boolean[K], rule, nums);
        System.out.println(ans);
    }

    public static int solve (int index, boolean[] used, int[] rule, String[] nums) {
        if (index == rule.length) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                StringBuilder str = new StringBuilder();
                for (int j = 0; j < rule.length; j++) {
                    str.append(nums[i].charAt(rule[j]));
                }
                int ans = Integer.parseInt(str.toString());
                min = Math.min(min, ans);
                max = Math.max(max, ans);
            }
            return max - min;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < rule.length; i++) {
            if (!used[i]) {
                rule[index] = i;
                used[i] = true;
                ans = Math.min(ans, solve(index + 1, used, rule, nums));
                used[i] = false;
            }
        }
        return ans;
    }



    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
