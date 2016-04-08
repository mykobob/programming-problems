import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: BinaryTree
*/
public class BinaryTree {
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("binarytree.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("BinaryTree.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int nodes = in.nextInt();
            List<Integer>[] arr = new ArrayList[nodes];
            for (int j = 0; j < nodes; j++) {
                arr[j] = new ArrayList<Integer>();
            }
            for (int j = 0; j < nodes - 1; j++) {
                int src = in.nextInt()-1;
                int dest = in.nextInt()-1;
                arr[src].add(dest);
                arr[dest].add(src);
            }
            solve(arr, 0);
        }

        out.close();
        System.exit(0);
    }
    public static void solve(List<Integer>[] arr, int removed){
        if(full(arr)){
            System.out.println(removed);
        }
        else{
            for (int i = 0; i < arr.length; i++) {
                List<Integer> a = new ArrayList<Integer>();
                for (int j = 0; j < arr[i].size(); j++) {
                    a.add(arr[i].get(j));
                }
                arr[i].clear();
                for (int j = 0; j < a.size(); j++) {
                    if(j == i)
                        continue;
                    arr[j].remove(new Integer(i));
                }
                solve(arr, removed+1);
                arr[i] = a;
                for (int j = 0; j < a.size(); j++) {
                    if(i == j)
                        continue;
                    arr[j].add(i);
                }
            }
        }
    }

    private static boolean full(List<Integer>[] arr) {
        int twoCount = 0;
        int oneCount = 0;
        int threeCount = 0;
        for(List<Integer> ii : arr){
            if(ii.size() > 3)
                return false;
            switch (ii.size()){
                case 1:oneCount++; break;
                case 2:twoCount++; break;
                case 3:threeCount++;
            }
        }
        if(twoCount != 1)
            return false;


        return true;
    }
}
