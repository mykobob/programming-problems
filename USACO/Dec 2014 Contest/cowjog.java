import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;

public class cowjog {

    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("cowjog.in"));
        int numCows = in.nextInt();
        BigInteger time = in.nextBigInteger();
        in.nextLine();
        Cow[] cows = new Cow[numCows];
        BigInteger[] endSpots = new BigInteger[numCows];
        for (int i = 0; i < numCows; i++) {
            String[] str = in.nextLine().split(" ");
            cows[i] = new Cow(new BigInteger(str[0]), new BigInteger(str[1]));
        }
        for (int i = 0; i < numCows; i++) {
            endSpots[i] = cows[i].pos.add(cows[i].speed.multiply(time));
        }
        BigInteger minDist = endSpots[numCows - 1];
        int groups = 1;
        for (int i = numCows - 1; i >= 0; i--) {
            if(endSpots[i].compareTo(minDist) < 0) {
                groups++;
                minDist = endSpots[i];
            }
        }
        System.out.println(groups);
        PrintWriter out = new PrintWriter(new File("cowjog.out"));
        out.println(groups);
        out.close();
        System.exit(0);
    }
    static class Cow {
        BigInteger pos;
        BigInteger speed;
        public Cow(BigInteger p, BigInteger s) {
            pos = p;
            speed = s;
        }
    }
}
