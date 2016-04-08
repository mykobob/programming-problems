import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: crowded
*/
public class crowded {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("crowded/3.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("crowded.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int cows = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        List<Cow> all = new ArrayList<Cow>();
        for(int ii = 0;ii<cows;ii++){
            st = new StringTokenizer(in.readLine());
            all.add(new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(all, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.pos-o2.pos;
            }
        });
        Queue<Cow> inRange = new LinkedList<Cow>();
        PriorityQueue<Cow> heights = new PriorityQueue<Cow>(1, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) { return o2.height-o1.height; }
        });
//        System.out.println(D);
//        System.out.println(all);
        int count = 0;
        int spot = 0;
        for(int ii = 0;ii<all.size();ii++){
            Cow cur = all.get(ii);
            while(spot < all.size() && all.get(spot).pos - cur.pos < D) {
                inRange.add(all.get(spot));
                heights.add(all.get(spot++));
            }
//            System.out.println(cur);
//            System.out.println(inRange);
//            System.out.println(heights);
            while(Math.abs(inRange.peek().pos - cur.pos) >= D)
            {
                Cow remove = inRange.poll();
                heights.remove(remove);
            }
            boolean left = false, right = false;
            for(Cow check : heights){
                if(check.height >= cur.height * 2)
                {
                    if(check.pos > cur.pos)
                        right = true;
                    if(check.pos < cur.pos)
                        left = true;
                }
                else
                    break;
            }
            if(right && left) {
                count++;
//                System.out.println("here");
            }
        }
        System.out.println(count);
        out.println(count);




        out.close();
        System.exit(0);
    }
    static class Cow{
        int pos, height;
        public Cow(int p, int h){
            pos = p;
            height = h;
        }
        public String toString(){
            return String.format("%d: %d", pos, height);
        }
    }
}
