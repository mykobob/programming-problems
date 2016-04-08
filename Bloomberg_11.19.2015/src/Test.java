import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args)
    {

//        Scanner in = new Scanner(System.in);
//        int N = in.nextInt();
//        in.close();
//        for (int i = 1; i < 676; i++) {
//            System.out.print(i + " ");
//            solve(i);
//        }
        solve(52);
    }

    public static void solve (int N) {
        String ans = "";

        int power = 0;
        boolean done = false;
        while (Math.pow(26, power) < N) {
            power++;
            done = true;
        }
        if (done) {
            power--;
        }
        while (power >= 0) {
            int div = (int) Math.pow(26, power);
            int divide = N / div;
//            System.out.println(div + " " + divide);
//            System.out.println(divide);
            if (N % (int) Math.pow(26, power) == 0) {
                ans += 'Z';
            } else {
                ans += (char) (divide + 'A');
            }
            power--;
            N /= (int) Math.pow(26, power);
        }
        System.out.println(ans);
    }
}
