import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class reverseWords {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("B-large-practice.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reverseWords.out")));
        int cases = in.nextInt();
        in.nextLine();
        for(int ii = 1;ii<=cases;ii++){
            String[] arr = in.nextLine().split(" ");
            System.out.printf("Case #%d: ", ii);
            out.printf("Case #%d: ", ii);
            for(int jj = arr.length-1;jj>=0;jj--)
            {
                System.out.printf("%s ", arr[jj]);
                out.printf("%s ", arr[jj]);
            }
            System.out.println();
            out.println();
        }
        out.close();

    }
}
