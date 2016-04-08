import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Meetings {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Meetings"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            int N = i(data.nextToken()), M = i(data.nextToken());
            int[] ppl = new int[N];
            int[] capacity = new int[M];
            StringTokenizer pplToken = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                ppl[j] = i(pplToken.nextToken());
            }
            StringTokenizer capacityToken = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                capacity[j] = i(capacityToken.nextToken());
            }
            Arrays.sort(ppl);
            Arrays.sort(capacity);
            int pplIdx = N - 1, capacityIdx = M - 1;
            int meetings = 0;
            while(pplIdx != -1 && capacityIdx != -1) {
//                System.out.println(pplIdx + " " + capacityIdx);
                if (ppl[pplIdx] <= capacity[capacityIdx]) {
                    meetings++;
                    pplIdx--;
                    capacityIdx--;
                } else {
                    pplIdx--;
                }
            }
            System.out.println(meetings);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
