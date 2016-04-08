import java.io.File;
import java.util.*;

/**
 * Created by Michael on 4/4/15.
 */
public class PalPath {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("palpath.in"));
        int size = in.nextInt();
        in.nextLine();
        char[][] mat = new char[size][];

        Map<Integer, List<Character>> map = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String tmp = in.nextLine();
            mat[i] = tmp.toCharArray();
        }

        for (int i = 0; i < size; i++) {
            int r = i, c = 0;
            List<Character> arr = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                arr.add(mat[r][c]);
                r--;
                c++;
            }
            map.put(i, arr);
        }

        for(int i : map.keySet()) {
            List<Character> tmp = map.get(i);
            for (int j = 0; j < tmp.size(); j++) {
                if(tmp.size()%2 == 0) {
                    String key =    i + " " + tmp.get(j) + " " + (j < tmp.size()/2 ? 0 : 1);
                    if(count.containsKey(key)) {
                        count.put(key, count.get(key) + 1);
                    } else {
                        count.put(key, 1);
                    }
                } else {
                    if(j == tmp.size()/2) {
                        String key =    i + " " + tmp.get(j) + " " + 1;
                        if(count.containsKey(key)) {
                            count.put(key, count.get(key) + 1);
                        } else {
                            count.put(key, 1);
                        }
                        key =    i + " " + tmp.get(j) + " " + 0;
                        if(count.containsKey(key)) {
                            count.put(key, count.get(key) + 1);
                        } else {
                            count.put(key, 1);
                        }
                    } else {
                        String key =    i + " " + tmp.get(j) + " " + (j < tmp.size() / 2 ? 0 : 1);
                        if(count.containsKey(key)) {
                            count.put(key, count.get(key) + 1);
                        } else {
                            count.put(key, 1);
                        }
                    }
                }
            }
        }

        System.out.println(map);
        System.out.println(count);

        int[][][] dp = new int[size][size][size * 2];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                dp[i][j][1] = 1;
                dp[i][j][0] = 1;
            }
        }
        // dp[i][j][k] = number of palindromes of length k that end on i, j
        // dp[i][j][k] = dp[i-1][j][k-1] + dp[i][j-1][k-1] if

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 2; k <= size * 2 - 1; k+= 2) {
                    int level = i + j >= size ? (i - size + 1) + (j - size + 1) : i + j;

                        int goUp;
                        if(i == 0) {
                            goUp = 0;
                        } else {
                            goUp = dp[i - 1][j][k - 2];
                            String key = level + " " + mat[i][j] + " " + 1;
                            if (count.containsKey(key)) {
                                int theCount = count.get(key);
                                goUp += theCount;
                            } else {
                                goUp = 0;
                            }
                        }

                        int goLeft;
                        if(j == 0) {
                            goLeft = 0;
                        } else {
                            goLeft = dp[i][j - 1][k - 2];
                            String key = level + " " + mat[i][j] + " " + 0;
                            if (count.containsKey(key)) {
                                int thCount = count.get(key);
                                goLeft += thCount;
                            } else {
                                goLeft = 0;
                            }
                        }

                        dp[i][j][k] = goUp + goLeft;


                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(Arrays.toString(dp[i][j]) + " ");
            }
            System.out.println();
        }

        System.out.println(dp[size - 1][size - 1][size * 2 - 1]);
    }
}
