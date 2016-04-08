import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 1/23/15.
 */
public class Hack {
    public static void main(String... bob) throws Exception {
//        Scanner in = new Scanner(new File("hack.txt"));
        Scanner in = new Scanner(new File("input2.txt"));
        PrintWriter out = new PrintWriter("hack.out");
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int size = in.nextInt();
            int numKeys = in.nextInt();
            int key = in.nextInt(2);
            int[] locks = new int[numKeys];
            for (int j = 0; j < numKeys; j++) {
                locks[j] = in.nextInt(2);
//                System.out.println(Integer.toBinaryString(locks[j]));
            }
            Queue<Integer> frontier = new LinkedList<Integer>();
            Set<Integer> visited = new HashSet<Integer>();
            frontier.add(key);
            boolean done = false;
            while(!frontier.isEmpty()) {
                int next = frontier.poll();
                if(visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                if(next == 0) {
//                    System.out.println(next[1]);
                    System.out.println("YES");
                    out.println("YES");
                    done = true;
                    break;
                }
                for (int j = 0; j < numKeys; j++) {
                    int tmp = next ^ locks[j];
                    frontier.add(tmp);
                }
            }
            if(!done) {
                System.out.println("NO");
                out.println("NO");
            }
        }
        out.close();
        System.exit(0);
    }



}
