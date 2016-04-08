import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 3/5/15.
 */
public class Cartoons {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("cartoons.out");
        int T = in.nextInt();
        while(T -- >0) {
            memo = new HashMap<String, Integer>();
            int numCartoons = in.nextInt(), numChannels = in.nextInt(), numFav = in.nextInt(), K = in.nextInt();
            in.nextLine();
            String[] fav = in.nextLine().split(" ");
            String[][] canChoose = new String[numChannels][numCartoons];
            for (int i = 0; i < numChannels; i++) {
                String[] data = in.nextLine().split(" ");
                for (int j = 0; j < numCartoons; j++) {
                    canChoose[i][j] = data[j];
                }
            }

//            for (int i = 0; i < numChannels; i++) {
//                for (int j = 0; j < numCartoons; j++) {
//                    System.out.println(canChoose[i][j]);
//                }
//            }

            int ans =solve(canChoose, fav, 0, 0, 0, K);
            System.out.println(ans);
            out.println(ans);
//            System.out.println();
        }
        out.close();
    }

    static Map<String, Integer> memo;

    public static int solve(String[][] canChoose, String[] fav, int row, int col, int switches, int K) {
        if(switches > K) {
            return 0;
        }

        String key = row + " " + col + " " + switches;
        if(memo.containsKey(key)) {
            return memo.get(key);
        }
        if(col >= canChoose[0].length) {
            return 0;
        }

        List<Integer> possibleChan = new ArrayList<Integer>();
        for (int i = 0; i < canChoose.length; i++) {
            for (int j = 0; j < fav.length; j++) {
                String favStr = fav[j];
                if(canChoose[i][col].equals(favStr)) {
                    possibleChan.add(i);
                }
            }
        }
//        System.out.println(possibleChan);
        if(possibleChan.size() == 0) {
            return solve(canChoose, fav, row, col + 1, switches, K);
        }
        int best = -1;
        for (int i = 0; i < possibleChan.size(); i++) {
            int tmp = possibleChan.get(i);
            int ans = 1 + solve(canChoose, fav, tmp, col + 1, switches + (tmp == row ? 0 : 1), K);
            if(ans > best) {
                best = ans;
            }
        }
        memo.put(key, best);
        return best;
    }
}