/*
ID: michael138
LANG: JAVA
TASK: wormhole
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class wormhole {

    static int[] x;
    static int[] y;
    static int numWormholes;
    static int[] partner;
    static int[] nextRight;

    private static boolean cycle() {
        for (int i = 1; i <= numWormholes; i++) {
            int pos = i;
            for (int j = 0; j < numWormholes; j++) {
                pos = nextRight[partner[pos]];

            }
            if(pos != 0) {
                return true;
            }
        }
        return false;
    }

    public static int solve() {
        int i, total = 0;
        for (i = 1; i <= numWormholes; i++) {
            if(partner[i] == 0)
                break;
        }

        if(i > numWormholes) {
            if(cycle()) {
//                for (int j = 1; j <= numWormholes; j++) {
//                    System.out.print(j + ":" + partner[j] + " ");
//                }
//                System.out.println();
                return 1;
            }
            else {
                return 0;
            }
        }

        for (int j = i + 1; j <= numWormholes; j++) {
            if(partner[j] == 0) {
                partner[i] = j;
                partner[j] = i;
                total += solve();
                partner[i] = partner[j] = 0;
            }
        }
        return total;
    }

    public static void main(String... bob)  throws Exception {
        Scanner in = new Scanner(new File("wormhole.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        numWormholes = in.nextInt();
        x = new int[numWormholes + 1];
        y = new int[numWormholes + 1];
        partner = new int[numWormholes + 1];
        nextRight = new int[numWormholes + 1];

        for (int i = 1; i <= numWormholes; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        for (int i = 1; i <= numWormholes; i++) {
            for (int j = 1; j <= numWormholes; j++) {
                if(x[j] > x[i] && y[i] == y[j]) {
                    if(nextRight[i] == 0 || x[j]-x[i] < x[nextRight[i]] - x[i]) {
                        nextRight[i] = j;
                    }
                }
            }
        }

        int ans = solve();
        System.out.println(ans);
        out.println(ans);
//        System.out.println(factorial(numWormholes) / twoToThe(numWormholes/2));
        out.close();
        System.exit(0);
    }





}
