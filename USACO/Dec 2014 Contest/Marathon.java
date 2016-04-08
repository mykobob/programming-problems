
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Marathon {
    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("marathon.in"));
        int numPlaces = in.nextInt();
        int canSkip = in.nextInt();
        Point[] arr = new Point[numPlaces];
        for (int i = 0; i < numPlaces; i++) {
            arr[i] = new Point(in.nextInt(), in.nextInt());
        }

        int[][] dp = new int[numPlaces][canSkip + 1];
        for (int i = 0; i < numPlaces; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        Arrays.fill(dp[0], 0);
        for (int i = 1; i < numPlaces; i++) {
            for (int j = 0; j <= canSkip; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + calcDist(arr, i, 1);
                }
                else {
                    dp[i][j] = dp[i - 1][j] + calcDist(arr, i, 1);
                    for (int k = 1; k <= j; k++) {
//                        if(i - k < 0) {
//                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + calcDist(arr, i, 1));
//                        }
//                        else {
//                            dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][k] + calcDist(arr, i, 1),
//                                    dp[i - k][k]) + calcDist(arr, i, k));
//                        }
                        if(i - j - 1 >= 0) {
                            int dist = calcDist(arr, i, k + 1);
//                            System.out.println(dist + " of " + (i-k) + " to " + i);
                            dp[i][j] = Math.min(dp[i - k - 1][j - k] + dist, dp[i][j]);
                        }
                        else {
                            dp[i][j] = Math.min(dp[i][j], dp[i][k - 1]);
                        }
                    }
                }
            }
        }
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        List<Point> used = new ArrayList<Point>();
//        used.add(arr[0]);
//        int ans = solve(arr, 1, canSkip, used);
//        System.out.println(ans);
////        System.out.println(count);
        int ans = dp[numPlaces - 1][canSkip];
        PrintWriter out = new PrintWriter(new File("marathon.out"));
        System.out.println(ans);
        out.println(ans);
        out.close();
        System.exit(0);
    }

    static int count = 0;
    static Map<List<Point>, Integer> seen = new HashMap<List<Point>, Integer>();

    public static int solve(Point[] arr, int index, int canSkip, List<Point> used) {
        if(canSkip < 0) {
            return Integer.MAX_VALUE;
        }
        if(index == arr.length - 1) {
            used.add(arr[index]);
            int dist = 0;
            for (int i = 1; i < used.size(); i++) {
                dist += calcDist(used, i);
            }
            used.remove(index);
            return dist;
        }
        if(seen.containsKey(used)) {
            return seen.get(used);
        }
        count++;
        used.add(arr[index]);
        int ans = solve(arr, index + 1, canSkip, used);
        used.remove(used.size() - 1);
        ans = Math.min(ans, solve(arr, index+1, canSkip - 1, used));
        seen.put(used, ans);
        return ans;
    }

    public static int calcDist(List<Point> arr, int index) {
        return Math.abs(arr.get(index).x - arr.get(index - 1).x) + Math.abs(arr.get(index).y - arr.get(index - 1).y);
    }

    public static int calcDist(Point[] arr, int index, int farBack) {
        return Math.abs(arr[index].x - arr[index - farBack].x) + Math.abs(arr[index].y - arr[index - farBack].y);
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }

        public int hashCode() {
            return (x + " " + y).hashCode();
        }
    }
}
