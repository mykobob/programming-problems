import java.io.*;
import java.util.StringTokenizer;

public class FightingTheZombie {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int damageNeeded = in.nextInt();
            int numSpells = in.nextInt();
            Distribution[] spells = new Distribution[numSpells];
            for (int j = 0; j < numSpells; j++) {
                spells[j] = Distribution.create(in.next());
            }

            double bestChoice = 0;
            for (Distribution spell : spells) {
                double curProbability = spell.calcProbability(damageNeeded);
                bestChoice = Math.max(bestChoice, curProbability);
            }
            out.caseAns(i + 1, String.format("%.6f", bestChoice));
        }

        out.close();
    }

    static class Distribution {
        int numTimes;
        int upper;
        int offset;
        double[][] probs;

        public Distribution(int numTimes, int upper, int offset) {
            this.numTimes = numTimes;
            this.upper = upper;
            this.offset = offset;
            this.probs = new double[numTimes + 1][(numTimes) * upper + 1];
            calcProbabilities();
        }

        private void calcProbabilities() {
            for (int numDices = 1; numDices <= numTimes; numDices++) {
                for (int valToGet = numDices; valToGet <= numDices * upper; valToGet++) {
                    if (numDices == 1) {
                        probs[numDices][valToGet] = 1.0/upper;
                    } else {
                        for (int curDie = 1; curDie <= upper; curDie++) {
                            int otherVal = valToGet - curDie;
                            if ((numDices - 1) <= otherVal && otherVal <= (numDices - 1) * upper) {
                                probs[numDices][valToGet] += probs[numDices - 1][otherVal] * 1.0 / upper;
                            }
                        }
                    }
                }
            }
        }

        public void printProbabilities() {
            for (int i = 0; i < probs.length; i++) {
                for (int j = 0; j < probs[i].length; j++) {
                    System.out.printf("%.6f ", probs[i][j]);
                }
                System.out.println();
            }
        }

        public double calcProbability(int val) {
            val -= offset;
            double probability = 0;
            val = Math.max(0, val);
            for (int i = val; i <= numTimes * upper; i++) {
                probability += probs[numTimes][i];
            }
            return probability;
        }

        public static Distribution create(String str) {
            int d = str.indexOf("d");
            int numTimes = Integer.parseInt(str.substring(0, d));
            int offsetIdx = str.indexOf("+");
            if (offsetIdx == -1) {
                offsetIdx = str.indexOf("-");
            }
            if (offsetIdx == -1) {
                offsetIdx = str.length();
            }
            int numSides = Integer.parseInt(str.substring(d + 1, offsetIdx));

            int offset = 0;
            if (offsetIdx != str.length()) {
                offset = Integer.parseInt(str.substring(offsetIdx));
            }
            return new Distribution(numTimes, numSides, offset);
        }
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("FightingTheZombie.out")));
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
//            in = new BufferedReader(new InputStreamReader(System.in));
             in = new BufferedReader(new FileReader("fighting_the_zombie.txt"));
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
