import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: ratios
*/
public class ratios {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("ratios.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
        // Use StringTokenizer
//        StringTokenizer st = new StringTokenizer(in.readLine());
        Ratio target = new Ratio(in.readLine());
        Ratio[] ratios = new Ratio[3];
        ratios[0] = new Ratio(in.readLine());
        ratios[1] = new Ratio(in.readLine());
        ratios[2] = new Ratio(in.readLine());

        Ratio best = new Ratio(10000, 10000, 10000);
        boolean changed = false;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 100; k++) {
                    Ratio newRatio = ratios[0].multiply(i).add(ratios[1].multiply(j)).add(ratios[2].multiply(k));
                    newRatio.addMult(i, j, k);
                    if(newRatio.sum() == 0)
                        continue;
                    if(target.sameRatio(newRatio)){
                        if(newRatio.sum() < best.sum()) {
                            best = newRatio;
                            changed = true;
                        }
                    }
                }
            }
        }
//        System.out.println(best);
//        System.out.println(target);
        if(!changed){
            System.out.println("NONE");
            out.println("NONE");
        }
        else {
            System.out.printf("%d %d %d %d%n", best.xx, best.yy, best.zz, best.divisor(target));
            out.printf("%d %d %d %d%n", best.xx, best.yy, best.zz, best.divisor(target));
        }
        out.close();
        System.exit(0);
    }
    public static int i(String str){return Integer.parseInt(str);}
    static class Ratio{
        int x, y, z;
        int[] arr;
        int xx = 1, yy = 1, zz = 1;
        public Ratio(String str){
            this(i(str.split(" ")[0]), i(str.split(" ")[1]), i(str.split(" ")[2]));
        }
        public Ratio(int xx, int yy, int zz){
            arr = new int[3];
            x = xx;
            arr[0] = x;
            y = yy;
            arr[1] = y;
            z = zz;
            arr[2] = z;
        }
        public int sum(){
            return x + y + z;
        }
        public void addMult(int i, int j, int k){
            xx = i;
            yy = j;
            zz = k;
        }
        public Ratio add(Ratio other){
            return new Ratio(x + other.x, y + other.y, z + other.z);
        }
        public boolean sameRatio(Ratio other){  // other will be bigger
//            if(other.x%x != 0 || other.y%y != 0 || other.z%z != 0)
//                return false;
            int ratio = -1;
            for (int i = 0; i < 3; i++) {
                try{
                    if(other.arr[i]%arr[i] != 0)
                        return false;
                    int rat = other.arr[i]/arr[i];
                    if(ratio == -1)
                        ratio = rat;
                    else
                        if(ratio != rat)
                            return false;
                }catch(Exception e){if(other.arr[i] != 0) return false;}
            }
            return true;
        }
        public Ratio multiply(int scalar){
            return new Ratio(x * scalar, y*scalar, z*scalar);
        }
        public String toString(){
            return String.format("%d %d %d", x, y, z);
        }

        public int divisor(Ratio target) {
            if(x!=0 && target.x!=0)
                return x/target.x;
            if(y!=0 && target.y != 0)
                return y/target.y;
            if(z !=0 && target.z != 0)
                return z/target.z;
            return 0;
        }
    }
}
