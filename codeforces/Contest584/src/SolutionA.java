import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class SolutionA {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("SolutionA"));
        StringTokenizer data = new StringTokenizer(in.readLine());
        int N = i(data.nextToken());
        int T = i(data.nextToken());
        if (N == 1 && T == 10) {
            System.out.println(-1);

        } else {
            String str = "1";
            for (int i = 1; i < N; i++) {
                str += "0";
            }
            BigInteger lowerBound = new BigInteger(str);
            str += "0";
            BigInteger higherBound = new BigInteger(str);
            BigInteger[] lowerDivide = lowerBound.divideAndRemainder(new BigInteger("" + T));
            BigInteger[] higherDivide = higherBound.divideAndRemainder(new BigInteger("" + T));
            if (higherDivide[0].compareTo(lowerDivide[0]) <= 0) { // can't be done
                System.out.println(-1);
            } else {
                System.out.println((lowerDivide[0].add(BigInteger.ONE)).multiply(new BigInteger("" + T)));
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
