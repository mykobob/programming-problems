import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

class scode2 {
    public static void main(String...bob) throws Exception{
        for(int ii = 1;ii<=10;ii++){
            main(ii);
            Scanner in = new Scanner(new File("scode.out"));
            int ans =in.nextInt();
            in = new Scanner(new File("scode_silver/"+ii+".out"));
            int ans2 = in.nextInt();
            System.out.println(ans + " " + ans2);
        }
    }
    public static void main(int num) throws Exception {

        long start=System.nanoTime();
        Scanner in = new Scanner(new File("scode_silver/"+num+".in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));

        String orig=in.nextLine();
//        if(orig.length()%2==0){
//            out.println(0);
////            System.out.println("hi");
//        }
//        else
//        {
            int x=solve(orig.substring(0,orig.length()/2),orig.substring(orig.length()/2))+solve(orig.substring(orig.length()/2+1),orig.substring(0,orig.length()/2+1));
            out.println(x);
//        }

//        System.out.println((System.nanoTime()-start)/10e9);
        out.close();
//        System.exit(0);
    }
    public static int solve(String x,String y) //x is shorter than y
    {
        int count=isValid(x, y);
        if(count==0)
            return 0;
        return count+solve(y.substring(0,y.length()/2),y.substring(y.length()/2))+solve(y.substring(y.length()/2+1),y.substring(0,y.length()/2+1));
    }
    public static int isValid(String x,String y)
    {
        int count=0;
        if(y.substring(1).equals(x))
            count++;
        if(y.substring(0, y.length()-1).equals(x))
            count++;
        return count;
    }
}