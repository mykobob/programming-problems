import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: humble
*/
public class humble {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("humble.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int size = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] tmp = in.readLine().split(" ");
        int[] primes = new int[size];
        for(int ii = 0;ii<tmp.length;ii++)
            primes[ii] = Integer.parseInt(tmp[ii]);
//        System.out.println(solve());
        int[] humble = new int[K+1];

        // all 0 at first
        int[] search = new int[size];

        humble[0] = 1;

        for(int ii = 0;ii<K;ii++){
            int next = Integer.MAX_VALUE;
            for(int jj = 0;jj<size;jj++){
                for(;primes[jj] * humble[search[jj]]<=humble[ii];search[jj]++);
                if(primes[jj] * humble[search[jj]]<next)
                    next = primes[jj] * humble[search[jj]];
            }
            humble[ii+1] = next;
//            System.out.println(Arrays.toString(search));

        }
//        System.out.println(Arrays.toString(humble));
//        System.out.println(Arrays.toString(search));
//        System.out.println(Arrays.toString(primes));

        System.out.println(humble[K]);
        out.println(humble[K]);

        out.close();
        System.exit(0);
    }
}
