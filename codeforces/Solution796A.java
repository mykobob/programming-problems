import java.io.*;
import java.util.StringTokenizer;

public class Solution796A {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int numHouses = in.nextInt(), house = in.nextInt() - 1, dollars = in.nextInt();
        int[] housePrices = new int[numHouses];
        for (int i = 0; i < numHouses; i++) {
            housePrices[i] = in.nextInt();
        }
        int leftPtr = house - 1, rightPtr = house + 1;
        while (true) {
            if (leftPtr <= -1 && rightPtr >= numHouses) {
                break;
            }
            if (leftPtr >= 0) {
                if (housePrices[leftPtr] != 0 && housePrices[leftPtr] <= dollars) {
                    System.out.println((house - leftPtr) * 10);
                    break;
                }
            }
            if (rightPtr < numHouses) {
                if (housePrices[rightPtr] != 0 && housePrices[rightPtr] <= dollars) {
                    System.out.println((rightPtr - house) * 10);
                    break;
                }
            }
            leftPtr--;
            rightPtr++;
        }
        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution796A.out")));
            } catch (Exception e) {

            }
        }

        public void caseAns(int num, int ans) {
            caseAns(num, String.valueOf(ans));
        }

        public void caseAns(int num, String ans) {
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

        public FAST() throws Exception {
            in = new BufferedReader(new InputStreamReader(System.in));
            // in = new BufferedReader(new FileReader("Solution796A.in"));
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
