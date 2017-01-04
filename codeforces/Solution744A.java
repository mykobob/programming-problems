import java.io.*;
import java.util.*;

public class Solution744A {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int N = in.nextInt(), M = in.nextInt(), K = in.nextInt();
        Integer[] capitals = new Integer[K];
        for (int i = 0; i < K; i++) {
            capitals[i] = in.nextInt();
        }

        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < M; i++) {
            int src = in.nextInt(), dest = in.nextInt();
            if (!edges.containsKey(src)) {
                edges.put(src, new ArrayList<>());
            }
            edges.get(src).add(dest);
            if (!edges.containsKey(dest)) {
                edges.put(dest, new ArrayList<>());
            }
            edges.get(dest).add(src);
        }

        int count = (int) Arrays.stream(capitals).filter(edges::containsKey).count();
        if (count > 1) { // already have a capital in edges
            Optional<Integer> bestCap = Arrays.stream(capitals).filter(edges::containsKey).max((c1, c2) -> (edges.get(c1).size() - edges.get(c2).size()));

            int capId = bestCap.get();
            int maxCount = edges.get(capId).size();
            int nonCapitals = N - K - count;
            int nonCapitalEdges = numEdges(nonCapitals);
            System.out.printf("%d %d%n", nonCapitals, nonCapitalEdges);
            System.out.println(nonCapitalEdges + nonCapitals - M);

        } else { // no capitals in edges or only 1 capital in edges
            int nonCapitals = N - K;
            int nonCapitalEdges = numEdges(nonCapitals);
            System.out.println(nonCapitalEdges + nonCapitals - M);
        }

        out.close();
    }

    private static int numEdges(int numNodes) {
        return numNodes * (numNodes - 1) >> 1;
    }
    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution744A.out")));
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
            // in = new BufferedReader(new FileReader("Solution744A.in"));
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
