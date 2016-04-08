import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Michael on 4/24/15.
 */
public class Number {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T -- >0) {
            String tmp = in.next();
            Character[] str = new Character[tmp.length()];
            for (int i = 0; i < tmp.length(); i++) {
                str[i] = tmp.charAt(i);
            }
            Arrays.sort(str, Collections.reverseOrder());
            for (int i = 0; i < str.length; i++) {
                System.out.print(str[i]);
            }
            System.out.println();
        }
    }
}
