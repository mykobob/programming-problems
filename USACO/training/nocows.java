import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: nocows
*/
public class nocows {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("nocows.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int maxHeight = Integer.parseInt(st.nextToken());
        int[][] dp = new int[numNodes+1][maxHeight+1];
        dp[1][1] = 1;
        for(int ii = 2;ii<=numNodes;ii++){
            for(int jj = 1;jj<=maxHeight;jj++){
                int sum = 0;

                for(int leftTrees = 1;leftTrees<ii-1;leftTrees++){
                    for(int rightTrees = 1;rightTrees<jj-1;rightTrees++){
                        sum += 2 * dp[leftTrees][jj-1] * dp[ii-1-leftTrees][rightTrees] % 9901;
                    }
                    sum += dp[leftTrees][jj-1] * dp[ii-1-leftTrees][jj-1] % 9901;
                }

                dp[ii][jj] = sum%9901;
            }
        }
//        for(int[] ii : dp)
//            System.out.println(Arrays.toString(ii));
        System.out.println(dp[numNodes][maxHeight]);
        out.println(dp[numNodes][maxHeight]);
        out.close();
        System.exit(0);
    }
}
