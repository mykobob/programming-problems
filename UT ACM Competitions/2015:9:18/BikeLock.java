import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeLock {

    static long MOD = (long) (1e9 + 7);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        while (T-->0) {
            String str = '0' + in.nextLine() + '9';
            List<Integer> questions = new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '?') {
                    questions.add(i);
                }
            }
            System.out.println(str);
            System.out.println(questions);
            List<Long> multiples = new ArrayList<>();
            for (int i = 0; i < questions.size();i++) {
                int start = questions.get(i);
                int search = i + 1;
                int before = start;
                while(search < questions.size() && questions.get(search) == before + 1) {
                    search++;
                }
                start-=1;
                i = search;
                search = questions.get(Math.min(search, questions.size() - 1)) + 1;
                System.out.println(start + " " + search);
                int startVal = str.charAt(start) - '0';
                int endVal = str.charAt(search) - '0';
                if (startVal < endVal) {
                    System.out.println(startVal + " " + endVal);
                    int range = endVal - startVal + 1;
                    int choose = search - start - 1;
                    System.out.println(range + " " + choose);
                    multiples.add(choose(range, choose));
                }
            }
            System.out.println("multiples " + multiples);
//            System.out.println(ans);
        }
    }



    public static long choose(long n, long k) {
        return fact(n) / fact(k) / fact(n - k);
    }

    public static long fact(long n) {
        if (n > 0) {
            return n * fact(n - 1);
        }
        return 1;
    }
}
