/*
ID: christo41
LANG: JAVA
TASK: milk
 */
import java.io.*;
import java.util.*;

class milk {
    public static void main(String[] args) throws Exception {

        Scanner in = new Scanner(new File("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                "milk.out")));

        Map<Integer,Integer> map=new TreeMap<Integer,Integer>();
        int total=in.nextInt();
        int times=in.nextInt();
        for(int i=0;i<times;i++)
            map.put(in.nextInt(),in.nextInt());
        //	System.out.println(map);
        int curcost=0;
        for(int i:map.keySet())
        {


            //		System.out.println(curcost);
            if(total<=0)
                break;
            if(map.get(i)>=total)
            {
                System.out.println("Milk left: "+total+" Cost: "+i+" how much: "+total+" |"+total+"-"+total+"="+(total-total));
                System.out.println("curcost: "+curcost);
                curcost+=i*total;
                break;
            }
            if(map.get(i)<total)
            {
                System.out.println("Milk left: "+total+" Cost: "+i+" how much: "+map.get(i)+" |"+total+"-"+map.get(i)+"="+(total-map.get(i)));
                System.out.println("curcost: "+curcost);
                total-=map.get(i);
                curcost+=map.get(i)*i;

            }
        }
        out.println(curcost);
        out.close();
        System.exit(0);
    }

}
class Milk implements Comparable<Milk>{
    int price, quant;
    public Milk(int price, int quant){
        this.price = price;
        this.quant = quant;
    }
    @Override
    public int compareTo(Milk o) {
        return price - o.price;
    }
}