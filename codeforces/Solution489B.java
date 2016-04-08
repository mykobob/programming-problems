import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution489B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution489B"));
        int boyNum = i(in.readLine());
        int[] boyHeights = new int[boyNum];
        StringTokenizer boys = new StringTokenizer(in.readLine());
        for (int i = 0; i < boyNum; i++) {
            boyHeights[i] = i(boys.nextToken());
        }

        int girlNum = i(in.readLine());
        int[] girlHeights = new int[girlNum];
        StringTokenizer girls = new StringTokenizer(in.readLine());
        for (int i = 0; i < girlNum; i++) {
            girlHeights[i] = i(girls.nextToken());
        }

        Arrays.sort(boyHeights);
        Arrays.sort(girlHeights);
        int i = 0, j = 0;
        int count = 0;
//        System.out.println(i + " " + boyNum + " " + j + " " + girlNum);
        while (i < boyNum && j < girlNum) {
//            System.out.println(boyHeights[i] + " " + girlHeights[j]);
            if (Math.abs(boyHeights[i] - girlHeights[j]) <= 1) {
                count++;
                i++;
                j++;
            } else if (boyHeights[i] < girlHeights[j]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println(count);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
