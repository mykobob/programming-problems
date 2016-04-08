import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution219A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(in.readLine());
        String str = in.readLine();
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : str.toCharArray()) {
            if(count.containsKey(ch)) {
                count.put(ch, count.get(ch) + 1);
            } else {
                count.put(ch, 1);
            }
        }
        boolean good = true;
        for (char ch : count.keySet()) {
            if(count.get(ch) % K != 0) {
                System.out.println(-1);
                good = false;
                break;
            }
        }
        if(good) {
            String ans = "";
            for (char ch : count.keySet()) {
                int times = count.get(ch) / K;
                for (int i = 0; i < times; i++) {
                    ans += ch;
                }
            }
            String finalAns = "";
            for (int i = 0; i < K; i++) {
                finalAns += ans;
            }
            System.out.println(finalAns);
        }
    }
}
