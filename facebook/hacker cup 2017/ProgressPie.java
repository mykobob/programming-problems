import java.io.*;
import java.util.StringTokenizer;

public class ProgressPie {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 1; i <= T; i++) {
            int progress = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            boolean inCircle = distanceFromMiddle(x, y) <= 50;
            boolean inArc = inArc(progress, x, y);
            out.caseStr(i, inCircle && inArc ? "black" : "white");
        }

        out.close();
    }

    private static boolean inArc(int progress, int x, int y) {
        double progressAngle = progress / 100.0 * 360;
        double pointAngle = calcAngle(x, y);
        return pointAngle <= progressAngle;
    }

    private static double calcAngle(int x, int y) {
        double a = distanceFromMiddle(x, y);
        double b = 50;
        double c = Math.hypot(x - 50, y - 100);

        // law of cosines
        double angle = Math.acos((a * a + b * b - c * c) / (2 * a * b));
        double degrees = angle * 180 / Math.PI;

        if (x <= 50) {
            if (x == 50) {
                return 180;
            }
            return 360 - degrees;
        } else {
            return degrees;
        }
    }

    private static double distanceFromMiddle(int x, int y) {
        return Math.hypot(x - 50, y - 50);
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("ProgressPie.out")));
            } catch (Exception e) {

            }
        }

        public void caseInt(int num, int ans) {
            caseStr(num, String.valueOf(ans));
        }

        public void caseStr(int num, String ans) {
            out.printf("Case #%d: %s\n", num, ans);
        }

        public void writeln(String str) {
            out.println(str);
        }

        public void write(String str) {
            out.print(str);
        }

        public void close() {
            out.close();
        }
    }

    static class FAST {
        private BufferedReader in;
        private StringTokenizer str;

        public FAST() throws FileNotFoundException {
//            in = new BufferedReader(new InputStreamReader(System.in));
             in = new BufferedReader(new FileReader("progress_pie.txt"));
        }

        private String next() {
            while (str == null || !str.hasMoreElements()) {
                try {
                    str = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                }
            }
            return str.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() throws Exception {
            return in.readLine();
        }
    }
}
