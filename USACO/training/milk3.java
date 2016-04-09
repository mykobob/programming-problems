/*
ID: michael63
LANG: JAVA
TASK: milk3
*/
import java.util.*;
import java.io.*;

public class milk3
{
    public static void main(String...bob) throws Exception
    {
        String fileName = "milk3";
        Scanner in = new Scanner(new File(fileName+".in"));
        int aMax = in.nextInt();
        int bMax = in.nextInt();
        int cMax = in.nextInt();
        recur(0,aMax,0,bMax,cMax,cMax);
        Collections.sort(all);
//        System.out.println(all.toString().substring(1,all.toString().length()-1).replace(",",""));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        out.println(all.toString().substring(1,all.toString().length()-1).replace(",",""));
        out.close();
        System.exit(0);
    }
    static ArrayList<Integer> all = new ArrayList<Integer>();
    static ArrayList<String> history = new ArrayList<String>();
    public static boolean alreadyWentTo(String str)
    {
        for(String str2 : history)
            if(str2.equals(str))
                return true;
        return false;
    }
    public static void recur(int aActual, int aMax, int bActual, int bMax, int cActual, int cMax)
    {
        if(alreadyWentTo(aActual + " " + bActual + " " + cActual))
            return;
//        System.out.println(aActual + " " + bActual + " " + cActual);
        if(aActual > aMax || bActual > bMax || cActual > cMax)
            return;
        if(aActual < 0 || bActual < 0 || cActual < 0)
            return;
        if(aActual == 0)
        {
            if(!all.contains(cActual))
            {
                all.add(cActual);
            }
        }
        history.add(aActual + " " + bActual + " " + cActual);
        recur(aActual + Math.min(cActual, aMax - aActual), aMax, bActual, bMax, cActual - Math.min(cActual, aMax - aActual), cMax);
        recur(aActual - Math.min(aActual,cMax-cActual), aMax, bActual, bMax, cActual + Math.min(aActual,cMax-cActual), cMax);

        recur(aActual, aMax, bActual + Math.min(cActual,bMax-bActual), bMax, cActual - Math.min(cActual,bMax-bActual), cMax);
        recur(aActual, aMax, bActual - Math.min(bActual,cMax-cActual), bMax, cActual + Math.min(bActual,cMax-cActual), cMax);

        recur(aActual + Math.min(bActual,aMax-aActual), aMax, bActual - Math.min(bActual,aMax-aActual), bMax, cActual, cMax);
        recur(aActual - Math.min(aActual,bMax-bActual), aMax, bActual + Math.min(aActual,bMax-bActual), bMax, cActual, cMax);
    }
}