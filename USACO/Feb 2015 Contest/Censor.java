import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Michael on 2/23/15.
 */
public class Censor {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("censor.in"));
        PrintWriter out = new PrintWriter("censor.out");
        StringBuilder str = new StringBuilder(in.nextLine());
        int censored = in.nextInt();
        in.nextLine();
        List<String> censors = new ArrayList<>();
        for (int i = 0; i < censored; i++) {
            String remove = in.nextLine();
            censors.add(remove);
        }
        while(true) {
            int bestStart = 1 << 30;
            int bestEnd = 1 << 30;
            int i;
            for (i = 0; i < censored; i++) {
                int start = str.indexOf(censors.get(i));
//                System.out.println(start);
                if(start < bestStart && start > 0) {
                    bestStart = start;
                    bestEnd = start + censors.get(i).length();
                }
            }
            if(bestStart == 1 << 30) {
                break;
            }
//            System.out.println(bestStart + " " + bestEnd);
            str = str.delete(bestStart, bestEnd);
        }
        System.out.println(str);
        out.println(str);
        out.close();
    }

    public static boolean none(StringBuilder out, List<String> censors) {
        for(String str : censors) {
            if(out.indexOf(str) > 0) {
                return false;
            }
        }
        return true;
    }
}
