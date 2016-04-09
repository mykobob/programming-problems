import java.util.*;
        import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: concom
*/
public class concom2 {
    static Scanner in;
    static PrintWriter out;
    static double[][] directControl;
    static boolean[][] traveledTo;
    static int MAX;

    public static void main(String... bob) throws Exception {
        in = new Scanner(new File("concom.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
        directControl = new double[101][101];
        traveledTo = new boolean[101][101];
        int directions = in.nextInt();
        MAX = Integer.MIN_VALUE;
        while(directions-->0){
            int src = in.nextInt();
            int end = in.nextInt();
            int control = in.nextInt();
            MAX = Math.max(MAX, Math.max(src, end));
            directControl[src][end] = control/100.0;
        }
        MAX++;
        disp();
        System.out.println();
        for(int ii = 1;ii<MAX;ii++){
            dfs(true, ii, ii, 1);
//            System.out.println("next iter");
            for(int jj = 0;jj<traveledTo.length;jj++)
                Arrays.fill(traveledTo[jj], false);
            disp();
            System.out.println();
        }
//        disp();
//        System.out.println(MAX);
        for(int ii = 1;ii<MAX;ii++){
            for(int jj = 1;jj<MAX;jj++){
                if(directControl[ii][jj] > .5){
//                    System.out.println(ii + " " + jj);
                    out.printf("%d %d%n", ii, jj);
                }
                else{
                    double total = 0;
                    for(int kk = 1;kk<MAX;kk++){
                        if(kk != jj && directControl[ii][kk] != 0){
                            total += directControl[kk][jj];
                        }
                    }
                    if(total > .5)
                        out.printf("%d %d%n", ii, jj);
                }
            }
        }
        out.close();
        System.exit(0);
    }
    public static void dfs(boolean firstIter, int start, int row, double percent){
        if(!firstIter && start == row){
            return;
        }
        for(int ii = 0;ii<MAX && ii<directControl[row].length;ii++){
            if(directControl[row][ii] != 0 && !traveledTo[row][ii]){
                if(directControl[start][ii] == 0 && start != ii)
                    directControl[start][ii] = percent * directControl[row][ii];
//                System.out.printf("found this %.2f at (%d,%d)%n", directControl[row][ii], row, ii);
                traveledTo[row][ii] = true;
                dfs(false, start, ii, percent*directControl[row][ii]);
            }
        }
    }
    public static void disp(){
        for(int ii = 0;ii<MAX;ii++){
            for(int jj = 0;jj<MAX;jj++){
                System.out.printf("%2d ", (int)(directControl[ii][jj] * 100));
            }
            System.out.println();
        }

    }
}


