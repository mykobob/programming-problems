import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution752B {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        String key = in.nextLine();
        String typed = in.nextLine();
        Map<Character, Character> mapping = new HashMap<>();

        boolean good = true;
        for (int i = 0; i < key.length(); i++) {
            char k = key.charAt(i);
            char t = typed.charAt(i);
            boolean kContains = mapping.containsKey(k);
            boolean tContains = mapping.containsKey(t);
            if (!kContains && !tContains) {
                mapping.put(k, t);
                mapping.put(t, k);
            } else {
                if (kContains) {
                    if (t != mapping.get(k)) {
                        System.out.println(-1);
                        good = false;
                        break;
                    }
                }
                if (tContains) {
                    if (k != mapping.get(t)) {
                        System.out.println(-1);
                        good = false;
                        break;
                    }
                }
            }
        }

        if (good) {
            System.out.println(mapping.entrySet().stream().filter(entry -> entry.getKey() != entry.getValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).size() >> 1);
            Set<Character> seen = new HashSet<>();
            for (Map.Entry<Character, Character> entry : mapping.entrySet()) {
                if (seen.add(entry.getKey()) && seen.add(entry.getValue())) {
                    System.out.printf("%s %s%n", entry.getKey(), entry.getValue());
                }
            }
        }
        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution752B.out")));
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
            // in = new BufferedReader(new FileReader("Solution752B.in"));
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
