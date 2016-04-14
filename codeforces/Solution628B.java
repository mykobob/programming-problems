import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution628B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Solution628B.out")));
        // BufferedReader in = new BufferedReader(new FileReader("Solution628B.in"));
        String data = in.readLine();
        long total = 0;
        for (int i = 0; i < data.length(); i++) {
//            System.out.println("048".indexOf(data.charAt(i)+""));
            if ("048".contains(data.charAt(i) + "")) {
                ++total;
            }
        }
        for (int i = 0; i < data.length() - 1; i++) {
            if (i(data.substring(i, i + 2)) % 4 == 0) {
                total += i + 1;
            }
        }
        System.out.println(total);

        out.close();
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
