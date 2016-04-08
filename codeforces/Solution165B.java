import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution165B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());
        int low = 0, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(n, mid, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
//            System.out.println();
        }
//        System.out.println(low + " " + high);
        System.out.println(low);
    }

    public static boolean check(int n, int v, int k) {
        long deno = 1;
        long sum = 0;
        while (v / deno != 0) {
            sum += v / deno;
            deno *= k;
//            System.out.println(sum);
        }
        return sum >= n;
    }
}
