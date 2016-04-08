import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Books {
    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("books2.txt"));
        PrintWriter out = new PrintWriter("books2.out");
        int nums = in.nextInt();
        for (int i = 0; i < nums; i++) {
            int num = in.nextInt();
            int first = getFirst(num);
            int biggest = getMax(num, first);
            int smallest = getMin(num, first);
//            System.out.printf("\n%d %d\n", smallest, biggest);
            char[] toProcess = (""+num).toCharArray();
            System.out.printf("Case #%d: ", i + 1);
            out.printf("Case #%d: ", i + 1);
            if(smallest == 1 << 25) {
                System.out.print(num + " ");
                out.print(num + " ");
            }
            else {
                for (int j = toProcess.length - 1; j >= 0; j--) {
                    if (toProcess[j] - '0' == smallest) {
                        char tmp = toProcess[0];
                        toProcess[0] = toProcess[j];
                        toProcess[j] = tmp;
                    }
                }
                System.out.print(toProcess);
                System.out.print(" ");
                out.print(toProcess);
                out.print(" ");
            }
            toProcess = (""+num).toCharArray();
            for (int j = 0; j < toProcess.length; j++) {
                if(toProcess[j] - '0' == biggest) {
                    char tmp = toProcess[0];
                    toProcess[0] = toProcess[j];
                    toProcess[j] = tmp;
                }
            }

            System.out.print(toProcess);
            System.out.println();
            out.print(toProcess);
            out.println();
        }
        out.close();
    }

    public static int getFirst(int num) {
        if(num < 10)
            return num;
        return getFirst(num / 10);
    }

    public static int getMax(int num, int start) {
        if(num == 0)
            return -1;
        if(num % 10 > start) {
            if(num % 10 == 0)
                return getMin(num / 10, start);
            return Math.max(num % 10, getMax(num/10, start));
        }
        return getMax(num/10, start);
    }

    public static int getMin(int num, int start) {
        if(num == 0)
            return 1 << 25;
        if(num % 10 < start) {
            if(num % 10 == 0)
                return getMin(num / 10, start);
            return Math.min(num % 10, getMin(num/10, start));
        }
        return getMin(num / 10, start);
    }
}
