import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class D {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        Integer[] nums = new Integer[N];
        String[] tmp = in.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tmp[i]);
        }
        Arrays.sort(nums);

        int[] prefixSum = new int[N - 1];
        prefixSum[0] = nums[0];
        for (int i = 1; i < N - 1; i++) {
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            if(prefixSum[i] <= nums[i + 1]) {
                count++;
            }
        }
//        System.out.println("prefix " + Arrays.toString(prefixSum));
//        System.out.println("nums " + Arrays.toString(nums));
        System.out.println(count + 1);
    }
}
