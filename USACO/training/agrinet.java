import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: agrinet
*/
public class agrinet {
    static Scanner in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new Scanner(new File("agrinet.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int size = Integer.parseInt(in.nextLine());
        int[][] adjMat = new int[size][size];
        for(int ii = 0;ii<size;ii++){
            for(int jj = 0;jj<size;jj++)
                adjMat[ii][jj] = in.nextInt();
        }
        System.out.println(prims(adjMat));
        out.println(prims(adjMat));
        out.close();
        System.exit(0);
    }

    private static int prims(int[][] adjMat) {
        int best = 0;
        boolean[] visited = new boolean[adjMat.length];
        int[] dist = new int[adjMat.length];
        Arrays.fill(dist, 1 << 25);
        for(int ii = 0;ii<adjMat.length;ii++)
            dist[ii] = Math.min(dist[ii], adjMat[0][ii]);
        int cur = 0;
        while(!allTraveled(visited)){
            int next = 0;
            int min = 1 << 25;
            for(int ii = 0;ii<dist.length;ii++){
                 if(dist[ii] < min && cur != ii)
                 {
                     min = dist[ii];
                     next = ii;
                 }
            }
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
}
