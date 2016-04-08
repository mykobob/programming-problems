import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionC {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("SolutionC"));
        StringTokenizer data = new StringTokenizer(in.readLine());
        int n = i(data.nextToken());
        int t = i(data.nextToken());
        String a = in.readLine();
        String b = in.readLine();
        StringBuilder out = new StringBuilder();
        dance:
        for (int i = 0; i < n; i++) {
            if (t == 0) {
                for (int j = i; j < n; j++) {
                    if (a.charAt(j) != b.charAt(j)) {
                        System.out.println(-1);
                        break dance;
                    }
                }
            }

        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
