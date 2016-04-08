import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution92B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution92B"));
        char[] num = in.readLine().toCharArray();
        int index = num.length - 1;
        int count = 0;
        outer:
        while (index != 0) {
//            System.out.print(num);
//            System.out.println(" " + index + " " + count);
            switch (num[index]) {
                case '0': // even
                    while (num[index] == '0') {
                        index--;
                        count++;
                    }
                    break;
                case '1':
                    int tmpIndex = index;
                    while (num[index] == '1') {
                        num[index] = '0';
                        index--;
                        if (index < 0) {
                            count ++; // for the +1
                            count += tmpIndex + 1; // for the right shifting
                            break outer;
                        }
                    }
                    num[index] = '1';
                    index = tmpIndex;
                    count++;
                    break;
            }
        }
        System.out.println(count);
    }

    public static int i(String str, int base) {
        return Integer.parseInt(str, base);
    }
}
