
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
public class scode {
    public static void main(String...bob) throws Exception{
        for(int ii = 1;ii<=10;ii++){
            new scode().run(ii);
            Scanner in = new Scanner(new File("scode.out"));
            int ans = in.nextInt();
            in = new Scanner(new File("scode_silver/"+ii+".out"));
            int ans2 = in.nextInt();
            System.out.println(ans + " " + ans2);
//            System.out.println("next");
        }
    }
    public void run(int num)throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("scode_silver/"+num+".in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
        String str = in.readLine();
        memo = new HashMap<String, BigInteger>();
        visited = new HashSet<String>();
//        System.out.println(recur(str));
//        System.out.println(memo);
//        out.println(recur(str)%2014);
        BigInteger ans = solve(str);
        ans = ans.add(new BigInteger("2014")).subtract(one).mod(new BigInteger("2014"));
        out.println(ans);
        out.close();
//        System.out.println(memo);
//        System.exit(0);

    }
    static Set<String> visited;
    static Map<String, BigInteger> memo;
    static BigInteger one = new BigInteger("1");
    static BigInteger zero = new BigInteger("0");
    public static BigInteger solve(String str){
        if(memo.containsKey(str))
            return memo.get(str);
        BigInteger count = one;
        int len = str.length();
        for(int ii = 1;ii*2<len;ii++){
//            System.out.printf("%s %d    %s %d%n", left, ansLeft, right, ansRight);
//            count += solve(left)%2014 + solve(right)%2014;
            if(str.substring(0, ii).equals(str.substring(len-ii)))
                count = count.add(solve(str.substring(ii)));

            if(str.substring(0, ii).equals(str.substring(ii, ii+ii)))
                count = count.add(solve(str.substring(ii)));

            if(str.substring(0,ii).equals(str.substring(len-ii)))
                count = count.add(solve(str.substring(0, len-ii)));

            if(str.substring(len-ii*2, len-ii).equals(str.substring(len-ii)))
                count = count.add(solve(str.substring(0, len-ii)));
        }
        memo.put(str, count);
//        System.out.println(str + " " + count);
        return count;

    }
      /*
    public static int recur(String str){
        if(str.length() <= 2) {
            if(!memo.containsKey(str))
                memo.put(str, 0);
            return 0;
        }
        if(memo.containsKey(str))
            return memo.get(str);

        int count = 0;
        for(int length = 1;length<str.length();length++){
            String left = str.substring(0, length);
            String right = str.substring(length);
            String together = left.concat(" ".concat(right));
            if(visited.contains(together))
                continue;
//            System.out.println("original " + str);
//            System.out.println(left + " " + right);
            if(left.length() == right.length() && left.length() == 2)
            {
                count++;
//                System.out.printf("%s and %s with %d%n", left, right, count);
                continue;
            }
//            System.out.println(right.startsWith(left) + " " + left.startsWith(right));
            int ans = 0;
            if(left.length() == 1 || right.length() == 1)
                ans = recur(left) * recur(right) + 1;
            else
                ans = recur(left) + recur(right)+1;
//            int ans = recur(left) + recur(right) + 1;
            if(right.startsWith(left)){
//                count += ans;
                count += ans;
//                System.out.println(1);
            }
            if(right.endsWith(left)){
//                count += ans;
                count += ans;
//                System.out.println(2);
            }

            if(left.startsWith(right)){
//                count += ans;
                count += ans;
//                System.out.println(3);
            }
            if(left.endsWith(right)){
//                count += ans;
                count += ans;
//                System.out.println(4);
            }
//            System.out.printf(&qu
        }
        memo.put(str, count);
//        System.out.println("recur");
        return count;
    }       */
}