import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: fact4
*/
public class fact4 {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("fact4.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int num = Integer.parseInt(in.readLine());
        int[] fact = new int[13];
        int[] rightMost = new int[13];
        fact[0] = 1;
        for (int i = 1; i < 13; i++) {
            fact[i] = i * fact[i-1];
        }
        for (int i = 0; i < 13; i++) {
            int tmp = fact[i];
            while(tmp > 0)
                if(tmp%10 != 0)
                {
                    rightMost[i] = tmp%10;
                    break;
                }
                else
                    tmp/=10;
        }
        System.out.println(Arrays.toString(fact));
        System.out.println(Arrays.toString(rightMost));

        out.close();
        System.exit(0);
    }
}
