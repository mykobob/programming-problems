import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution407B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution407B"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int numRooms = i(testCases.nextToken());
        int[] rooms = new int[numRooms];
        boolean[] odd = new boolean[numRooms];
        StringTokenizer roomNums = new StringTokenizer(in.readLine());
        for (int i = 0; i < numRooms; i++) {
            rooms[i] = i(roomNums.nextToken()) - 1;
        }
//        System.out.println(Arrays.toString(rooms));
        BigInteger mod = new BigInteger(""+((long) (1e9 + 7)));
        BigInteger[] dp = new BigInteger[numRooms + 1];
        for (int i = 0; i < numRooms; i++) {
            dp[i] = BigInteger.ZERO;
        }
        for (int i = 0; i < numRooms; i++) {
            dp[i + 1] = (dp[i].multiply(BigInteger.ONE.add(BigInteger.ONE)).add(BigInteger.ONE.add(BigInteger.ONE)).subtract(dp[rooms[i]])).mod(mod);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[numRooms]);
//        solve(rooms, 0, odd);
//        System.out.println(num);
    }

    public static void print (int[][] mat) {
        System.out.println("even odd");
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + "    ");
            }
            System.out.println();
        }
    }

    static int num = 0;
    static Map<String, Integer> memo = new HashMap<>();
    public static void solve(int[] rooms, int index, boolean[] odd) {
        if (index == rooms.length) {
            return;
        }
        String key = index + " " + odd[index];
//        System.out.println(key);
        if (memo.containsKey(key)) {
            num += memo.get(key);
            return;
        } else {
//        System.out.println(index + " " + odd[index] + " " + num);
            odd[index] = !odd[index];
            if (odd[index]) {
                num++;
                solve(rooms, rooms[index], odd);
            } else {
                num++;
                solve(rooms, index + 1, odd);
            }
        }
        memo.put(key, num);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
