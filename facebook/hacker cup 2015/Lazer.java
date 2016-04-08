import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Lazer {
    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("lazer.txt"));
        PrintWriter out = new PrintWriter("lazer.out");


        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int rs = in.nextInt(), cs = in.nextInt();
            in.nextLine();
            char[][] mat = new char[rs][cs];
            for (int j = 0; j < rs; j++) {
                String str = in.nextLine();
                for (int k = 0; k < cs; k++) {
                    mat[j][k] = str.charAt(k);
                }
            }
            List<Turret> turrets = new ArrayList<>();
            int startR = -1, startC = -1;
            int endR = -1, endC = -1;
            for (int j = 0; j < rs; j++) {
                for (int k = 0; k < cs; k++) {
                    if("^v><".contains(""+mat[j][k])) {
                        turrets.add(new Turret(mat, j, k));
                    }
                    if(mat[j][k] == 'S')
                    {
                        startR = j;
                        startC = k;
                    }
                    if(mat[j][k] == 'G')
                    {
                        endR = j;
                        endC = k;
                    }
                }
            }

//            visited = new boolean[rs][cs];
            PriorityQueue<Data> frontier = new PriorityQueue<>();
            frontier.add(new Data(startR, startC, 0, startR, startC, endR, endC));
            boolean done = false;
            outer:
            while(!frontier.isEmpty()) {
                Data next = frontier.poll();
                if(!inBounds(next.x, next.y, mat)) {
                    continue;
                }

//                if(visited[next.x][next.y])
//                    continue;
                if(mat[next.x][next.y] == '#' || "^v><".contains(""+mat[next.x][next.y])) {
                    continue;
                }

                if(next.steps != 0) {
                    for (int j = 0; j < turrets.size(); j++) {
                        Set<Point> cur = turrets.get(j).directions.get((next.steps) % 4);
//                        System.out.println(cur);
                        if (cur.contains(new Point(new Point(next.x, next.y)))) {
                            continue outer;
                        }
                    }
                }
                if(mat[next.x][next.y] == 'G') {
                    System.out.printf("Case #%d: %d\n", i + 1, next.steps);
                    out.printf("Case #%d: %d\n", i + 1, next.steps);
                    done = true;
                    break;
                }
//                System.out.println(next);
//                visited[next.x][next.y] = true;
//                System.out.println(frontier);
                for (int j = 0; j < 4; j++) {
                    frontier.add(new Data(next.x + dr[j], next.y + dc[j], next.steps + 1, startR, startC,endR,endC));
                }
            }
            if(!done) {
                System.out.printf("Case #%d: %s\n", i + 1, "impossible");
                out.printf("Case #%d: %s\n", i + 1, "impossible");
            }

//            if(i == 0) {
//                dfs(startR, startC, mat, turrets, 0, i + 1);
//            }
//            else {
//                dfs(startR, startC, mat, turrets, 0, i + 1);
//            }
//            System.out.println();
        }
        out.close();
    }
    static boolean[][] visited;

    public static int dfs(int r, int c, char[][] mat, List<Turret> turrets, int steps, int cases) {
        if(!inBounds(r, c, mat))
            return -1;
        if(visited[r][c])
            return -1;
        if(mat[r][c] == '#' || "^v><".contains(""+mat[r][c]))
            return -1;
        if(steps != 0) {
            for (int j = 0; j < turrets.size(); j++) {
                Set<Point> cur = turrets.get(j).directions.get((steps) % 4);
//                        System.out.println(cur);
                if (cur.contains(new Point(new Point(r, c)))) {
                    return -5;
                }
            }
        }
        if(mat[r][c] == 'G') {
            System.out.printf("Case #%d: %d\n", cases, steps);
            return 9;
        }
        System.out.println(r + " " + c);
        boolean redo = false;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int result = dfs(r + dr[i], c + dc[i], mat, turrets, steps + 1, cases);
            if(result == 9) {
                return result;
            }
            if(result == -5) {
                redo = true;
            }
        }
        if(redo) {
            visited[r][c] = false;
            return -5;
        }
        return -1;
    }

    public static boolean inBounds(int x, int y, char[][] arr) {
        return x >= 0 && x < arr.length && y >= 0 && y <arr[x].length;
    }

    static class Data implements Comparable<Data> {
        int steps;
        int x;
        int y;
        int xx, yy;
        int ex, ey;
        public Data(int x, int y, int steps, int origX, int origY, int endx, int endy) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            xx = origX;
            yy = origY;
            ex = endx;
            ey = endy;
        }

        public int distToSrc() {
            return Math.abs(x - xx) + Math.abs(y - yy);
        }

        public int distToDest() {
            return Math.abs(x - ex) + Math.abs(y - ey);
        }

        private int compare() {
            return distToDest() + distToSrc();
        }

        public String toString() {
            return String.format("%d %d: %d", x, y, steps);
        }

        @Override
        public int compareTo(Data o) {
            return o.compare() - compare();
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Turret
    {
        List<Set<Point>> directions;
        public Turret(char[][] arr, int r, int c) {
            int index = 0;
            switch (arr[r][c])
            {
                case '^': index = 0; break;
                case '>': index = 1; break;
                case 'v': index = 2; break;
                case '<': index = 3; break;
            }
            directions = new ArrayList<>();
            while(directions.size() < 4) {
                Set<Point> blah = new HashSet<>();
                go(arr, r + dr[index], c + dc[index], index, blah);
                directions.add(blah);
                index = (index + 1) % 4;
            }
        }

        private void go(char[][] arr, int r, int c, int index, Set<Point> seen) {
            if(r < 0 || r >= arr.length || c < 0 || c >= arr[r].length)
                return;
            if(arr[r][c] == '#' || arr[r][c] == '^' || arr[r][c] == '>' || arr[r][c] == 'v' || arr[r][c] == '<')
                return;
//            System.out.println(r + " " + c);
            seen.add(new Point(r, c));
            go(arr, r + dr[index], c + dc[index], index, seen);
        }

    }
}