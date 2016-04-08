import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution300B {

    static List<Integer> team = new ArrayList<>();

    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution300B"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken()), M = i(testCases.nextToken());
        int[][] mat = new int[N + 1][N + 1];
        boolean[] teamed = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int a = i(data.nextToken()), b = i(data.nextToken());
            mat[a][b] = mat[b][a] = 1;
        }
        Map<Integer, Integer> teamSizes = new HashMap<>();
        teamSizes.put(1, 0);
        teamSizes.put(2, 0);
        teamSizes.put(3, 0);
        for (int i = 1; i <= N; i++) {
            if (!teamed[i]) {
                team = new ArrayList<>();
                team.add(i);
                teamed[i] = true;
                dfs(i, teamed, mat);
                if (team.size() > 3) {
                    System.out.println(-1);
                    return;
                }
                Integer val = teamSizes.get(team.size()) == null ? 0 : teamSizes.get(team.size());
                teamSizes.put(team.size(), val + 1);
                teams.add(team);
            }
        }
        if (teamSizes.get(2) != null && teamSizes.get(1) != null && (teamSizes.get(2) > teamSizes.get(1) || (teamSizes.get(2) - teamSizes.get(1)) % 3 != 0)) {
            System.out.println(-1);
        } else {
            Set<Integer> used = new HashSet<>();
            for (int i = 0; i < teams.size(); i++) {
                List<Integer> tmp = teams.get(i);
                if (!used.contains(i)) {
                    if (tmp.size() == 3) {
                        System.out.println(tmp.toString().replaceAll("\\p{Punct}", ""));
                        used.add(i);
                    } else {
                        for (int j = i; j < teams.size(); j++) {
                            if (teams.get(j).size() != 3) {
                                if (!used.contains(j) && teams.get(j).size() != teams.get(i).size()) {
                                    System.out.println(teams.get(i).toString().replaceAll("\\p{Punct}", "") + " " + teams.get(j).toString().replaceAll("\\p{Punct}", ""));
                                    used.add(j);
                                    used.add(i);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            List<Integer> rest = new ArrayList<>();
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).size() == 1 && !used.contains(i)) {
                    rest.add(teams.get(i).get(0));
                }
            }
            for (int i = 0; i < rest.size(); i+=3) {
                System.out.println(rest.get(i) + " " + rest.get(i + 1) + " " + rest.get(i + 2));
            }
        }
    }

    static List<List<Integer>> teams = new ArrayList<>();
    public static void dfs(int person, boolean[] teamed, int[][] mat) {
        for (int i = 1; i < mat.length; i++) {
            if (mat[person][i] == 1 && !teamed[i]) {
                teamed[i] = true;
                team.add(i);
                dfs(i, teamed, mat);
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
