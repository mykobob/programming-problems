import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: rect1
*/
public class rect1 {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("rect1.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int a = i(st.nextToken()), b = i(st.nextToken()), times = i(st.nextToken());
        List<Rect> all = new ArrayList<Rect>();
        all.add(new Rect(0, 0, a, b, 1));
        for(int ii = 0;ii<times;ii++){
            st = new StringTokenizer(in.readLine());
            int llx = i(st.nextToken()), lly = i(st.nextToken()), urx = i(st.nextToken()), ury = i(st.nextToken()), color = i(st.nextToken());
            Rect cur = new Rect(llx, lly, urx, ury, color);
            List<Rect> tmp = new ArrayList<Rect>();
            tmp.add(cur);
            for(Rect r : all){
                List<Rect> inter = r.intersect(cur);
                tmp.addAll(inter);
            }
            all = tmp;
        }
//        System.out.println(all);
        Map<Integer, Integer> areas = new TreeMap<Integer, Integer>();
        for(Rect r : all){
            if(areas.containsKey(r.color)){
                areas.put(r.color, areas.get(r.color) + (r.area()));
            }
            else
                areas.put(r.color, r.area());
        }
        for(int ii : areas.keySet()){
//            System.out.printf("%d %d%n", ii, areas.get(ii));
            out.printf("%d %d%n", ii, areas.get(ii));
        }

        out.close();
        System.exit(0);
    }
    private static int i(String str){ return Integer.parseInt(str); }

    static class Rect{
        int llx, lly, urx, ury;
        int color;
        public Rect(int x1, int y1, int x2, int y2, int color){
            this.llx = x1;
            this.lly = y1;
            this.urx = x2;
            this.ury = y2;
            this.color = color;
        }
        public List<Rect> intersect(Rect other){
            List<Rect> rects = new ArrayList<Rect>();
            int llx = Math.max(this.llx, other.llx);
            int lly = Math.max(this.lly, other.lly);
            int urx = Math.min(this.urx, other.urx);
            int ury = Math.min(this.ury, other.ury);
            if(llx < urx && lly < ury){
                if(llx > this.llx){
                    rects.add(new Rect(this.llx, this.lly, llx, this.ury, this.color));
                }
                if(lly > this.lly){
                    rects.add(new Rect(llx, this.lly, urx, lly, this.color));
                }
                if(urx < this.urx){
                    rects.add(new Rect(urx, this.lly, this.urx, this.ury, this.color));
                }
                if(ury < this.ury){
                    rects.add(new Rect(llx, ury, urx, this.ury, this.color));
                }
            }
            else
                rects.add(this);
            return rects;
        }
        public boolean isEmpty(){
            return llx-urx == 0 || lly - ury == 0;
        }
        public String toString(){
            return String.format("(%d %d) -> (%d,%d) COLOR: %d", llx, lly, urx, ury, color);
        }

        public int area() {
            return (urx-llx)*(ury-lly);
        }
    }
}
