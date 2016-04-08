import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jousting {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input (8).txt"));
        PrintWriter out = new PrintWriter("jousting.out");
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int[] skills = new int[N];
            for (int j = 0; j < N; j++) {
                skills[j] = in.nextInt();
            }
            double[][] chance = new double[(int)(Math.log(N)/Math.log(2))][N];
            for (int k = 0; k < N; k += 2) {
                int skillA = skills[k];
                int skillB = skills[k + 1];
                chance[0][k] = canWin(skillA, skillB);
                chance[0][k + 1] = canWin(skillB, skillA);
            }

            for (int j = 1; j < chance.length; j ++) {
                int size = 1 << (j + 1);
                for (int k = 0; k < N; k++) {
                    int lower = (k) / size * size;
                    int upper = (k) / size * size + size;

                    for (int l = lower; l < upper; l++) {
                        if(l/(size/2) != k/(size/2)) {
                            if (k != l) {
                                chance[j][k] += chance[j - 1][k] * chance[j - 1][l] * canWin(skills[k], skills[l]);
                            }
                        }
                    }
//                    print(chance);
//                    System.out.println();
                }
            }
            System.out.printf("%.3f%%\n", chance[chance.length - 1][0] * 100);
            out.printf("%.3f%%\n", chance[chance.length - 1][0] * 100);
        }
        out.close();
    }

    public static void print(double[][] chance) {
        for (int j = 0; j < chance.length; j++) {
            for (int k = 0; k < chance[j].length; k++) {
                System.out.printf("%.3f ", chance[j][k]);
            }
            System.out.println();
        }
    }

    public static double canWin(double A, double B) {
        return A / (A + B);
    }

}
