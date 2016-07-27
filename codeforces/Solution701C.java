import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution701C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        String pokemon = in.nextLine();
        Set<Character> types = new HashSet<>();
        for (int i = 0; i < pokemon.length(); i++) {
            types.add(pokemon.charAt(i));
        }

        int low = 1;
        int high = T;
        outer:
        while (low < high) {
            int mid = (low + high) / 2;
//            System.out.println(low + " " + mid + " " + high);
            HashMap<Character, Integer> typesSeen = new HashMap<>();
            for (int i = 0; i < mid; i++) {
                char ch = pokemon.charAt(i);
                int got = typesSeen.containsKey(ch) ? typesSeen.get(ch) : 0;
                got++;
                typesSeen.put(ch, got);
            }
//            System.out.println(typesSeen);
            if (typesSeen.size() == types.size()) { // we can do it at this size
                high = mid;  // we keep this size, but try a smaller size
            } else {
                int lower = 0;
                int higher = mid;
                while (higher < T) {

                    char lowerCh = pokemon.charAt(lower);
                    char higherCh = pokemon.charAt(higher);
                    if (typesSeen.containsKey(lowerCh)) {
                        int got = typesSeen.get(lowerCh);
                        if (got == 1) {
                            typesSeen.remove(lowerCh);
                        } else {
                            typesSeen.put(lowerCh, got - 1);
                        }
                    }

                    int got = typesSeen.containsKey(higherCh) ? typesSeen.get(higherCh) : 0;
                    got++;
                    typesSeen.put(higherCh, got);

//                    System.out.println(typesSeen);
                    if (typesSeen.size() == types.size()) { // we can do it at this size
                        high = mid;  // we keep this size, but try a smaller size
                        continue outer;
                    }
                    lower++;
                    higher++;
                }
                // we can't do it at this size, so we try at a bigger size
                low = mid + 1;
            }
        }
        System.out.println(low);

        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution701C.out")));
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
            // in = new BufferedReader(new FileReader("Solution701C.in"));
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
