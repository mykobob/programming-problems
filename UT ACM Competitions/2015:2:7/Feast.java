import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 2/7/15.
 */
public class Feast {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input (1).txt"));
//        Scanner in = new Scanner(new File("feast_input.txt"));
        PrintWriter out = new PrintWriter("feast.out");
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int numBuckets = in.nextInt();
            int[] needed = {in.nextInt(), in.nextInt(), in.nextInt()};
            List<int[]> data = new ArrayList<int[]>();
            for (int j = 0; j < numBuckets; j++) {
                int[] info = {in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt()};
                data.add(info);
            }

            Queue<int[]> frontier = new LinkedList<int[]>();
            Set<String> seen = new HashSet<String>();
            frontier.add(new int[]{0, 0, 0, 0});
            int best = (1 << 25);
            outer:
            while(!frontier.isEmpty()) {
                int[] next = frontier.poll();
                int j;
                for (j = 0; j < 4; j++) {
                    if(j == 3) {
                        if(next[3] > best) {
                            continue outer;
                        } else {
                            best = next[3];
                            continue outer;
                        }
                    } else {
                        if(next[j] < needed[j]) {
                            break;
                        }
                    }
                }
                if(next[3] > best) {
                    continue;
                }

                String hash = next[0] + " " + next[1] + " " + next[2];
                if(seen.contains(hash)) {
                    continue;
                } else {
                    seen.add(hash);
                }

                for(int[] tmp : data) {
                    int[] toAdd = new int[4];
                    for (int k = 0; k < 4; k++) {
                        toAdd[k] = next[k] + tmp[k];
                    }
                    frontier.add(toAdd);
                }
            }
            System.out.println(best);
            out.println(best);
        }
        out.close();
    }
}
