import java.awt.Point;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: michael138
LANG: JAVA
TASK: irrigation
*/
public class irrigation {
    static BufferedReader in;
    static PrintWriter out;
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
        main(10);
    }
    public static void main(int test) throws Exception {
        long start = System.nanoTime();
        in = new BufferedReader(new FileReader("irrigation/" + test+ ".in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numFields = Integer.parseInt(st.nextToken());
        int minCost= Integer.parseInt(st.nextToken());
        int[][] adjMat = new int[numFields][numFields];
        for(int ii = 0;ii<numFields;ii++)
            Arrays.fill(adjMat[ii], 1 << 25);
        ArrayList<Point> points = new ArrayList<Point>();
        for(int ii = 0;ii<numFields;ii++){
            st = new StringTokenizer(in.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for(int ii = 0;ii<numFields;ii++){
            for(int jj = ii+1;jj<numFields;jj++){
                Point a = points.get(ii);
                Point b = points.get(jj);
                int dist = dist(a, b);
                if(dist >= minCost){
                    adjMat[ii][jj] = dist;
                    adjMat[jj][ii] = dist;
                }
            }
        }
//        disp(adjMat);
//        System.out.println(prims(adjMat));
        out.println(prims(adjMat));
        out.close();
        System.out.println((System.nanoTime()-start)/1e9);
        System.exit(0);
    }
    public static void disp(int[][] adjMat){
        for(int[] ii : adjMat){
            for(int i : ii)
                System.out.printf("%d ", i);
            System.out.println();
        }
    }
    private static int prims(int[][] adjMat) {
        int best = 0;
        boolean[] visited = new boolean[adjMat.length];
        int[] dist = new int[adjMat.length];
        Arrays.fill(dist, 1 << 25);
        for(int ii = 0;ii<adjMat.length;ii++)
            dist[ii] = Math.min(dist[ii], adjMat[0][ii]);
        visited[0] = true;
        while(!allTraveled(visited)){
            int next = 0;
            int min = 1 << 25;
//            System.out.println(Arrays.toString(dist));
            for(int ii = 0;ii<dist.length;ii++){
                if(dist[ii] < min)
                {
                    min = dist[ii];
                    next = ii;
                }
            }
//            System.out.println(next);
//            System.out.println();
            if(!visited[next]){
                best += dist[next];
                for(int jj = 0;jj< adjMat.length;jj++){
                    dist[jj] = Math.min(dist[jj], adjMat[next][jj]);
                }
                visited[next] = true;
            }
            dist[next] = 1 << 26;
        }
        return best;

    }
    public static boolean allTraveled(boolean[] b){
        for(boolean bb : b)
            if(!bb)
                return false;
        return true;
    }
    public static int dist(Point a, Point b){
        return (int)(Math.pow(b.x-a.x, 2) + Math.pow(b.y-a.y, 2));
    }

}
