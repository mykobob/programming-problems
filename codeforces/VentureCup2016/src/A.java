import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class A {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("A"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        String str = in.readLine();
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                ans += solve (str, i, j) ? 1 : 0;
            }
        }
        System.out.println(ans);
    }

    static Map<String, Integer> map = new HashMap<String, Integer>();
    static {
        map.put("U", 0);
        map.put("R", 1);
        map.put("D", 2);
        map.put("L", 3);
    }

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static boolean solve(String str, int i, int j) {
        int x = 0, y = 0;
        for (int k = i; k <= j; k++) {
            x += dx[map.get(str.charAt(k)+"")];
            y += dy[map.get(str.charAt(k)+"")];
        }
        return x == 0 && y == 0;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
