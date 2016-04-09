import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: maze1
*/
public class maze1 {
    static BufferedReader in;
    static PrintWriter out;
    static char[][] mat;
    static int[][] count;

    static int[] dr_check = {-1, 0, 1, 0};
    static int[] dc_check = {0, 1, 0, -1};

    static int[] dr = {-2, 0, 2, 0};
    static int[] dc = {0, 2, 0, -2};

    static int r1 = -1, c1 = -1, r2 = -1, c2 = -1;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("maze1.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int width = Integer.parseInt(st.nextToken()) *2 +1;
        int height = Integer.parseInt(st.nextToken())*2+1;
        mat = new char[height][width];
        count = new int[height][width];
        for(int ii = 0;ii<mat.length;ii++){
            String str= in.readLine();
            while(str.length() < width)
                str = str.concat(" ");
            for(int jj = 0;jj<str.length();jj++){
                mat[ii][jj] = str.charAt(jj);
            }
            Arrays.fill(count[ii], Integer.MAX_VALUE);
        }
        for(int ii = 1;ii<mat.length;ii+=2)
            for(int jj = 1;jj<mat[ii].length;jj+=2){
                for(int kk = 0;kk<4;kk++)
                    if(isExit(ii, jj, dr_check[kk], dc_check[kk], dr[kk], dc[kk])){
                        if(r1 == -1){
                            r1 = ii;
                            c1 = jj;
                        }
                        else{
                            r2 = ii;
                            c2 = jj;
                        }

                }
            }
//        System.out.printf("%d %d    %d %d%n", r1, c1, r2, c2);
        bfs(r1, c1);
        bfs(r2, c2);
        int max = Integer.MIN_VALUE;
        for(int ii = 1;ii<count.length;ii+=2){
            for(int jj = 1;jj<count[ii].length;jj+=2){
                if(count[ii][jj] != Integer.MAX_VALUE)
                    max = Math.max(max, count[ii][jj]);
//                System.out.printf("%4d ", count[ii][jj] == Integer.MAX_VALUE?0:count[ii][jj]);
            }
//            System.out.println();
        }
        System.out.println(max);
        out.println(max);
        out.close();
        System.exit(0);
    }
    public static int bfs(int r, int c){
        Queue<Point> frontier = new LinkedList<Point>();
        Set<Point> visited = new HashSet<Point>();
        frontier.add(new Point(r,c,1));
        while(!frontier.isEmpty()){
            Point next = frontier.poll();
            if(visited.add(next)){
                count[next.r][next.c] = Math.min(count[next.r][next.c], next.dist);
                for(int ii = 0;ii<4;ii++){
                    if(canMoveTo(next.r, next.c, dr_check[ii], dc_check[ii])){
                        if(!outOfBounds(next.r + dr[ii], next.c + dc[ii])){
                            Point p = new Point(next.r + dr[ii], next.c + dc[ii], next.dist+1);
                            frontier.add(p);
                        }
                    }
                }
            }
        }
        return -1;
    }
    public static boolean isExit(int r, int c, int dr, int dc, int dr2, int dc2){
         if(canMoveTo(r, c, dr, dc))
             if(outOfBounds(r + dr2, c + dc2))
                 return true;
         return false;
    }
    public static boolean canMoveTo(int r, int c, int dr, int dc){
        if(mat[r + dr][c + dc] == ' ')
            return true;
        return false;
    }
    public static boolean outOfBounds(int r, int c){
        return r < 0 || r >= mat.length || c < 0 || c >= mat[r].length;
    }

    static class Point{
        int r, c, dist;
        public Point(int r, int c, int dist){
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        public boolean equals(Object other){
            return hashCode() == other.hashCode();
        }
        public int hashCode(){
            return r * 10000 + c * 100;
        }
        public String toString(){
            return String.format("(%d,%d)", r, c);
        }
    }
}
