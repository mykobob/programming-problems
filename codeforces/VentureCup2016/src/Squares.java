import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Squares {
    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] data = in.nextLine().split(" ");
            if (data[0].contains("sq")) {
                solve(data);
            }
        }
    }

    private static void solve(String[] str) {
        System.out.println(Arrays.toString(str));
        char[][] mat = new char[5][8];
        for (String s : str) {
            if (s.startsWith("bounds")) {
                System.out.println(s);
                String[] data = s.substring(7, s.length() - 1).split(",");
                int sR = i(data[0]);
                int sC = i(data[1]);
                int eR = i(data[2]);
                int eC = i(data[3]);
                for (int i = sR; i <= eR; i++) {
                    for (int j = sC; j <= eC; j++) {
                        if (mat[i][j] != '\u0000') {
                            System.out.println("BAD " + s);
                        }
                        mat[i][j] = data[4].charAt(0);
                    }
                }
            }
        }
        for (char[] b : mat) {
            for (char b1 : b) {
                System.out.print(b1 == '\u0000' ? '.' : b1);
            }
            System.out.println();
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
