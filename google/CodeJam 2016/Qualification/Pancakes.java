import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Pancakes {
    public static void main(String... bob) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Pancakes.out")));
        BufferedReader in = new BufferedReader(new FileReader("B-small-attempt1.in"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringBuilder data = new StringBuilder(in.readLine());
            int count = 0;
            while (!allPos(data)) {
                int idx;
                if (data.charAt(0) == '+') {
                    idx = data.indexOf("-");
                } else {
                    idx = data.indexOf("+");
                }
                if (idx < 0) {
                    idx = data.length();
                }
//                System.out.println(idx);
                for (int j = 0; j < idx; j++) {
                    data.setCharAt(j, data.charAt(j) == '+' ? '-' : '+');
                }
                ++count;
            }
            System.out.printf("Case #%d: %d\n", i + 1, count);
            out.printf("Case #%d: %d\n", i + 1, count);
        }
        out.close();
    }

    public static boolean allPos(StringBuilder data) {
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '-') {
                return false;
            }
        }
        return true;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
