import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: concom
*/
public class concom {
    static Scanner in;
    static PrintWriter out;
    static int N;
    static int[][] percents;
    static int MAX = Integer.MIN_VALUE;

    public static void main(String... bob) throws Exception {
        in = new Scanner(new File("concom.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        N = in.nextInt();
        percents = new int[SIZE][SIZE];
        for(int ii = 0;ii<N;ii++){
            int src = in.nextInt();
            int dest = in.nextInt();
            int percent = in.nextInt();
            percents[src][dest] = percent;
            MAX = Math.max(MAX, Math.max(src, dest));
        }
        for(int ii = 1;ii<=MAX;ii++){
            Arrays.fill(beenTo, false);
            Arrays.fill(percentOwned, 0);
            Arrays.fill(owned, false);
            solve(ii);
            for(int jj = 0;jj<=MAX;jj++){
                if(owned[jj] && jj != ii){
//                    System.out.println(ii + " " + jj);
                    out.println(ii + " "+ jj);
                }
            }
//            System.out.println(Arrays.toString(percentOwned));
        }

        out.close();
        System.exit(0);
    }
    static int SIZE = 101;
    static boolean[] beenTo = new boolean[SIZE];
    static int[] percentOwned = new int[SIZE];
    static boolean[] owned = new boolean[SIZE];
    public static void solve(int src){
        if(beenTo[src])
            return;
        beenTo[src] = true;
        for(int ii = 1;ii<=MAX;ii++){
            percentOwned[ii] += percents[src][ii];
            if(percentOwned[ii] > 50){
                owned[ii] = true;
                solve(ii);
            }
        }
    }
}