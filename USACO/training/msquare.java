import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: msquare
*/
public class msquare {
    static BufferedReader in;
    static PrintWriter out;

    static ArrayDeque<Matrix> frontier = new ArrayDeque<Matrix>();
    static Set<Matrix> visited = new HashSet<Matrix>();

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("msquare.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
        long start = System.nanoTime();
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        String nums = "12345678";
        String read = in.readLine().replace(" ", "");
        Matrix target = new Matrix();
        target.out = read;
        Matrix mat = new Matrix();
        mat.out = nums;
//        System.out.println(target);


        frontier.add(mat);

        while(!frontier.isEmpty()){
            Matrix next = frontier.remove();
//            System.out.println(getPath(next));
//            System.out.println(next.out);
//            System.out.println(next);
//            System.out.println();
            if(next.out.equals(target.out)){
                Matrix tmp = next;
                StringBuilder path = getPath(tmp);
                System.out.println(path.length());
                System.out.println(path);
                out.println(path.length());
                out.println(path);
                break;
            }
            if(visited.add(next)){
//                System.out.println("HI");
                add(a(next.out), "A", next);
                add(b(next.out), "B", next);
                add(c(next.out), "C", next);
            }
        }

        System.out.println((System.nanoTime()-start)/1e9);
        out.close();
        System.exit(0);
    }
    private static StringBuilder getPath(Matrix m){
        Matrix tmp = m;
        StringBuilder b = new StringBuilder();
        while(tmp!=null){
            if(tmp.action != null)
                b = b.append(tmp.action);
            tmp = tmp.parent;
        }
        b = b.reverse();
        return b;
    }

    private static void add(String str, String action, Matrix code){
        Matrix next = new Matrix();
        next.parent = code;
        next.out = str;
        next.action = action;
        frontier.add(next);

    }

    private static String a(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    private static String b(String s) {
        char[] c = s.toCharArray();
        return "" + c[3] + c[0] + c[1] + c[2] + c[5] + c[6] + c[7] + c[4];
    }

    private static String c(String s) {
        char[] c = s.toCharArray();
        return "" + c[0] + c[6] + c[1] + c[3] + c[4] + c[2] + c[5] + c[7];
    }

    static class Matrix{
        String out;
        Matrix parent;
        String action;
        // 0 no more than 2
        // 1 no more than 4
        // 2 no mroe than 4
        public Matrix(){
            out = "";
        }
        public boolean equals(Object other){
            return hashCode() == other.hashCode();
        }
        public int hashCode(){
            return out.hashCode();
        }

        public String toString(){
            return out;
//            return Arrays.toString(mat[0])+"\n"+Arrays.toString(mat[1]);
        }
    }
}
