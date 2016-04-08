import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACV {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("ACV"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int annual = 0, monthly = 0;
            int N = i(data.nextToken());
            for (int j = 0; j < N; j++) {
                StringTokenizer a = new StringTokenizer(in.readLine());
                a.nextToken();
                switch (a.nextToken()) {
                    case "Monthly":
                        monthly++;
                        break;
                    case "Annual":
                        annual++;
                        break;
                }
            }
            System.out.printf("%.2f\n", annual * 99.0 + monthly * 9.99 * 12);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
