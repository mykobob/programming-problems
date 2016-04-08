import java.util.*;

public class Solution464B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Cube cube = new Cube();
        for (int i = 0; i < 8; i++) {
            cube.addPoint(new Point(in.nextInt(), in.nextInt(), in.nextInt()));
        }
        solve(cube, 0);
        if (!done) {
            System.out.println("NO");
        }
    }

    static boolean done = false;
//    static Set<Cube> seen = new HashSet<>();
    public static void solve(Cube cube, int vertex) {
        if (vertex == 8) {
            return;
        }
//        if(!seen.contains(cube)) {
            Set<Point> permutations = new HashSet<>();
            getPermute(cube.points[vertex], permutations);
//            if (permutations.size() > 1) {
//                seen.add(cube);
//            }
//            System.out.println(permutations);
            if (cube.isValid()) {
                System.out.println("YES");
                System.out.println(cube);
                done = true;
                return;
            }
            Point before = cube.points[vertex];
            for (Point permutation : permutations) {
                cube.points[vertex] = permutation;
                if (!done) {
                    solve(cube, vertex + 1);
                }
            }
            cube.points[vertex] = before;
//        }
    }

    private static void getPermute(Point point, Set<Point> list) {
        list.add(new Point(point.x, point.y, point.z));
        list.add(new Point(point.x, point.z, point.y));
        list.add(new Point(point.y, point.x, point.z));
        list.add(new Point(point.y, point.z, point.x));
        list.add(new Point(point.z, point.x, point.y));
        list.add(new Point(point.z, point.y, point.x));
    }

    static class Cube {
        Point[] points;
        int index;

        public Cube() {
            points = new Point[8];
            index = 0;
        }

        public void addPoint(Point x) {
            if (index == 8) {
                return;
            }
            points[index++] = x;
        }

        public boolean isValid() {
//            List<Long> dist = new ArrayList<>();
            Map<Long, Integer> count = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                Point check = points[i];
                for (int j = i + 1; j < points.length; j++) {
//                    System.out.println(check + " " + points[j]);
//                    dist.add(check.dist(points[j]));
                    long dist = check.dist(points[j]);
                    if (dist == 0) {
//                        System.out.println(check + " " + points[j]);
                        return false;
                    }
                    if (count.containsKey(dist)) {
                        int num = count.get(dist) + 1;
                        if (num > 12) {
                            return false;
                        }
                        count.put(dist, num);
                    } else {
                        count.put(dist, 1);
                    }
                    if (count.size() > 3) {
                        return false;
                    }
                }
            }
            // we know that there are 12 side len, 12 side len * sqrt(2), 4 side len * sqrt(3)
            List<Integer> values = new ArrayList<>(count.values());
//            System.out.println(count);
            Collections.sort(values);
            return values.get(0) == 4 && values.get(1) == 12 && values.get(2) == 12;
        }

        private boolean close(double a, double b) {
            return Math.abs(a - b) < .00000003;
        }

        public String toString() {
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                out.append(points[i].toString()).append("\n");
            }
            return out.toString();
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Cube && hashCode() == other.hashCode();
        }
    }

    static class Point {
        int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public long dist(Point other) {
            return (long) (other.x - x) * (other.x - x) + (long) (other.y - y) * (other.y - y) + (long) (other.z - z) * (other.z - z);
        }

        public String toString() {
            return x + " " + y + " " + z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            if (y != point.y) return false;
            if (z != point.z) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            result = 31 * result + z;
            return result;
        }
    }
}
