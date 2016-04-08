import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class T9Spelling {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("A-large-practice.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("T9Spelling.out")));
        int times = in.nextInt();
        for(int ii = 0;ii<times;ii++){
            int size = in.nextInt();
            in.nextLine();
            long[] arr1 = new long[size];
            String[] data = in.nextLine().split(" ");
            for(int jj= 0 ;jj<size;jj++)
                arr1[jj] = Long.parseLong(data[jj]);

            long[] arr2 = new long[size];
            data = in.nextLine().split(" ");
            for(int jj= 0 ;jj<size;jj++)
                arr2[jj] = Long.parseLong(data[jj]);

            Arrays.sort(arr1);
            Arrays.sort(arr2);
//            System.out.printf("%s%n%s%n", Arrays.toString(arr1), Arrays.toString(arr2));
            long total = 0;
            for(int jj = 0;jj<size;jj++){
                total += arr1[jj] * arr2[size-1-jj];
            }
            System.out.printf("Case #%d: %d%n", ii+1, total);
            out.printf("Case #%d: %d%n", ii+1, total);
        }
        out.close();

    }
}
