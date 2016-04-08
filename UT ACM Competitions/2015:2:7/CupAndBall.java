import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class CupAndBall {
    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(new File("input (9).txt"));
        Scanner in = new Scanner(new File("cupandball.in"));
        PrintWriter out = new PrintWriter("cupandball.out");
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int numCups = in.nextInt();
            int numSwaps = in.nextInt();
            int[] cups = new int[numCups];
            for (int j = 0; j < numCups; j++) {
                cups[j] = j;
            }
            for (int j = 0; j < numSwaps; j++) {
                int src = in.nextInt();
                int dest = in.nextInt();

                int tmp = cups[src];
                cups[src] = cups[dest];
                cups[dest] = tmp;
            }
            for (int j = 0; j < cups.length; j++) {
                if(cups[j] == 0) {
                    System.out.println(j);
                    out.println(j);
                }
            }
        }
        out.close();
    }
}
