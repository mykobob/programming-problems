import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 2/23/15.
 */
public class Hopscotch {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter out = new PrintWriter("hopscotch.out");
        String[] data = in.readLine().split(" ");
        int r = Integer.parseInt(data[0]);
        int c = Integer.parseInt(data[1]);
        int k = Integer.parseInt(data[2]);
        int[][] mat = new int[r][c];
        int[][]dp = new int[r][c];
        Map<String, List<Point>> map = new HashMap<>();
        for (int i = 0; i < r; i++) {
            String[] line = in.readLine().split(" ");
            for (int j = 0; j < c; j++) {
                int num = Integer.parseInt(line[j]);
                mat[i][j] = num;
//                if(i != 0 && j != 0) {
//                    int tmpi = i-1, tmpj = j-1;
//                    String key = tmpi + " " +tmpj;
//                    List<Point> prev = new ArrayList<>(map.get(key));
//                    while(true) {
//                        if(tmpi == -1 && tmpj == -1) {
//                            break;
//                        }
//                        if(tmpi != -1) {
//                            prev.add(new Point(tmpi, j - 1));
//                            tmpi--;
//                        }
//                        if(tmpj != -1) {
//                            prev.add(new Point(i - 1, tmpj));
//                            tmpj--;
//                        }
//                    }
//                    map.put(i + " " + j, prev);
//                } else {
//                    map.put(i + " " + j, new ArrayList<Point>());
//                }
            }
        }

        dp[0][0] = 1;
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                for (int l = 0; l < i; l++) {
                    for (int m = 0; m < j; m++) {
                        if(mat[i][j] != mat[l][m]) {
                            dp[i][j] += dp[l][m];
                        }
                    }
                }
            }
        }
//        for(int[] ii : dp) {
//            for(int jj  : ii) {
//                System.out.printf("%d ", jj);
//            }
//            System.out.println();
//        }
        System.out.println(dp[r-1][c-1]);
        out.println(dp[r-1][c-1]);
        out.close();

    }
}
