import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: contact
*/
public class contact {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("contact.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());
        int lower = Integer.parseInt(st.nextToken());
        int higher = Integer.parseInt(st.nextToken());
        int Nhighest = Integer.parseInt(st.nextToken());
        StringBuilder str = new StringBuilder();
        while(in.ready())
            str.append(in.readLine());
//        System.out.println(str);

        Map<String, Integer> count = new HashMap<String, Integer>();

        for (int length = lower; length <= higher; length++) {
            for (int i = 0; i+length <= str.length(); i++) {
                String str2 = str.substring(i, i+length);
                if(count.containsKey(str2))
                    count.put(str2, count.get(str2)+1);
                else
                    count.put(str2, 1);
            }
        }

//        System.out.println(count);
        List<Freq> freqs = new ArrayList<Freq>();
        for(String str2 : count.keySet()){
            Freq next = new Freq(str2, count.get(str2));
            Freq contains = get(freqs, next);
            if(contains != null){
                contains.add(str2);
            }
            else{
                freqs.add(next);
            }
        }
        Collections.sort(freqs, new Comparator<Freq>() {
            @Override
            public int compare(Freq o1, Freq o2) {
                return o2.freq-o1.freq;
            }
        });
//        System.out.println(freqs);
        for(Freq f : freqs){
            Collections.sort(f.strs, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if(o1.length() != o2.length())
                        return o1.length()-o2.length();
                    return Integer.parseInt(o1,2)-Integer.parseInt(o2,2);
                }
            });
        }

        List<String> ans = new ArrayList<String>();
        for(int ii = 0;ii<Math.min(Nhighest, freqs.size());ii++){
            Freq f = freqs.get(ii);
//            System.out.println(f.freq);
//            out.println(f.freq);
            ans.add(String.valueOf(f.freq));
            int count2 = 0;
            StringBuilder outStr = new StringBuilder();
            for(int jj = 0;jj<f.strs.size();jj++)
            {
                String str2 = f.strs.get(jj);
//                System.out.print(str2 + " ");
                outStr.append(str2);
                outStr.append(" ");
                count2++;
                if(count2 % 6==0 && count2 != f.strs.size()) {
//                    System.out.println();
                    ans.add(outStr.toString().trim());
                    outStr = new StringBuilder();
                }
//                System.out.println(count2 + " " + f.strs.size());
//                System.out.println(outStr);
            }
            ans.add(outStr.toString().trim());
        }
        for(String str2 : ans)
        {
//            System.out.println(str2);
            out.println(str2);
        }


        out.close();
        System.exit(0);
    }
    public static Freq get(List<Freq> freqs, Freq a){
        for(Freq f : freqs){
            if(f.equals(a))
                return f;
        }
        return null;
    }
    static class Freq implements Comparable<Freq>{
        List<String> strs;
        String str;
        int freq;
        public Freq(String s, int f){
            strs = new ArrayList<String>();
            strs.add(s);
            str = s;
            freq=f;
        }
        public void add(String str){
            strs.add(str);
        }
        public boolean equals(Object other){
            if(other instanceof Freq)
                return freq == ((Freq) other).freq;
            return false;
        }

        public String toString(){
            return String.format("%s-%d", strs, freq);
        }

        @Override
        public int compareTo(Freq other) {
            return other.freq-freq;
        }
    }
}
