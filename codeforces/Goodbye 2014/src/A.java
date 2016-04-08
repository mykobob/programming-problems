import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class A {
    public static void main(String...bob) throws Exception {
//        BufferedReader in = new BufferedReader(new FileReader("goodbye2014A.in"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String data = in.readLine();
        int n = Integer.parseInt(data.substring(0, data.indexOf(" "))) - 1;
        int t = Integer.parseInt(data.substring(data.indexOf(" ") + 1)) - 1;

        String[] strValues = in.readLine().split(" ");
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
                values[i] = Integer.parseInt(strValues[i]);
        }
        int start = 0;
        while(start < n) {
            if(start > t) {
                System.out.println("NO");
                break;
            }
            if(start == t) {
                System.out.println("YES");
                break;
            }
            start += values[start];
        }
    }
}
