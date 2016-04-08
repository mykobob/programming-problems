import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class CaesarCipher {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("CaesarCipher.out")));
        // BufferedReader in = new BufferedReader(new FileReader("CaesarCipher.in"));
//        StringTokenizer testCases = new StringTokenizer(in.readLine());
        String[] val = "A L N M T H I S O U R G J K P Q V W X Y Z B C D E F".split(" ");
        Map<Character, Character> mapping = new HashMap<>();
        for (int i = 0; i < val.length; i++) {
            mapping.put((char)('a' + i), val[i].charAt(0));
        }
        String str = in.readLine();
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                System.out.print(mapping.get(ch));
            } else {
                System.out.print(ch);
            }
        }
        System.out.println();
//        LinkedHashSet<Character> phase = new LinkedHashSet<>();
//        for (char ch : str.toCharArray()) {
//            if (Character.isLetter(ch)) {
//                phase.add(ch);
//            }
//        }
//        int start = (str.charAt(str.length() - 1) - 'A');
//        int iter = start;
//        do {
//            phase.add((char)(iter + 'A'));
//            iter++;
//            iter %= 26;
//        } while (start != iter);
//        for (Character ch : phase) {
//            System.out.print(ch + " ");
//        }
//        System.out.println();
//        out.close();
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
