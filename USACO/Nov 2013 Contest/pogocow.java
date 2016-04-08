import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: pogocow
*/
public class pogocow {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("pogocow.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("pogocow.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(in.readLine());
        int[][] dp = new int[N][N];
        Spot[] spots = new Spot[N];
        for(int ii = 0;ii<N;ii++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            spots[ii] = new Spot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(spots);
        int best = 0;
        for(int ii = 0;ii<2;ii++){
            for (int i = N-1; i >= 0; i--) {
                int K = N;
                int val = 0;
                for (int j = 0; j <= i; j++) {
                    while(K-1 > i && spots[K-1].spot-spots[i].spot >= spots[i].spot-spots[j].spot) {
                        K--;
                        val = Math.max(val, spots[K].points + dp[K][i]);
                    }
                    dp[i][j] = val;
                }
                best = Math.max(best, val + spots[i].points);
            }

            Arrays.sort(spots, Collections.reverseOrder());
        }
        Arrays.sort(spots);
        for(Spot p : spots)
            System.out.println(p);
        for(int[] ii : dp){
            System.out.println(Arrays.toString(ii));
        }
        System.out.println(best);

        out.close();
        System.exit(0);
    }
    static class Spot implements Comparable<Spot>{
        int spot, points;
        public Spot(int s, int p){
            spot = s;
            points = p;
        }
        public int compareTo(Spot other){
            return spot-other.spot;
        }
        public String toString(){
            return String.format("Spot: %d  Points: %d", spot, points);
        }
    }
}
