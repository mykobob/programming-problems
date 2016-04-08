import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution538B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[] str = in.readLine().toCharArray();
        List<Integer> ans = new ArrayList<Integer>();
        while(!done(str)) {
            String step = step(str);
            ans.add(Integer.parseInt(step));
        }
        System.out.println(ans.size());
        for (int an : ans) {
            System.out.print(an + " ");
        }
        System.out.println();
    }

    public static boolean done(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if(str[i] > '0') {
                return false;
            }
        }
        return true;
    }

    public static String step(char[] str) {
        String ans = "";
        for (int i = 0; i < str.length; i++) {
            if(str[i] > '0') {
                ans += '1';
                str[i]--;
            } else {
                ans += '0';
            }
        }
        return ans;
    }
}
