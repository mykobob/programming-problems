import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Rover {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Rover"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken()), M = i(data.nextToken()), R = i(data.nextToken()), Q = i(data.nextToken());
            List<MiniRover> rovers = new ArrayList<MiniRover>();
            for (int j = 0; j < R; j++) {
                StringTokenizer roverData = new StringTokenizer(in.readLine());
                int r = i(roverData.nextToken()), c = i(roverData.nextToken());
                char ch = roverData.nextToken().charAt(0);
                int dir = -1;
                switch (ch) {
                    case 'S': dir = 0; break;
                    case 'W': dir = 1; break;
                    case 'N': dir = 2; break;
                    case 'E': dir = 3; break;
                }
                rovers.add(new MiniRover(r, c, dir));
            }
            int[][] mat = new int[N][M];
            for (int j = 0; j < N; j++) {
                String str = in.readLine();
                for (int k = 0; k < M; k++) {
                    if (str.charAt(k) == '#') {
                        mat[j][k] = -1;
                    } else {
                        mat[i][j] = str.charAt(k) - '0';
                    }
                }
            }

            for (MiniRover rover : rovers) {

            }
        }
    }

    public static long totalTime(MiniRover rover, int[][] mat, boolean[][] visited, int timeLeft) {
        if (visited[rover.r][rover.c]) {
            return 0;
        }
        int nextR = rover.r + dr[rover.dir];
        int nextC = rover.c + dc[rover.dir];
        int count = 0;
        while (!valid(nextR, nextC, mat) && count < 4) {
            rover.dir = (rover.dir + 1) % 4;
            count++;
        }
        if (count == 4) {
            return 0;
        }

    }

    private static boolean valid(int nextR, int nextC, int[][] mat) {
        if (inBounds(nextR, nextC, mat)) {
            if (mat[nextR][nextC] != -1) {
                return true;
            }
        }
        return false;
    }

    private static boolean inBounds(int nextR, int nextC, int[][] mat) {
        return nextR >= 0 && nextR < mat.length && nextC >= 0 && nextC < mat[nextR].length
    }


    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    static class MiniRover {
        int r, c;
        int dir;

        public MiniRover(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
