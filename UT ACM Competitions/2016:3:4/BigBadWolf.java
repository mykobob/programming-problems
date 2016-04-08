import java.io.*;
import java.util.*;

public class BigBadWolf {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("BigBadWolf.out")));
        // BufferedReader in = new BufferedReader(new FileReader("BigBadWolf.in"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        long T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            String[] str = in.readLine().split(" ");
            long C = i(str[0]), D = i(str[1]);
            long total = 0;
            if (C == 1 || D == 1) {
                System.out.println((C == 1 ? D : C) - 1);
            } else {
                while (!(C == 1 && D == 1)) {
                    if (C > D) {
                        total += C / D;
                        C %= D;
                    } else {
                        total += D / C;
                        D %= C;
                    }
                }
                System.out.println(total);
            }
        }
        out.close();
    }


    public static long i(String str) {
        return Long.parseLong(str);
    }
}
