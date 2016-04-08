import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class DJ {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("DJ"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int K = i(data.nextToken()), N = i(data.nextToken());
            int[] minutes = new int[N];
            data = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                minutes[j] = i(data.nextToken());
            }
            TreeMap<Integer, Integer> min = new TreeMap<Integer, Integer>();
            for (int j = 0; j < K; j++) {
                int a = minutes[j];
                if (min.containsKey(a)) {
                    min.put(a, min.get(a) + 1);
                } else {
                    min.put(a, 1);
                }
            }
            StringBuilder out = new StringBuilder(min.firstKey() + "");
            for (int j = K; j < N; j++) {
                int add = minutes[j];
                int remove = minutes[j - K];
                int a = min.get(remove);
                if (a == 1) {
                    min.remove(remove);
                } else {
                    min.put(remove, a);
                }
                if (min.containsKey(add)) {
                    min.put(add, min.get(add) + 1);
                } else {
                    min.put (add, 1);
                }
                out.append(" " + min.firstKey());
            }
            System.out.println(out);
        }
    }



    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
