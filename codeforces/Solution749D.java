import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Solution749D {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        int T = in.nextInt();
        TreeMap<Integer, List<Integer>> bidsMap = new TreeMap<>();
        TreeMap<Integer, Integer> bestBidsTmp = new TreeMap<>();
        TreeMap<Integer, Integer> bestBids = new TreeMap<>();
        for (int i = 0; i < T; i++) {
            int person = in.nextInt();
            int bid = in.nextInt();
            if (!bidsMap.containsKey(person)) {
                bidsMap.put(person, new ArrayList<Integer>());
            }
            bidsMap.get(person).add(bid);
            bestBidsTmp.compute(person, (curPerson, curBid) -> curBid == null ? bid : Math.max(curBid, bid));
        }
        bestBidsTmp.forEach((person, bestBid) -> bestBids.put(bestBid, person));

        int queries = in.nextInt();
        for (int i = 0; i < queries; i++) {
            int K = in.nextInt();
            int[] toRemove = new int[K];
            for (int j = 0; j < K; j++) {
                int remove = in.nextInt();
                if (bidsMap.containsKey(remove)) {
                    List<Integer> bids = bidsMap.get(remove);
                    bestBids.remove(bids.get(bids.size() - 1));
                }
                toRemove[j] = remove;
            }
//            System.out.println(bestBids);
            int[] firstAns = getFirstAns(bidsMap, bestBids);
//            System.out.println(bestBids);
            System.out.println(firstAns[0] + " " + firstAns[1]);
            for (int j = 0; j < K; j++) {
                int person = toRemove[j];
                List<Integer> personBids = bidsMap.get(person);
                if (personBids != null) {
                    bestBids.put(personBids.get(personBids.size() - 1), person);
                }
            }
//            System.out.println(bestBids);
//            System.out.println();
        }

        out.close();
    }

    private static int[] getFirstAns(TreeMap<Integer, List<Integer>> bidsMap, TreeMap<Integer, Integer> bestBids) {
        int[] ans = null;
        // bidsMap: person -> list of bids
        if (bestBids.isEmpty()) {
//            System.out.println("A");
            ans = new int[] {0, 0};
        } else {
            Map.Entry<Integer, Integer> bestPersonEntry = bestBids.pollLastEntry();
            int bestBid = bestPersonEntry.getKey();
            int bestPerson = bestPersonEntry.getValue();
            if (bestBids.isEmpty()) {
//                System.out.println("B");
                ans = new int[] { bestPerson, bidsMap.get(bestPerson).get(0) };
            } else {
                Map.Entry<Integer, Integer> nextBestEntry = bestBids.lastEntry();
                int secondBestBid = nextBestEntry.getKey();

                List<Integer> bestPersonBids = bidsMap.get(bestPerson);
                int index = ~Collections.binarySearch(bestPersonBids, secondBestBid);
                ans = new int[]{bestPerson, bestPersonBids.get(index)};

            }
            bestBids.put(bestBid, bestPerson);
        }
        return ans;
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution749D.out")));
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
            // in = new BufferedReader(new FileReader("Solution749D.in"));
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
