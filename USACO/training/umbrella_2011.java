import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: umbrella_2011
*/
public class umbrella_2011 {
    BufferedReader in;
    PrintWriter out;

    public static void main(String... bob) throws Exception {
        for(int ii = 1;ii<=10;ii++){
            new umbrella_2011().run(ii);
//            System.out.println("hi");
            BufferedReader check = new BufferedReader(new FileReader("umbrella\\O." + ii));
            BufferedReader answer = new BufferedReader(new FileReader("umbrella_2011.out"));
            System.out.println(check.readLine().equals(answer.readLine())?"TEST GOOD":"TEST BAD");
        }
    }
    public void run(int testNum) throws Exception{
        in = new BufferedReader(new FileReader("umbrella\\I."+testNum));
        out = new PrintWriter(new BufferedWriter(new FileWriter("umbrella_2011.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numCows = Integer.parseInt(st.nextToken());
        int widthData = Integer.parseInt(st.nextToken());
        int[] cows = new int[numCows];
        int[] widthCost = new int[widthData];
        for(int ii = 0;ii<numCows;ii++){
            cows[ii] = Integer.parseInt(in.readLine());
        }
        for(int ii = 0;ii<widthData;ii++){
            widthCost[ii] = Integer.parseInt(in.readLine());
        }
        for(int ii = widthData-2;ii >= 0;ii--)
            widthCost[ii] = Math.min(widthCost[ii], widthCost[ii+1]);
        Arrays.sort(cows);
        int[] dp = new int[numCows+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int ii = 1;ii<=numCows;ii++){
            for(int jj = 0;jj<ii;jj++){
                dp[ii] = Math.min(dp[ii], dp[jj] + widthCost[cows[ii-1]-cows[jj]]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[numCows]);
        out.println(dp[numCows]);

        out.close();
    }
}
