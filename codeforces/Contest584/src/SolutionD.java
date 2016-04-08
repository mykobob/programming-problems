import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolutionD {

    static List<Integer> primes = new ArrayList<Integer>();
//    static Set<Integer> visited = new HashSet<Integer>();
    static {
        int N = 1000000000;

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        // count primes
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
                if (primes.size() > 100000) {
                    System.out.println(primes.size());
                }
            }
        }
    }

    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("SolutionD"));
        System.out.println(primes);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
