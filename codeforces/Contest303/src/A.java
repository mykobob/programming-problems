import java.util.Arrays;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        int[][] mat=  new int[N][N];
        boolean[] good = new boolean[N];
        Arrays.fill(good, true);
        for (int i = 0; i < N; i++) {
            String[] str = in.nextLine().split(" ");
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(str[j]);
                if(mat[i][j] == 1 || mat[i][j] == 3) {
                    good[i] = false;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(good[i]) {
                    if (mat[j][i] == 2) {
                        good[i] = false;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if(good[i])
                count++;
        }

        System.out.println(count);
        for (int i = 0; i < N; i++) {
            if(good[i])
                System.out.print(i + 1 + " ");
        }
        System.out.println();

    }
}
