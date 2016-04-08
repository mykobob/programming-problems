import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boomerang {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new FileReader("boomerang_constellations.txt"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken());
            List<Node> nodes = new ArrayList<Node>();
            for (int j = 0; j < N; j++) {
                data = new StringTokenizer(in.readLine());
                nodes.add(new Node(i(data.nextToken()), i(data.nextToken())));
            }

            for (int j = 0; j < nodes.size(); j++) {
                Node cur = nodes.get(j);
                for (int k = 0; k < nodes.size(); k++) {
                    int dist = cur.dist(nodes.get(k));
                    int num = cur.nborCount.get(dist) == null ? 0 : cur.nborCount.get(dist);
                    cur.nborCount.put(dist, num + 1);
                }
            }
            long total = 0;
            for (int j = 0; j < nodes.size(); j++) {
                Map<Integer, Integer> nborCount = nodes.get(j).nborCount;
                for (int combo : nborCount.values()) {
                    total += combo * (combo - 1) / 2;
                }
            }
            System.out.printf("Case #%d: %d\n", i + 1, total);
        }
    }

    static class Node {
        Map<Integer, Integer> nborCount;
        int x, y;

        public Node(int x, int y) {
            nborCount = new HashMap<>();
            this.x = x;
            this.y = y;
        }

        public int dist(Node other) {
            return (int) Math.pow(other.x - x, 2) + (int) Math.pow(other.y - y, 2);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
