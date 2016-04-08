import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class Numbers {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("C-small-practice.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numbers.out")));

        double constant = (3 + Math.sqrt(5));

        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        Set<Integer> visited = new HashSet<Integer>();
        int exp = 1;
        while(true){
            double nextNum = Math.pow(constant, exp);
            int next = getNum(nextNum);
            if(visited.add(next)){
                arr.add(next);
            }
            else
                break;
            exp++;
        }
//        System.out.println(arr);
        int times = in.nextInt();
        for (int ii = 0; ii < times; ii++) {
            int num = in.nextInt();
            int target = (num)%arr.size();
            String ans = String.valueOf(arr.get(target));
            while(ans.length()<3)
                ans = "0" + ans;
            System.out.println(arr);
            System.out.printf("Case #%d: %s%n", ii+1, ans);
            System.out.println(getNum(Math.pow(constant, num)));
            out.printf("Case #%d: %s%n", ii+1, ans);
        }

        out.close();
    }
    public static int getNum(double d){
        d %= 1000;
        int num = (int)Math.floor(d);
        return num;
    }
}
