import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Portmanteau {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = i(in.readLine().trim());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine().trim());
            int N = i(data.nextToken());
            int K = i(data.nextToken());
            String[] strs = new String[N];
            Set<String> seen = new HashSet<>();
            List<String> newOnes = new ArrayList<>();
            data = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                strs[j] = data.nextToken();
                seen.add(strs[j]);
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j != k) {
                        String newOne = combine(strs[j], strs[k], K);
                        if (newOne != null) {
                            if (seen.add(newOne)) {
                                newOnes.add(newOne);
                            }
                        }
                    }
                }
            }
            System.out.println(newOnes.size());
            Collections.sort(newOnes);
            for (String newOne : newOnes) {
                System.out.println(newOne);
            }
        }
    }

    private static String combine(String a, String b, int K) {
//        if (K >= b.length() || K >= a.length()) {
//            if (K >= a.length() && K >= b.length()) {
//                return a.equals(b) ? a : null;
//            }
//            if (K >= a.length()) {
//                return b.startsWith(a) ? b : null;
//            }
//            if (K >= b.length()) {
//                return a.endsWith(b) ? a : null;
//            }
//            return null;
//        }
        int num = 0;
        int aInd = a.length() - 1;
        int bInd = K - 1;
        while (num < K) {
            if (aInd == -1 || bInd == -1) {
                return null;
            }
//            System.out.println(a.charAt(aInd) + " " + b.charAt(bInd) + " " + num);
            if (a.charAt(aInd) != b.charAt(bInd)) {
                return null;
            }
            aInd--;
            bInd--;
            num++;
        }
//        System.out.println(a.substring(0, aInd) + b);
        String str = a.substring(0, aInd + 1) + b;
//        System.out.println(str + " " + aInd);
        return str;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
