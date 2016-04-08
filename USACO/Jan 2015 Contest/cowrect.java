import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 1/16/15.
 */
public class cowrect {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("cowrect.in"));
        PrintWriter out = new PrintWriter("cowrect.out");
        int numCows = in.nextInt();
        in.nextLine();
        List<Integer> gYs = new ArrayList<>();
        List<Integer> gXs = new ArrayList<>();
        List<Cow> cows = new ArrayList<>();
        for (int i = 0; i < numCows; i++) {
            String[] data = in.nextLine().split(" ");
            Cow cow = new Cow(i(data[0]), i(data[1]), data[2]);
            cows.add(cow);
            if(!cow.hols) {
                gYs.add(cow.y);
                gXs.add(cow.x);
            }
        }
        //Collections.sort(cows);
        Collections.sort(gYs);
        Collections.sort(gXs);

        int maxArea = 1 << 26;
        int maxCows = -1;

        for (int i = 0; i < numCows; i++) {
            Cow orig = cows.get(i);

            int minX = 1001, maxX = -1;
            int minY = 1001, maxY = -1;
            
            List<Cow> curCluster = new ArrayList<>();
            if(!orig.inCluster && orig.hols) {
                curCluster.add(orig);

                minX = Math.min(minX, orig.x);
                maxX = Math.max(maxX, orig.x);

                minY = Math.min(minY, orig.y);
                maxY = Math.max(maxY, orig.y);
                
                orig.inCluster = true;
                outer:
                for (int j = i + 1; j < numCows; j++) {
                    Cow toAdd = cows.get(j);
                    if(!toAdd.inCluster && toAdd.hols) {
                        int lowerX = Math.min(orig.x, toAdd.x);
                        int higherX = Math.max(orig.x, toAdd.x);
                        int lowerY = Math.min(orig.y, toAdd.y);
                        int higherY = Math.max(orig.y, toAdd.y);

                        for (int ii = 0; ii < numCows; ii++) {

                            if(!cows.get(ii).hols) {
                                /*
                                int lowerXIndex = makePos(Collections.binarySearch(gXs, minX));
                                int higherXIndex = makePos(Collections.binarySearch(gXs, maxX));

                                int lowerYIndex = makePos(Collections.binarySearch(gYs, minY));
                                int higherYIndex = makePos(Collections.binarySearch(gYs, higherY));

                                if(higherXIndex == lowerXIndex || higherYIndex == lowerYIndex) { // If there exist no geureys between them
                                    toAdd.inCluster = true;
                                    curCluster.add(toAdd);
                                } */
                                int x = cows.get(ii).x;
                                int y = cows.get(ii).y;
                                if(lowerX <= x && x <= higherX && lowerY <= y && y <= higherY) {
                                    continue outer;
                                }
                            }


                        }
                        for (int k = 0; k < numCows; k++) {
                            if(!cows.get(k).hols) {
                                int x = cows.get(k).x;
                                int y = cows.get(k).y;
                                if(lowerX <= x && x <= higherX && lowerY <= y && y <= higherY) {
                                    continue outer;
                                }
                            }
                        }
                        curCluster.add(toAdd);

                        minX = Math.min(minX, toAdd.x);
                        maxX = Math.max(maxX, toAdd.x);

                        minY = Math.min(minY, toAdd.y);
                        maxY = Math.max(maxY, toAdd.y);

                    }
                }
            }

//            int minX = 1001, maxX = -1;
//            int minY = 1001, maxY = -1;

//            System.out.println(curCluster);

//            for (int j = 0; j < curCluster.size(); j++) {
//                Cow c = curCluster.get(j);
//                minX = Math.min(minX, c.x);
//                maxX = Math.max(maxX, c.x);
//
//                minY = Math.min(minY, c.y);
//                maxY = Math.max(maxY, c.y);
//            }

            if(curCluster.size() > maxCows) {
//                System.out.println("changed");
//                System.out.println(curCluster);
                maxCows = curCluster.size();
                maxArea = (maxY - minY) * (maxX - minX);
            }
            else if(curCluster.size() == maxCows) {
                maxArea = Math.min(maxArea, (maxY - minY) * (maxX - minX));
            }

        }
        System.out.println(maxCows);
        System.out.println(maxArea);
        out.println(maxCows);
        out.println(maxArea);
        out.close();
        System.exit(0);
    }

    public static int makePos(int n) {
        if(n > 0)
            return n;
        return ~n;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }

    static class Cow implements Comparable<Cow> {
        int x, y;
        boolean hols;
        boolean inCluster;
        public Cow(int x, int y, String kind) {
            this.x = x;
            this.y = y;
            hols = kind.equals("H");
            inCluster = false;
        }

        public int compareTo(Cow other) {
            if(x != other.x) {
                return x - other.x;
            }
            return y - other.y;
        }

        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }
}
