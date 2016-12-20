import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution745B {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt(), M = in.nextInt();
        char[][] mat = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = in.nextLine();
            for (int j = 0; j < M; j++) {
                mat[i][j] = str.charAt(j);
            }
        }
        int[][] coords = getCorners(mat);
        System.out.println(filledIn(mat, coords) ? "YES" : "NO");
        out.close();
    }

    private static boolean filledIn(char[][] mat, int[][] coords) {
        for (int i = coords[0][0]; i <= coords[1][0]; i++) {
            for (int j = coords[0][1]; j <= coords[1][1]; j++) {
                if (mat[i][j] != 'X') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[][] getCorners(char[][] mat) {
        int R = -1, C = -1, r = mat.length + 1, c = mat[0].length + 1;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 'X') {
                    r = Math.min(r, i);
                    c = Math.min(c, j);
                    R = Math.max(R, i);
                    C = Math.max(C, j);
                }
            }
        }
        return new int[][]{{r,c},{R,C}};
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution745B.out")));
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

        public FAST() {
            in = new BufferedReader(new InputStreamReader(System.in));
            // in = new BufferedReader(new FileReader("Solution745B.in"));
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
