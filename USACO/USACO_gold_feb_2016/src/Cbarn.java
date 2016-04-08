import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Cbarn {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader in = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        int[] numCows = new int[N];
        for (int i = 0; i < N; i++) {
            numCows[i] = i(in.readLine());
        }
        System.out.println(Arrays.toString(numCows));
        List<Interval> intervals = new ArrayList<>();
        int i = 0;
        while (i < N) {
            if (numCows[i] != 0) {
                int end = i + 1;
                int total = numCows[i];
                while (end < N && numCows[end] != 0) {
                    total += numCows[end];
                    end++;
                }
                intervals.add(new Interval(i, end - 1, total, N));
                i = end;
            } else {
                i++;
            }
        }
        if (intervals.size() > 1) {
            Interval first = intervals.get(0);
            Interval last = intervals.get(intervals.size() - 1);
            if (first.start == 0 && last.end == N - 1) {
                intervals.set(0, new Interval(last.start, first.end, first.size + last.size, N));
                intervals.get(0).wraps = true;
                intervals.remove(intervals.size() - 1);
            }
        }
        System.out.println(intervals);
        int start = -1;
        for (int j = 0; j < intervals.size() - 1; j++) {
            Interval a = intervals.get(j);
            Interval b = intervals.get(j + 1);
            int diff = b.start - a.end;
            if (a.size - a.getIntervalSize() <= diff) {
                start = j;
            }
        }

    }

    static class Interval {
        int start, end;
        int TOTAL;
        int size;
        boolean wraps;

        public Interval(int start, int end, int size, int TOTAL) {
            this.start = start;
            this.end = end;
            this.wraps = false;
            this.size = size;
            this.TOTAL = TOTAL;
        }

        public boolean done () {
            return getIntervalSize() == size;
        }

        public int getIntervalSize() {
            if (start <= end) {
                return end - start + 1;
            } else {
                return (TOTAL - end) + start + 1;
            }
        }

        public void merge(Interval other) {

        }

        public String toString() {
            return String.format("%d -> %d, %d...", start, end, size);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
