import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Michael on 4/10/15.
 */
public class Standing {
    public static void main(String[] args) throws Exception {
//        Scanner in = new Scanner(new File("standing.in"));
        Scanner in = new Scanner(new File("A-large.in"));
        PrintWriter out = new PrintWriter("standing.out");
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int length = in.nextInt();
            String str = in.next();
            int[] data = new int[str.length()];
            for (int j = 0; j < str.length(); j++) {
                data[j] = str.charAt(j) - '0';
            }
            int standing = 0;
            int needed = 0;
            for (int needStanding = 0; needStanding < data.length;) {
                int val = data[needStanding];
                if(needStanding <= standing) {
                    int k = needStanding;
                    do
                    {
                        standing += data[k];
                        k++;
                    } while(k < data.length && k < needStanding + val);
                    needStanding = k;
                } else {
                    while(standing < needStanding) {
                        standing++;
                        needed++;
                    }
                    needStanding = standing;
                }
            }
//            System.out.println(needed);
            System.out.printf("Case #%d: %d\n", i + 1, needed);
            out.printf("Case #%d: %d\n", i + 1, needed);
        }
        out.close();
    }
}
