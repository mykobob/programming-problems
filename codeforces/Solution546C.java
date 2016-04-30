import java.io.*;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution546C {

    static HashSet<String> frontier = new HashSet<String>();

    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt();
        ArrayDeque<Integer> a = new ArrayDeque<Integer>(10);
        ArrayDeque<Integer> b = new ArrayDeque<Integer>(10);
        int aSize = in.nextInt();
        for (int i = 0; i < aSize; i++) {
            a.add(in.nextInt());
        }
        int bSize = in.nextInt();
        for (int i = 0; i < bSize; i++) {
            b.add(in.nextInt());
        }
        int count = 0;
        boolean infinite = false;
        while(true) {
            if (a.isEmpty() || b.isEmpty()) {
                break;
            } else if (added(a, b)) {
                int aCompare = a.pollFirst();
                int bCompare = b.pollFirst();
                if (aCompare > bCompare) {
                    a.add(bCompare);
                    a.add(aCompare);
                } else {
                    b.add(aCompare);
                    b.add(bCompare);
                }
                ++count;
            } else {
                infinite = true;
                break;
            }
        }
        if (infinite) {
            System.out.println(-1);
        } else {
            if (a.isEmpty()) {
                System.out.println(count + " " + 2);
            } else if (b.isEmpty()) {
                System.out.println(count + " " + 1);
            } else {
                System.out.println(-1);
            }
        }

        out.close();
    }

    private static boolean added(ArrayDeque<Integer> a, ArrayDeque<Integer> b) {
        ArrayDeque<Integer> combined = new ArrayDeque<Integer>(a);
        combined.add(21);
        combined.addAll(b);
        return frontier.add(combined.toString());
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution546C.out")));
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
            // in = new BufferedReader(new FileReader("Solution546C.in"));
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
