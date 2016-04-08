import java.util.Scanner;

/**
 * Created by Michael on 4/24/15.
 */
public class Ball {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T -- >0) {
            int numBalls = in.nextInt();
            double[] radii = new double[numBalls];
            for (int i = 0; i < numBalls; i++) {
                radii[i] = in.nextDouble();
            }
            double length = 0;
            for (int i = 1; i < numBalls - 1; i++) {
                double a = radii[i - 1];
                double b = radii[i];
                double c = radii[i + 1];
                double dist = getDist(a, b);
                if(fits(a, b, c)) {
                    length += a + c;
                    i++;
                } else {
                    length += dist;
                }
            }
        }
    }

    public static double getDist(double a, double b) {
        double hypot = a + b;
        double leg = Math.max(a, b);
        return hypot * hypot - leg * leg;
    }

    public static boolean fits(double a, double b, double c) {
        // if a + c is hypot
        // a + b and b + c are legs
        // (a + b) ^ 2 + (b + c) ^ 2 <= (a + c) ^ 2
        double hypot = a + c;
        double legA = a + b, legB = b + c;
        return legA * legA + legB * legB <= hypot * hypot;
    }
}
