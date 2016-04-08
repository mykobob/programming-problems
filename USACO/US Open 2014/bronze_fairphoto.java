import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: bronze_fairphoto
*/
public class bronze_fairphoto {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("fairphoto.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int numCows = Integer.parseInt(in.readLine());
        int[] prefix = new int[numCows+1];
        List<Cow> cows = new ArrayList<Cow>();
        while(numCows-->0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows.add(new Cow(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }
        Collections.sort(cows);
        for(int ii = 1;ii<=cows.size();ii++){
            Cow cur = cows.get(ii-1);
            if(cur.breed == 'G')
                prefix[ii] = prefix[ii-1] + 1;
            else
                prefix[ii] = prefix[ii-1] - 1;
        }
        System.out.println(Arrays.toString(prefix));

        out.close();
        System.exit(0);
    }
    static class Cow implements Comparable<Cow>{
        int pos;
        char breed;
        public Cow(int p, char b){
            pos = p;
            breed = b;
        }

        @Override
        public int compareTo(Cow o) {
            return pos-o.pos;
        }
    }
}
