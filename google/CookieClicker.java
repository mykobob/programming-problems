import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class CookieClicker {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("B-large.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cookieclicker.out")));
        int times = in.nextInt();
        for(int ii = 0;ii<times;ii++) {
            double cost = in.nextDouble();
            double inc = in.nextDouble();
            double target = in.nextDouble();

            double perSec = 2;

            double timeNeeded = 0;
            int count = 0;
            while(true){
                double curTime = target/perSec;
                double ifBuyTime = cost/(perSec) + target/(perSec + inc);
                if(ifBuyTime > curTime){
                    timeNeeded += target/perSec;
                    break;
                }
                else{
                    timeNeeded += cost/perSec;
                    perSec += inc;
                }
//                System.out.println(timeNeeded);
            }
            System.out.printf("Case #%d: %.7f%n", ii+1,  timeNeeded);
            out.printf("Case #%d: %.7f%n", ii+1, timeNeeded);
//            System.out.println();
        }
        out.close();
    }
}
