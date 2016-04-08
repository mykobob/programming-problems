import java.util.Scanner;

/**
 * Created by Michael on 5/17/15.
 */
public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
//        System.out.println(Math.ceil(N * N / 2.0));
        if(K > (int)Math.ceil(N * N / 2.0)) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(count >= K) {
                        System.out.print("S");
                    } else {
                        if(j % 2 == i % 2) {
                            System.out.print("L");
                            count++;
                        } else {
                            System.out.print("S");
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}
