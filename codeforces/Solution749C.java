import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution749C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        String votes = in.nextLine();
        TreeSet<Integer> democrats = new TreeSet<>();
        TreeSet<Integer> republicans = new TreeSet<>();
        for (int i = 0; i < votes.length(); i++) {
            if (votes.charAt(i) == 'D') {
                democrats.add(i);
            } else {
                republicans.add(i);
            }
        }

        int index = 0;
        while (!democrats.isEmpty() && !republicans.isEmpty()) {
            if (index == votes.length()) {
                index = 0;
            }
            if (votes.charAt(index) == 'D' && democrats.contains(index)) {
                Integer spot = republicans.ceiling(index + 1);
                if (spot == null) {
                    republicans.pollFirst();
                } else {
                    republicans.remove(spot);
                }
            } else if (votes.charAt(index) == 'R' && republicans.contains(index)){
                Integer spot = democrats.ceiling(index + 1);
                if (spot == null) {
                    democrats.pollFirst();
                } else {
                    democrats.remove(spot);
                }
            }
            index++;
//            System.out.println(democrats);
//            System.out.println(republicans);
        }
        System.out.println(democrats.isEmpty() ? "R" : "D");

        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution749C.out")));
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
            // in = new BufferedReader(new FileReader("Solution749C.in"));
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
