import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution747D {
    public static void main(String... bob) throws Exception {
        solve();
    }

    private static void solve() {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int totalDays = in.nextInt(), usableDays = in.nextInt();
        int[] temps = new int[totalDays];
        for (int i = 0; i < totalDays; i++) {
            temps[i] = in.nextInt();
        }
        List<Segment> segments = new ArrayList<>();
        List<Integer> negIndexes = IntStream.range(0, totalDays).filter(i -> temps[i] < 0).boxed().collect(Collectors.toList());
        if (negIndexes.size() > usableDays) {
            System.out.println(-1);
            return;
        }

        if (negIndexes.size() == 0) {
            System.out.println(0);
            return;
        }

        usableDays -= negIndexes.size();
        int index = 0;
        while (index + 1 < negIndexes.size()) {
            int endIndex = index + 1;
            int start = negIndexes.get(index);
            int end = negIndexes.get(endIndex);
            Segment toAdd = new Segment(start, end);
            if (toAdd.dist() > 0) {
                segments.add(toAdd);
            }
            index = endIndex;
        }

        Collections.sort(segments);
//        System.out.println(segments);

        int segIdx = 0;
        while (segIdx < segments.size()) {
            Segment seg = segments.get(segIdx);
            if (usableDays < seg.dist()) {
                break;
            }
            usableDays -= seg.dist();
            segIdx++;
        }

        long times = (segments.size() - segIdx) * 2 + 1;
        if (usableDays < new Segment(negIndexes.get(negIndexes.size() - 1), totalDays).dist()) {
            times++;
        }

        System.out.println(times);


        out.close();
    }


    static class Segment implements Comparable<Segment> {
        int start, end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private int dist() {
            return end - start - 1;
        }

        @Override
        public int compareTo(Segment o) {
            return dist() - o.dist();
        }

        @Override
        public String toString() {
            return String.format("%d -> %d", start, end);
        }
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution747D.out")));
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
            // in = new BufferedReader(new FileReader("Solution747D.in"));
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
