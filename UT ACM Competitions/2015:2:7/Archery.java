import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Archery {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("archery.out");
        int[] radii = {5, 10, 15, 20};
        int[] value = {100, 50, 25, 10};
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int arrows = in.nextInt();
            int score = 0;
            for (int j = 0; j < arrows; j++) {
                double x = in.nextDouble(), y = in.nextDouble();
                double dist = Math.hypot(x, y);
                for (int k = 0; k < 4; k++) {
                    if(dist <= radii[k]) {
                        score += value[k];
                        break;
                    }
                }
            }
            System.out.println(score);
            out.println(score);
        }
        out.close();
    }
}
