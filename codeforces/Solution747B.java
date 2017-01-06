import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Solution747B {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        String input = in.nextLine();
        Map<Character, Long> charCounts =
                input
                    .chars()
                    .mapToObj(val -> (char) val)
                    .collect(groupingBy(Function.identity(), counting()));
        Long questionMarkCounts = charCounts.remove('?');
        if (questionMarkCounts == null) {
            questionMarkCounts = 0L;
        }

        long maxCount =
                charCounts.values().stream()
                        .max((a, b) -> (int) (a - b))
                        .orElse(0L);

        long needsReplacing =
                        maxCount * 4 - charCounts.values().stream()
                        .mapToLong(Long::valueOf)
                        .sum();

        if (needsReplacing > questionMarkCounts || (questionMarkCounts - needsReplacing) % 4 != 0) {
            System.out.println("===");
        } else {
            long totalCount = maxCount + (questionMarkCounts - needsReplacing) / 4;
            int index = 0;
            char[] bases = "ACGT".toCharArray();
            long[] counts = {getCount(charCounts, 'A'), getCount(charCounts, 'C'), getCount(charCounts, 'G'), getCount(charCounts, 'T')};
            for (int i = 0; i < T; i++) {
                char ch = input.charAt(i);
                if (ch == '?') {
                    while (counts[index] == totalCount) {
                        index++;
                    }
                    counts[index]++;
                    System.out.print(bases[index]);
                } else {
                    System.out.print(ch);
                }
            }
            System.out.println();
        }
        out.close();
    }
    
    private static long getCount(Map<Character, Long> map, char ch) {
        if (map.containsKey(ch)) {
            return map.get(ch);
        }
        return 0;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution747B.out")));
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
            // in = new BufferedReader(new FileReader("Solution747B.in"));
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
