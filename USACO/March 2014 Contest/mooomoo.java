import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: mooomoo
*/
public class mooomoo {
    static BufferedReader in;
    static PrintWriter out;
    static int[] vals;
    public static void main(String...bob) throws Exception
    {
//        for(int ii = 1;ii<=10;ii++){
//            main(ii);
//            Scanner in = new Scanner(new File("mooomoo.out"));
//            int myAns = in.nextInt();
//            in = new Scanner(new File("mooomoo/" + ii + ".out"));
//            int theirAns = in.nextInt();
//            System.out.printf("%d %d %b%n", myAns, theirAns, myAns == theirAns);
//        }
//        main(1);
//        System.out.println(leastCows(74));
//        int[] test = {10, 26, 74, 52, 62, 36, 26, 56, 80, 46, 16, 64, 50, 16, 74};
        main(0);
    }

    public static void main(int testCase) throws Exception {
        in = new BufferedReader(new FileReader("mooomoo.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("mooomoo.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numFields = Integer.parseInt(st.nextToken());
        int numBreeds = Integer.parseInt(st.nextToken());
        vals = new int[numBreeds];
        for (int ii = 0; ii < numBreeds; ii++)
            vals[ii] = Integer.parseInt(in.readLine());
        int[] volumes = new int[numFields];
        for (int ii = 0; ii < numFields; ii++)
            volumes[ii] = Integer.parseInt(in.readLine());
        int cows = leastCows(volumes[0]);
        for (int ii = 1; ii < volumes.length; ii++) {
            int target = volumes[ii] - (volumes[ii - 1] - 1 < 0 ? 0 : volumes[ii - 1] - 1);
//            System.out.println(target);
            if (target < 0) {
//                System.out.println(-1);
                out.println(-1);
                out.close();
                System.exit(0);
            }
            int needed = leastCows(target);
//            System.out.println(needed);
            if (needed == 1 << 25) {
//                System.out.println(-1);
                out.println(-1);
                out.close();
//                System.out.println("hi");
                System.exit(0);
            }
//            System.out.println(needed);
            cows += needed;
        }
//        System.out.println(cows);
        out.println(cows);
        out.close();
//        System.exit(0);
    }



    public static int leastCows(int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, 1 << 25);
        dp[0] = 0;
        for (int ii = 1; ii < dp.length; ii++) {
            for (int jj = 0; jj < vals.length; jj++) {
                if (ii - vals[jj] >= 0)
                    dp[ii] = Math.min(dp[ii], dp[ii - vals[jj]] + 1);
            }
        }
//        for(int ii = 0;ii<dp.length;ii++)
//            System.out.println(ii + " " + dp[ii]);
        return dp[target];
    }
}
