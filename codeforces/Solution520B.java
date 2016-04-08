import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution520B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] str = in.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        Queue<int[]> frontier = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        frontier.add(new int[]{N, 0});
        while(!frontier.isEmpty()) {
            int[] next = frontier.poll();
            if (next[0] == M) {
                System.out.println(next[1]);
                break;
            }
//            System.out.println(Arrays.toString(next));

            if(visited.add(next[0])) {
                if (next[0] >= 0 && next[0] <= 10000 * 2) {
                    frontier.add(new int[]{next[0] * 2, next[1] + 1});
                    frontier.add(new int[]{next[0] - 1, next[1] + 1});
                }
            }
        }

    }
}
