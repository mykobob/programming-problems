import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution405A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[] nums = new int[N];
        String[] str = in.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
