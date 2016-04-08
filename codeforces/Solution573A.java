import sun.security.util.BigInt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution573A {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution573A"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = (int) i(testCases.nextToken());
        BigInteger[] nums = new BigInteger[N];
        StringTokenizer strs = new StringTokenizer(in.readLine());
        int num = 0;
        while(strs.hasMoreTokens()) {
            nums[num++] = new BigInteger(strs.nextToken());
        }
//        Arrays.sort(nums, Collections.reverseOrder());
//        System.out.println(Arrays.toString(nums));

        BigInteger lcm = BigInteger.ONE;
        boolean good = true;
        for (int i = 0; i < N; i++) {
            lcm = lcm(lcm, nums[i]);
            if (!valid(lcm.divide(nums[i]))) {
                System.out.println("No");
                good = false;
                break;
            }
        }
        if (good) {
            for (int i = 0; i < N; i++) {
                if (!valid(lcm.divide(nums[i]))) {
                    System.out.println("No");
                    good = false;
                    break;
                }
            }
            if (good) {
                System.out.println("Yes");
            }
        }
//        BigInteger lcm = lcm(nums[0], nums[1]);
//        if (!valid(lcm.divide(nums[0])) || !valid(lcm.divide(nums[1]))) {
//            System.out.println(lcm);
//            System.out.println("No");
//        } else {
//            System.out.println("start");
//            boolean good = true;
//            for (int i = 2; i < N; i++) {
//                lcm = lcm(lcm, nums[i]);
//                System.out.println(lcm);
//                System.out.println(lcm.divide(nums[i]));
//                if (!valid(lcm.divide(nums[i]))) {
//                    System.out.println("No");
//                    good = false;
//                    break;
//                }
//            }
//            if (good) {
////                System.out.println(lcm);
//                System.out.println("Yes");
//            }
//        }
    }

    public static boolean valid (BigInteger a) {
//        BigInteger even = even(a);
//        BigInteger triple = triple(even);
        BigInteger other = even(a);
//        System.out.println("break");
        other = triple(other);
        return a.equals(BigInteger.ONE) || other.equals(BigInteger.ONE);
    }

    public static BigInteger even (BigInteger a) {
//        a.mod(BigInteger.ONE.add(BigInteger.ONE)).equals(BigInteger.ZERO);
        BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
        while (a.mod(two).equals(BigInteger.ZERO)) {
            a = a.divide(two);
//            System.out.println(a);
        }
        return a;
    }

    public static BigInteger triple (BigInteger a) {
//        return a.mod(BigInteger.ONE.add(BigInteger.ONE).add(BigInteger.ONE)).equals(BigInteger.ZERO);
        BigInteger three = BigInteger.ONE.add(BigInteger.ONE).add(BigInteger.ONE);
        while (a.mod(three).equals(BigInteger.ZERO)) {
            a = a.divide(three);
//            System.out.println(a);
        }
        return a;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(gcd(a, b));
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        }
        return gcd (b, a.mod(b));
    }

    public static long i(String str) {
        return Long.parseLong(str);
    }
}
