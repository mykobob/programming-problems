import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution448C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        StringTokenizer strs = new StringTokenizer(in.readLine());
        int[] nums = new int[N];
        int i = 0;
        while(strs.hasMoreTokens()) {
            nums[i] = Integer.parseInt(strs.nextToken());
            i++;
        }


    }
}
