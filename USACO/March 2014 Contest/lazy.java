import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: lazy
*/
public class lazy {
    static BufferedReader in;
    static PrintWriter out;
    static int size, K;
    static int[][] mat;
    public static void main(String ... bob) throws Exception {
        long start = System.nanoTime();
        long allStart = System.nanoTime();
        in = new BufferedReader(new FileReader("lazy.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        size = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mat = new int[size][size];
        for(int ii = 0;ii<size;ii++){
//            String[] data = in.readLine().split(" ");
            st = new StringTokenizer(in.readLine());
            for(int jj = 0;jj<size;jj++)
                mat[ii][jj] = Integer.parseInt(st.nextToken());
        }

//        System.out.println("read in " + (System.nanoTime()-start)/1e9);
//        start = System.nanoTime();

        int[][] dp = new int[size][size];
        for(int ii = 0;ii<size;ii++){
            int sum = 0;
            for(int jj = 0;jj<=K;jj++){ // row
                for(int kk = ii-K;kk <= ii+K;kk++){ // col
                    if(inBounds(jj, kk) && dist(0, ii, jj, kk) <= K){
                        sum += mat[jj][kk];
//                        System.out.printf("Valid (%d, %d)%n", jj, kk);
                    }
                }
            }
//            System.out.println();
            dp[0][ii] = sum;
        }
//        System.out.println("initial stuff " + (System.nanoTime()-start)/1e9);
//        start = System.nanoTime();

        int[] dr = genDR();
//        System.out.println(Arrays.toString(dr));

//        System.out.println("Gen DR " + (System.nanoTime()-start)/1e9);
//        start = System.nanoTime();

        int best = Integer.MIN_VALUE;
        for(int ii = 1;ii<size;ii++){
            for(int jj = 0;jj<size;jj++){
                int sr = ii-1;
                int sc = jj-K;
                int ar = ii;
                int ac = jj-K;
                int subtract = 0, add = 0;
                for(int index = 0;index < dr.length;index++){
                    if(inBounds(sr, sc)){
                        subtract += mat[sr][sc];
                    }
                    if(inBounds(ar, ac)){
                        add += mat[ar][ac];
                    }
//                    System.out.println(r + " " + c);
                    sr += dr[index];
                    sc ++;
                    ar -= dr[index];
                    ac ++;
                }
//                System.out.println(add + " " + subtract);
//                System.out.println();
                dp[ii][jj] = dp[ii-1][jj] + add - subtract;
                best = Math.max(best, dp[ii][jj]);
            }
        }
//        System.out.println("actual algorithm " + (System.nanoTime()-start)/1e9);

//        disp(dp);
//        System.out.println(best);
        out.println(best);
        out.close();
//        System.out.println("EVERYTHING " + (System.nanoTime()-allStart)/1e9);
        System.exit(0);
    }

    private static int[] genDR() {
        int[] dr = new int[2 * K + 1];
        for(int ii = 0;ii<dr.length/2;ii++)
            dr[ii] = -1;
        for(int ii = dr.length/2;ii<dr.length;ii++)
            dr[ii] = 1;
        return dr;
    }

    public static boolean inBounds(int r, int c){
        return r >= 0 && r < size && c >= 0 && c < size;
    }
    public static int dist(int r1, int c1, int r2, int c2){
        return Math.abs(r2-r1) + Math.abs(c2-c1);
    }
    public static void disp(int[][] adjMat){
        for(int[] ii : adjMat){
            for(int i : ii)
                System.out.printf("%d ", i);
            System.out.println();
        }
    }

}
