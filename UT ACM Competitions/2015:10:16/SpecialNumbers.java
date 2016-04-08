import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class SpecialNumbers {

    static List<Integer> primes = new ArrayList<Integer>();
    static Set<Integer> primeSet = new HashSet<Integer>();
    static Set<Integer> seen = new HashSet<Integer>();
    static List<Integer> primePairs = new ArrayList<Integer>();
    static {
        for (int i = 2; i < 1000; i++) {
            if (seen.add(i)) {
                primes.add(i);
                primeSet.add(i);
                int num = i;
                while (num < 1500) {
                    seen.add(num);
                    num += i;
                }
            }
        }
        for (int i = 0; i < primes.size(); i++) {
            for (int j = i + 1; j < primes.size(); j++) {
                primePairs.add(primes.get(i) * primes.get(j));
            }
        }
        Collections.sort(primePairs);
    }

    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("SpecialNumbers"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
//        System.out.println(primes);
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            int n = i(in.readLine());
            int spot = Collections.binarySearch(primePairs, n);
            if (spot < 0) {
                spot = ~spot;
            } else {
                spot++;
            }
            System.out.println(spot);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
