import java.util.Scanner;

public class Solution7A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[][] mat = new char[8][8];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            char[] tmp = in.nextLine().toCharArray();
            mat[i] = tmp;
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j] == 'B') {
                    count++;
                }
            }
        }
        if (count == 64) {
            System.out.println(8);
        } else {
            int ans = 0;
            for (int i = 0; i < 8; i++) {
                if (allB(mat[i])) {
                    ans++;
                }
            }
            for (int i = 0; i < 8; i++) {
                if (allB(mat, i)) {
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }

    public static boolean allB(char[][] mat, int col) {
        for (int i = 0; i < 8; i++) {
            if (mat[i][col] == 'W') {
                return false;
            }
        }
        return true;
    }

    public static boolean allB(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'W') {
                return false;
            }
        }
        return true;
    }
}
