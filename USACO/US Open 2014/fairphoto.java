import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: fairphoto
*/
public class fairphoto {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("fairphoto.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numInstrut = Integer.parseInt(st.nextToken());
        List<Cow> all = new ArrayList<Cow>();
        int[] numWhite = new int[numInstrut];
        int[] numSpotted = new int[numInstrut];
        while(numInstrut-->0){
            st = new StringTokenizer(in.readLine());
            int pos = Integer.parseInt(st.nextToken());
            boolean white = st.nextToken().charAt(0) == 'W';
            all.add(new Cow(pos, white));
        }
        Collections.sort(all, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.spot-o2.spot;
            }
        });

        if(all.get(0).white)
            numWhite[0] = 1;
        else
            numSpotted[0] = 1;

        for(int ii = 1;ii<all.size();ii++){
            if(all.get(ii).white)
                numWhite[ii]+=numWhite[ii-1] + 1;
            else
                numSpotted[ii]+=numSpotted[ii-1]+1;
        }
//        System.out.println(Arrays.toString(numWhite));
//        System.out.println(Arrays.toString(numSpotted));
        int bestStart = 0, bestEnd = 0, dist = 0;
        for(int ii = 0;ii<all.size();ii++){
            for(int jj = all.size()-1;jj>=0;jj--){
                if(Math.abs(all.get(jj).spot-all.get(ii).spot) < dist)
                    break;
//                if(jj-ii < dist)
//                    break;
                int whiteDiff = numWhite[jj]-numWhite[ii];
                int spotDiff = numSpotted[jj]-numSpotted[ii];
                int diff = whiteDiff-spotDiff;
                if(diff >= 0)
                {
                    if(diff%2 == 1){
                        bestStart = all.get(ii).spot;
                        bestEnd = all.get(jj).spot;
                        dist = bestEnd-bestStart;
                    }
                }
//                System.out.println("count");
            }
        }
//        System.out.printf("%d - %d %d%n", bestStart, bestEnd, dist);
        int ans = bestEnd-bestStart;
        System.out.println(ans);
        out.println(ans);

        out.close();
        System.exit(0);
    }
    static class Cow{
        int spot;
        boolean white;
        public Cow(int s, boolean w){
            spot = s;
            white = w;
        }
    }
}
