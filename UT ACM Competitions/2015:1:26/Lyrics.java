import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Lyrics {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Lyrics"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int X = i(data.nextToken()), Y = i(data.nextToken()), L = i(data.nextToken());
            Map<String, Integer> counts = new HashMap<String, Integer>();
            int validY = 0;
            for (int j = 0; j < L; j++) {
                data = new StringTokenizer(in.readLine().toLowerCase());
                while (data.hasMoreTokens()) {
                    String next = data.nextToken().replaceAll("\\W+_", "");
                    try {
                        Integer.parseInt(next);
                        continue;
                    } catch (Exception e) {
                        int f = 5;
                    }
                    Integer num = counts.containsKey(next) ? counts.get(next) : 0;
                    num++;
                    counts.put(next, num);
                }
            }
            for (Integer integer : counts.values()) {
                if (integer >= Y) {
                    validY++;
                }
            }
//            System.out.println(counts);
            System.out.println(validY >= X ? "WRITE" : "LET IT GO");
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
