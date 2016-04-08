import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: odometer
*/
public class odometer {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("odometer.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        long start = Long.parseLong(st.nextToken());
        long end = Long.parseLong(st.nextToken());
//        System.out.println(isInteresting(97791));
//        naive solution
//        long count = 0;
//        for(long ii = start;ii<=end;ii++)
//            if(isInteresting(ii))
//                count++;
//        System.out.println(count);
        /*
            better solution?
         */
        long shortcutCount = 0;
        long actualCount = 0;
        outer:
        for (long ii = start; ii <= end; ii++) {
            int length = length(ii);
            if (!isInteresting(ii))
                continue outer;
            for (int jj = length / 2; jj > 0; jj--) {
                long checkNum = ii / (long) (Math.pow(10, jj));
                int[] numCount = countNums(checkNum);
                for (int kk = 0; kk < numCount.length; kk++) {
                    if (numCount[kk] * 2 >= length) {
                        shortcutCount += Math.pow(10, jj);
                        checkNum++;
                        checkNum *= Math.pow(10, jj);
                        ii = checkNum;
                        continue outer;
                    }
                }
            }
            if(isInteresting(ii)) {
//                System.out.println(ii);
                actualCount++;
            }
        }
//        System.out.println(actualCount);
//        System.out.println(shortcutCount);
        int length = length(start);
        for(int jj = length/2;jj>0;jj--){
            if(isInteresting(start/(long)(Math.pow(10, jj)))){
//                long begin = (start/(long)(Math.pow(10, jj)));
                long ending = (start/(long)(Math.pow(10, jj))*(long)(Math.pow(10, jj)));
//                System.out.println(start + " " + ending);
                for(long ii = start-1;ii>=ending;ii--)
                    if(isInteresting(ii))
                        shortcutCount--;
                break;
            }

        }
//        System.out.println(shortcutCount + actualCount);
        out.println(shortcutCount + actualCount);
        out.close();
        System.exit(0);
    }

    static boolean isInteresting(long l) {
        int[] num = countNums(l);
        int count = length(l);
//        System.out.println(count);
        for (int ii : num) {
            if (ii * 2 >= count)
                return true;
        }
        return false;
    }

    static int length(long l) {
        return (int) (Math.log10(l)) + 1;
    }

    static int[] countNums(long l) {
        int[] num = new int[10];
        while (l > 0) {
            num[(int) (l % 10)]++;
            l /= 10;
        }
        return num;
    }

}
