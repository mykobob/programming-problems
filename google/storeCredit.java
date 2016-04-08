import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: storeCredit
*/
public class storeCredit {
    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("A-large-practice.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("storeCredit.out")));
        int testCases = in.nextInt();
        outer:
        for(int ii = 0;ii<testCases;ii++){
            int cap = in.nextInt();
            in.nextInt();
            in.nextLine();
            String[] strUses = in.nextLine().split(" ");
            int[] uses = new int[strUses.length];
            for(int jj = 0;jj<strUses.length;jj++)
                uses[jj] = Integer.parseInt(strUses[jj]);
            for(int jj = 0;jj<uses.length;jj++){
                for(int kk = jj+1;kk<uses.length;kk++){
                    if(uses[jj] + uses[kk] == cap){
//                        System.out.printf("Case #%d: %d %d%n", ii+1, jj+1, kk+1);
                        out.printf("Case #%d: %d %d%n", ii+1, jj+1, kk+1);
                        continue outer;
                    }
                }
            }
        }
        out.close();
    }
}
