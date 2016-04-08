import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Blog {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Blog"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            String str = in.readLine();
            if (str.equals("Dropbox")) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
