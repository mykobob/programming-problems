import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: inflate
*/
public class inflate {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("inflate.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] dp = new int[Integer.parseInt(st.nextToken())+1];
        int N = Integer.parseInt(st.nextToken());
        int[] points = new int[N];
        int[] minutes = new int[N];
        for(int ii = 0;ii<N;ii++){
            st = new StringTokenizer(in.readLine());
            points[ii] = Integer.parseInt(st.nextToken());
            minutes[ii] = Integer.parseInt(st.nextToken());
        }
        for(int ii = 0;ii<dp.length;ii++){
            for(int jj = 0;jj<minutes.length;jj++){
                if(ii - minutes[jj] >= 0){
                    dp[ii] = Math.max(dp[ii], dp[ii-minutes[jj]] + points[jj]);
                }
            }
        }
        System.out.println(dp[dp.length-1]);
        out.println(dp[dp.length-1]);
        out.close();
        System.exit(0);
    }
}
