/*
ID: michael138
LANG: JAVA
TASK: skidesign
*/

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class skidesign {
    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("skidesign.in"));
        int numHeights = in.nextInt();
        int[] heights = new int[numHeights];
        for (int i = 0; i < numHeights; i++) {
            heights[i] = in.nextInt();
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 100-17; i++) {
            int cost = 0;
            for (int j = 0; j < numHeights; j++) {
                if(heights[j] < i) {
                    cost += Math.pow(i - heights[j], 2);
                }
                else if(heights[j] > i + 17) {
                    cost += Math.pow(i + 17 - heights[j], 2);
                }
            }
            ans = Math.min(cost, ans);
        }
        PrintWriter out = new PrintWriter("skidesign.out");
        System.out.println(ans);
        out.println(ans);
        out.close();
        System.exit(0);
    }
}
