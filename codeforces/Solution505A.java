import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution505A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String str = in.readLine();
        boolean good = false;
        outer:
        for (int i = 0; i < str.length() + 1; i++) {
            for(char ch = 'a'; ch <= 'z';ch ++) {
                String pali = str.substring(0, i) + ch + str.substring(i);
//                System.out.println(pali);
                if (new StringBuilder(pali).reverse().toString().equals(pali)) {
                    System.out.println(pali);
                    good = true;
                    break outer;
                }
            }
        }
        if (!good) {
            System.out.println("NA");
        }
    }
}
