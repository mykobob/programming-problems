import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Security {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader in = new BufferedReader(new FileReader("high_security.txt"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        BufferedWriter out = new BufferedWriter(new FileWriter("securityOutput.txt"));
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken());
            char[][] mat = new char[2][N];
            for (int j = 0; j < 2; j++) {
                mat[j] = in.readLine().toCharArray();
//                System.out.println(mat[j]);
            }
            List<Period> periods = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                int idx = 0;
                while (true) {
                    int startIdx = idx;
                    while (idx < N && mat[j][idx] == '.') {
                        idx++;
                    }
                    if (startIdx != idx) {
                        Period period = new Period(j, startIdx, idx - 1);
                        periods.add(period);
                    }
                    if (idx == N) {
                        break;
                    }
                    idx++;
                }
            }
            Collections.sort(periods);
            int guards = 0;
            for (int j = 0; j < periods.size(); j++) {
                Period period = periods.get(j);
                int row = period.row == 0 ? 1 : 0;
                int col = period.start;
                if (!period.covered) {
                    if (period.size() == 1) {
                        // we put guard on other row
                        guards++;
                        if (mat[row][col] == '.') {
                            for (int k = 0; k < periods.size(); k++) {
                                Period compare = periods.get(k);
                                if (compare.contains(row, col)) {
                                    compare.covered = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        guards++;
                        period.covered = true;
                        for (int k = 0; k < periods.size(); k++) {
                            Period compare = periods.get(k);
                            if (compare.contains(row, col)) {
                                compare.numCovered++;
                                if (compare.numCovered == compare.size()) {
                                    compare.covered = true;
                                }
                            }
                        }
                    }
                }
            }
            System.out.printf("Case #%d: %d\n", i + 1, guards);
            out.write(String.format("Case #%d: %d\n", i + 1, guards));
        }
        out.close();
    }

    static class Period implements Comparable<Period>{
        int row;
        int start, end;
        boolean covered;
        int numCovered;

        public Period(int row, int start, int end) {
            this.row = row;
            this.start = start;
            this.end = end;
            this.covered = false;
            this.numCovered = 0;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d) -> (%d, %d)", row, start, row, end);
        }

        public int size() {
            return end - start + 1;
        }

        public boolean contains (int row, int col) {
            if (row != this.row) {
                return false;
            }
            return start <= col && col <= end;
        }

        @Override
        public int compareTo(Period o) {
            return size() - o.size();
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
