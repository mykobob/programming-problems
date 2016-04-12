import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class CoinJam {

    public static void main(String... bob) throws Exception {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("CoinJam.out")));
        BufferedReader in = new BufferedReader(new FileReader("C-small-attempt0.in"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken()), J = i(data.nextToken());
            long num = (1L << N - 1) | 1;
            System.out.printf("Case #%d:\n", i + 1);
            out.printf("Case #%d:\n", i + 1);
            int count = 0;
            for (int j = 0; j < J; j++) {
                if (valid(num)) {
                    List<BigInteger> factors = factors(num);
                    StringBuilder ans = new StringBuilder();
                    ans.append(Long.toBinaryString(num));
                    for (int k = 0; k < factors.size(); k++) {
                        ans.append(" ");
                        ans.append(factors.get(k));
                    }
                    System.out.printf("%s\n", ans);
                    out.printf("%s\n", ans);
                    num += 2;
                } else {
                    num += 2;
                    --j;
                }
                ++count;
                System.out.println(count);
            }
        }
        out.close();
    }


    public static boolean valid(long num) {
        // check all bases 2 -> 10
        String binary = Long.toBinaryString(num);
        for (int base = 2; base <= 10; base++) {
            BigInteger numBase = new BigInteger(binary, base);
            if (numBase.isProbablePrime(10)) {
                return false;
            }
        }
        return true;
    }

    static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while(b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
            else a = mid.add(BigInteger.ONE);
        }
        return a.subtract(BigInteger.ONE);
    }

    static BigInteger[] sqrts = new BigInteger[11];
     static {
         for (int i = 2; i <= 10; i++) {
             long num = (1L << 31 | 1);
             sqrts[i] = sqrt(new BigInteger(Long.toBinaryString(num), i));
         }
     }


    public static List<BigInteger> factors(long num) {
        String binary = Long.toBinaryString(num);
        List<BigInteger> factors = new ArrayList<>();
        for (int base = 2; base <= 10; base++) {
            BigInteger numBase = new BigInteger(binary, base);
            BigInteger sqrt = sqrts[base];
            while (sqrt.multiply(sqrt).compareTo(numBase) < 0) {
                sqrt = sqrt.add(BigInteger.ONE);
            }
            sqrts[base] = sqrt;

            BigInteger i = new BigInteger("2");
            while (i.compareTo(sqrt) <= 0) {
                if (numBase.mod(i).compareTo(BigInteger.ZERO) == 0) {
                    factors.add(i);
                    break;
                }
                i = i.add(BigInteger.ONE);
            }
        }
        return factors;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
