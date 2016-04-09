import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: stamps
*/
public class stamps {
//    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
//        in = new BufferedReader(new FileReader("stamps.in"));
        long start = System.nanoTime();
        Scanner in = new Scanner(new File("stamps.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.nextLine());
        int numStamps = Integer.parseInt(st.nextToken());
        int vals = Integer.parseInt(st.nextToken());
        int[] arr = new int[vals];
        int index = 0;
        while(in.hasNextLine()){
            String[] data = in.nextLine().split(" ");
            for(String str : data)
                arr[index++] = Integer.parseInt(str);
        }
        Arrays.sort(arr);
        //List<Integer> dp = new ArrayList<Integer>();
        int[] dp = new int[10000 * numStamps + 2];
        Arrays.fill(dp, 1 << 25);
        dp[0] = 0;
        boolean good = true;
//        index = 1;
        int answer = 0;
        for(int ii = 1;ii<dp.length;ii++){
//            int best = 1 << 25;
            for(int jj = 0;jj<arr.length;jj++){
                if(ii-arr[jj]>=0){
                    dp[ii] = Math.min(dp[ii], dp[ii-arr[jj]]+1);
                }
            }
            if(dp[ii] > numStamps){
                answer = ii;
                break;
            }
//            System.out.println(ii);
//            System.out.println(dp);
        }
//        System.out.println(dp.get(dp.size()-1));
//        System.out.println(dp.size()-2);
//        System.out.println(dp.length);
//        for(int ii = 0;ii<answer;ii++){
//            System.out.print(dp[ii] + " ");
//        }
        System.out.println(answer-1);
        out.println(answer-1);
        out.close();
        System.out.println((System.nanoTime()-start)/1e9);
        System.exit(0);
    }
}
