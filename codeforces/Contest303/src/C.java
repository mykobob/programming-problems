import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class C {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] tmp = in.readLine().split(" ");
            trees.add(new Tree(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
        }

        int[] leftRange = new int[N];
        int[] rightRange = new int[N];

        for (int i = 0; i < N; i++) {
            Tree outer = trees.get(i);
            for (int j = i+1; j < N; j++) {
                Tree inner = trees.get(j);
//                System.out.println(outer + " " + inner);
                if(outer.inRange(inner)) {
                    leftRange[j]++;
                    rightRange[i]++;
                } else {
                    break;
                }
                System.out.println();
            }
        }
//        System.out.println("Arrays.toString(rightRange) = " + Arrays.toString(rightRange));
//        System.out.println("Arrays.toString(leftRange) = " + Arrays.toString(leftRange));

        int count = 0;
        for (int i = 0; i < N; i++) {
            if(leftRange[i] == 0) {
                count++;
            } else if(rightRange[i] == 0) {
                count++;
            }
        }

    }

    static class Tree  {
        long pos;
        long height;
        List<Tree> inRange;
        public Tree(int p, int h) {
            pos = p;
            height = h;
            inRange = new ArrayList<>();
        }

        public boolean inRange(Tree other) {
            if(pos > other.pos) {
                return pos - height <= other.pos;
            }
            if(pos < other.pos) {
                return pos + height >= other.pos;
            }
            return false;
        }

        public String toString() {
            return  String.format("[%d, %d]: %d", pos - height, pos + height, pos);
        }
    }
}
