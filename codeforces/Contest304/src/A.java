import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt(), N = in.nextInt(), W = in.nextInt();
        int num = W * (W + 1)/2 * K - N;
        System.out.println(Math.max(0, num));
    }
}
