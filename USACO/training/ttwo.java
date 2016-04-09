import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: ttwo
*/
public class ttwo {
    static BufferedReader in;
    static PrintWriter out;

    static char[][] mat = new char[10][10];
    static int sr, sc, cr, cc;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int farmerIndex = 0, cowIndex = 0;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("ttwo.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        for(int ii = 0;ii<10;ii++)
        {
            String st = new StringTokenizer(in.readLine()).nextToken();
            if(st.contains("C")){
                cr = ii;
                cc = st.indexOf("C");
            }
            if(st.contains("F")){
                sr = ii;
                sc = st.indexOf("F");
            }
            mat[ii] = st.toCharArray();
        }
        int minutes = 0;
        while(sr != cr || sc != cc){
            if(minutes > 400)
            {
                minutes = 0;
                break;
            }
            // update farmer
            mat[sr][sc] = '.';
            int newR = sr + dr[farmerIndex];
            int newC = sc + dc[farmerIndex];
            if(!inBounds(newR, newC) || mat[newR][newC] == '*'){
                newR = sr;
                newC = sc;
                farmerIndex = (farmerIndex+1)%4;
            }
            sr = newR;
            sc = newC;
            mat[newR][newC] = 'F';

            // update cow
            mat[cr][cc] = '.';
            newR = cr + dr[cowIndex];
            newC = cc + dc[cowIndex];
            if(!inBounds(newR, newC) || mat[newR][newC] == '*'){
                newR = cr;
                newC = cc;
                cowIndex = (cowIndex+1)%4;
            }
            cr = newR;
            cc = newC;
            mat[newR][newC] = 'C';
            minutes++;

//            for(char[] ch : mat)
//                System.out.println(ch);
//            System.out.println();
        }
        System.out.println(minutes);
        out.println(minutes);
        out.close();
        System.exit(0);
    }
    public static boolean inBounds(int r, int c){
        return r >= 0 && r < 10 && c >= 0 && c < 10;
    }
}
