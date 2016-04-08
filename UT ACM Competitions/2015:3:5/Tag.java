import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by Michael on 3/5/15.
 */
public class Tag {

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("tag.txt"));
        int T = in.nextInt();
        while(T -- >0) {
            int N = in.nextInt(), M = in.nextInt(), P = in.nextInt();
            int[] playerState = new int[P];
            boolean[] out = new boolean[P];
            Map<Integer, Point> pos = new HashMap<Integer, Point>();
            for (int i = 0; i < P - 1; i++) {
                playerState[i + 1] = in.nextInt();
            }
            int R = -1, C = -1;
            in.nextLine();
            char[][] mat = new char[N][M];
            for (int i = 0; i < N; i++) {
                String tmp = in.nextLine();
                for (int j = 0; j < M; j++) {
                    mat[i][j] = tmp.charAt(j);
                    if(mat[i][j] == '0') {
                        R = i;
                        C = j;
                    }
                    else if(Character.isDigit(mat[i][j])) {
                        pos.put(mat[i][j] - '0', new Point(i, j));
                    }
                }
            }
            int i;
            for (i = 0; i < 30 * 60; i++) {
                if(allOut(out)) {
                    System.out.println(i);
                    break;
                }
                List<Integer> closestPpl = new ArrayList<Integer>();
                for (int j = 1; j < P; j++) {
                    Point cur = pos.get(j);
                    int distance = getDist(R, C, cur.x, cur.y);
                    if(closestPpl.isEmpty()) {
                        closestPpl.add(j);
                    } else {
                        int smallest = closestPpl.get(0);
                        Point smallPoint = pos.get(smallest);
                        int smallDist = getDist(R, C, smallPoint.x, smallPoint.y);
                        if(distance < smallDist) {
                            closestPpl.clear();
                            closestPpl.add(j);
                        } else if(distance == smallDist) {
                            closestPpl.add(j);
                        }
                    }
                }
                int closest = closestPpl.get(0);
                Point spot = pos.get(closest);
                int rowDiff = spot.x - R;
                int colDiff = spot.y - C;

                int dr = -1;
                int dc = -1;

                if(Math.abs(rowDiff) > Math.abs(colDiff)) {
//                    dr = (int)Math.signum(rowDiff);
//                    dc = 0;
                    dc = (int)Math.signum(colDiff);
                    dr = 0;
                    System.out.println(mat[R + dr][C + dc] + " 0");
                    if(mat[R + dr][C + dc] == '#') {
//                        dr = 0;
//                        dc = (int)Math.signum(colDiff);
                        dc = 0;
                        dr = (int)Math.signum(rowDiff);
                    }
                    if(mat[R + dr][C + dc] == '#') {
                        dr = 0;
                        dc = 0;
                    }
                } else {
//                    dc = (int) Math.signum(colDiff);
//                    dr = 0;
                    dr = (int)Math.signum(rowDiff);
                    dc = 0;
                    System.out.println(mat[R + dr][C + dc] + " 1");
                    if (mat[R + dr][C + dc] == '#') {
//                        dr = (int) Math.signum(rowDiff);
//                        dc = 0;
                        dc = (int)Math.signum(colDiff);
                        dr = 0;
                    }
                    if (mat[R + dr][C + dc] == '#') {
                        dr = 0;
                        dc = 0;
                    }
                }

                System.out.println(R + " " + C);
                R += dr;
                C += dc;

                for (int j = 1; j < P; j++) {
                    Point check = pos.get(j);
//                    System.out.println(check);
                    if(check.x == R && check.y == C) {
                        out[j] = true;
                    }
                }
                for (int j = 1; j < P; j++) {
                    Point check = pos.get(j);
                    int state = playerState[j];
                    if(!out[j]) {
                        int curdr = Tag.dr[state];
                        int curdc = Tag.dc[state];
                        if(!inBounds(N, M, check.x + curdr, check.y + curdc) || mat[check.x + curdr][check.y + curdc] == '#') {
                            state = (state + 1) % 4;
                            curdc = 0;
                            curdr = 0;
                        }
                        pos.put(j, new Point(check.x + curdr, check.y + curdc));
                        playerState[j] = state;
                    }
                }
            }
            if(i == 30 * 60) {
                System.out.println("DRAW");
            }
        }
    }

    public static boolean inBounds(int N, int M, int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static boolean allOut(boolean[] b) {
        for (int i = 1; i < b.length; i++) {
            if(!b[i])
                return false;
        }
        return true;
    }

    public static int getDist(int R, int C, int r, int c) {
        return Math.abs(R- r) + Math.abs(C - c);
    }

}
