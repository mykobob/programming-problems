import java.awt.*;
import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: cowtour
*/
public class cowtour {
    static BufferedReader in;
    static PrintWriter out;

    static double[][] adjMat;
    static int[] groups;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("cowtour.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int numPastures = Integer.parseInt(in.readLine());
        ArrayList<Point> points = new ArrayList<Point>();
        for(int ii = 0;ii<numPastures;ii++)
        {
            String[] data = in.readLine().split(" ");
            points.add(new Point(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }
        adjMat = new double[numPastures][numPastures];
        groups = new int[numPastures];
        Arrays.fill(groups, -1);
        for(int ii = 0 ;ii<numPastures;ii++)
            Arrays.fill(adjMat[ii], Double.POSITIVE_INFINITY);
//        Map<Integer, Point> map = new HashMap<Integer, Point>();
        for(int ii = 0;ii<numPastures;ii++){
            String str = in.readLine();
            for(int jj = 0;jj<str.length();jj++){
                if(str.charAt(jj) == '1')
                {
                    Point a = points.get(ii);
                    Point b = points.get(jj);
                    double dist = dist(a, b);
                    adjMat[ii][jj] = dist;
                }
                if(ii == jj)
                    adjMat[ii][jj] = 0;
            }
        }
//        int count = 1;
//        for(int ii = 0;ii<groups.length;ii++){
//            if(groups[ii] == -1)
//                unionLabel(ii, count++);
//        }

//        System.out.println(Arrays.toString(groups));
//        disp();
        for(int kk = 0;kk<numPastures;kk++){
            for(int ii = 0;ii<numPastures;ii++){
                for(int jj = 0;jj<numPastures;jj++){
                    if(connected(ii, kk) && connected(kk, jj))
                    {
                        if(adjMat[ii][kk] + adjMat[kk][jj] < adjMat[ii][jj]){
                            adjMat[ii][jj] = adjMat[ii][kk] + adjMat[kk][jj];
                        }
                    }
                }
            }
        }

        double[] dist = new double[numPastures];

        for(int ii = 0;ii<adjMat.length;ii++){
            for(int jj = 0;jj<adjMat[ii].length;jj++){
                if(adjMat[ii][jj] != Double.POSITIVE_INFINITY && adjMat[ii][jj] > dist[ii]){
                    dist[ii] = adjMat[ii][jj];
                }
            }
        }
//        disp();
//        System.out.println(Arrays.toString(dist));
        double best = Double.POSITIVE_INFINITY;
        for(int ii = 0;ii<adjMat.length;ii++){
            for(int jj = 0;jj<adjMat[ii].length;jj++){
                if(adjMat[ii][jj] == Double.POSITIVE_INFINITY){
//                    System.out.println("hi");
                    if(dist(points.get(ii), points.get(jj)) + dist[ii] + dist[jj] < best){
                        best = dist(points.get(ii), points.get(jj)) + dist[ii] + dist[jj];
//                        System.out.println(dist(points.get(ii), points.get(jj)) + dist[ii] + dist[jj]);
//                        System.out.println(best);
                    }
                }
            }
        }
        for(int ii = 0;ii<dist.length;ii++){
            best = Math.max(best, dist[ii]);
        }

        System.out.printf("%.6f%n", best);
        out.printf("%.6f%n", best);

        out.close();
        System.exit(0);
    }

    private static void unionLabel(int node, int label) {
        if(groups[node] != -1)
            return;
        groups[node] = label;
        for(int ii = 0;ii<adjMat[node].length;ii++){
            if(adjMat[node][ii] != Double.POSITIVE_INFINITY)
                unionLabel(ii, label);
        }
    }

    private static void disp() {
        for(int ii = 0;ii<adjMat.length;ii++){
            for(int jj = 0;jj<adjMat[ii].length;jj++){
                System.out.printf("%.2f ", adjMat[ii][jj] == Double.POSITIVE_INFINITY?0:adjMat[ii][jj]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean connected(int a, int b){
        return adjMat[a][b] != 0;
    }

    private static double dist(Point a, Point b) {
        return Math.hypot(a.y-b.y, a.x-b.x);
    }
}
