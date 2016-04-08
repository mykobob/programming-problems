import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AntsSmall {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while(T-->0) {
            int numAnts = in.nextInt();
            List<Ant> ants = new ArrayList<Ant>();
            for (int i = 0; i < numAnts; i++) {
                ants.add(new Ant(in.nextDouble(), in.next()));
            }
            Collections.sort(ants);
            double time = 0;
            for (int i = 0; i < numAnts; i++) {
                Ant a = ants.get(i);
                if(a.right)
                {
                    time = Math.max(time, 100 - a.pos);
                    break;
                }
            }
            for(int i = numAnts - 1; i >= 0; i--) {
                Ant a = ants.get(i);
                if(!a.right) {
                    time = Math.max(time, a.pos);
                    break;
                }
            }
            System.out.printf("%.5f\n", time);
        }
    }

    public static double updateAnts(List<Ant> ants) {
        double smallestDist = Double.POSITIVE_INFINITY;
        int spot = -1;
        for (int i = 0; i < ants.size() - 1; i++) {
            Ant a = ants.get(i);
            Ant b = ants.get(i + 1);
            if(b.pos - a.pos < smallestDist && a.right && !b.right) {
                smallestDist = b.pos - a.pos;
                spot = i;
            }
        }
        smallestDist /= 2;

        for (int i = 0; i < ants.size(); i++) {
            Ant get = ants.get(i);
            if(i == spot) {
                get.pos += smallestDist;
                Ant next = ants.get(i + 1);
                next.pos -= smallestDist;
                get.right = !get.right;
                next.right = !next.right;
                i++;
            } else {
                if(get.right) {
                    get.pos += smallestDist;
                } else {
                    get.pos -= smallestDist;
                }
            }
            if(get.pos >= 100 || get.pos <= 0) {
                ants.remove(i--);
            }
        }
        return smallestDist;

    }

    static class Ant implements Comparable<Ant> {
        double pos;
        boolean right;
        public Ant(double p, String type) {
            pos = p;
            right = type.equals("right");
        }

        public int compareTo(Ant other) {
            return (int)(Math.signum(pos - other.pos));
        }
    }
}
