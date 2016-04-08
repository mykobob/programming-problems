import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution320B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution320B"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            switch (i(data.nextToken())) {
                case 1: // add interval
                    int x = i(data.nextToken()), y = i(data.nextToken());
                    intervals.add(new Interval(intervals.size(), x, y));
                    break;
                case 2:
                    int a = i(data.nextToken()) - 1, b = i(data.nextToken()) - 1;
                    if (a >= intervals.size() || b >= intervals.size()) {
                        System.out.println("NO");
                    } else {
                        Interval from = intervals.get(a), to = intervals.get(b);
                        boolean ans = dfs(from, intervals, to, new boolean[intervals.size()]);
                        System.out.println(ans ? "YES" : "NO");
                    }
            }
        }
    }

    public static boolean dfs (Interval from, List<Interval> intervals, Interval target, boolean[] used) {
        if (from == null || used[from.index]) {
            return false;
        }
        if (from == target) {
            return true;
        }
        used[from.index] = true;
        for (Interval interval : intervals) {
            if (interval.within(from)) {
                boolean ans = dfs(interval, intervals, target, used);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    static class Interval {
        int index;
        int start, end;

        public Interval(int index, int start, int end) {
            this.index = index;
            this.start = start;
            this.end = end;
        }

        // returns true if to's interval is within this interval
        public boolean within(Interval to) {
            return start < to.start && to.start < end || start < to.end && to.end < end;
        }

        public int length() {
            return end - start;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
