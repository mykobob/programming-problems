/*
ID: michael138
LANG: JAVA
TASK: combo
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class combo {
    public static void main(String... bob)  throws Exception {
        Scanner in = new Scanner(new File("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
        int N = in.nextInt();
        int[] farmer = {in.nextInt(), in.nextInt(), in.nextInt()};
        int[] master = {in.nextInt(), in.nextInt(), in.nextInt()};
        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if(close(N,
                            Math.min(farmer[0], i), Math.max(farmer[0], i),
                            Math.min(farmer[1], j), Math.max(farmer[1], j),
                            Math.min(farmer[2], k), Math.max(farmer[2], k))) {
                        count++;
//                        System.out.printf("%d %d %d%n", i, j, k);
                    }

                    else if(close(N,
                            Math.min(master[0], i), Math.max(master[0], i),
                            Math.min(master[1], j), Math.max(master[1], j),
                            Math.min(master[2], k), Math.max(master[2], k))) {
                        count++;
//                        System.out.printf("%d %d %d%n", i, j, k);
                    }
                }
            }
        }
//        System.out.println(close(N, 1, 1, 1, 2, 3, 6));
        System.out.println(count);
        out.println(count);
        out.close();
        System.exit(0);
    }

    public static boolean close(int N, int a, int aa, int b, int bb, int c, int cc) {
        // a is smaller than aa
        // b is smaller than bb
        // c is smaller than cc

        if(close(N, a, aa) && close(N, b, bb) && close(N, c, cc))
            return true;
        return false;
    }

    public static boolean close(int N, int a, int aa) {
        return aa - a <= 2 || a + N - aa <= 2;
    }
}
