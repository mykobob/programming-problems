import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class GettingTheDigits {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            String str = in.nextLine();
            Map<Character, Integer> counts = new HashMap<>();
            for (char j = 'A'; j <= 'Z'; ++j) {
                counts.put(j, 0);
            }
            for (int j = 0; j < str.length(); j++) {
                counts.put(str.charAt(j), counts.get(str.charAt(j)) + 1);
            }
            // zero, six
            StringBuilder ans = new StringBuilder();
            int[] ordering = {0, 6, 8, 2, 4, 5, 7, 3, 9, 1};
            for (int j = 0; j < ordering.length; j++) {
                while (update(ordering[j], counts)) {
                    ans.append(ordering[j]);
                }
            }
//            System.out.println(counts);
//            System.out.println(ans.toString());
            if (!allZero(counts)) {
                System.out.println(i + 2 + " " + "Bad");
            }
            char[] tmp = ans.toString().toCharArray();
            Arrays.sort(tmp);
            out.caseStr(i + 1, new String(tmp));
        }

        out.close();
    }

    private static boolean allZero(Map<Character, Integer> counts) {
        for (Map.Entry<Character, Integer> e : counts.entrySet()) {
            if (e.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    static String[] strs = "ZERO ONE TWO THREE FOUR FIVE SIX SEVEN EIGHT NINE".split(" ");
    public static boolean update(int i, Map<Character, Integer> counts) {
        String str = strs[i];
        Map<Character, Integer> check = mapIt(str);
        for (int j = 0; j < str.length(); j++) {
            char ch = str.charAt(j);
            if (check.get(ch) > counts.get(ch)) {
                return false;
            }
        }
        for (int j = 0; j < str.length(); j++) {
            char ch = str.charAt(j);
            counts.put(ch, counts.get(ch) - 1);
        }
        return true;
    }

    public static Map<Character, Integer> mapIt(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("GettingTheDigits.out")));
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
             in = new BufferedReader(new FileReader("A-large-practice.in"));
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
