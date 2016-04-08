import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution166E {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//         BufferedReader in = new BufferedReader(new FileReader("Solution166E"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int length = i(testCases.nextToken());
        long endD = 1;
        long endOther = 0;
        long MOD = (long) (1e9 + 7);
        for (int i = 0; i < length; i++) {
            long newEndD = endOther * 3 % MOD;
            long newEndOther = (endD + endOther * 2) % MOD;
            endD = newEndD;
            endOther = newEndOther;
        }
        System.out.println(endD);
    }


    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
