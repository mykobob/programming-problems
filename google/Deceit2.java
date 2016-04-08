import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
ID: michael138
LANG: JAVA
TASK: Template
*/
public class Deceit2 {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("D-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("deceit.out")));
        final double add = .000001;
        int times = in.nextInt();
        for(int ii = 1;ii<=times;ii++) {
            int numMax = in.nextInt();

            List<Double> naomisOrig = new ArrayList<Double>();
            List<Double> kensOrig = new ArrayList<Double>();


            for(int jj = 0;jj<numMax;jj++)
                naomisOrig.add(in.nextDouble());
            for(int jj = 0;jj<numMax;jj++)
                kensOrig.add(in.nextDouble());
            Collections.sort(naomisOrig, Collections.reverseOrder());
            Collections.sort(kensOrig);
            List<Double> naomis = new ArrayList<Double>(naomisOrig);
            List<Double> kens = new ArrayList<Double>(kensOrig);
            int normalPoints = 0;
            for(int jj = 0;jj<numMax;jj++){
                double naomi = naomis.remove(0);
                double ken = kens.get(0);
                if(naomi > ken){
                    kens.remove(kens.size()-1);
                    normalPoints++;
                }
                else{
                    kens.remove(0);
                }
            }

            naomis = new ArrayList<Double>(naomisOrig);
            kens = new ArrayList<Double>(kensOrig);

            Collections.sort(naomis, Collections.reverseOrder());
            Collections.sort(kens);

            int deceitPoints = 0;

//            for (int jj = 0; jj < numMax; jj++) {
//                if(kens.size() == 1){
//                    deceitPoints += kens.get(0) > naomis.get(0)?0:1;
//                }
//                else {
//                    double smallestKen = kens.get(0);
//                    double smallestNaomi = naomis.get(naomis.size()-1);
//                    if(smallestKen > smallestNaomi){
//                        kens.remove(1);
//                        naomis.remove(naomis.size()-1);
//                    }
//                    else{
//                        kens.remove(0);
//                        naomis.remove(naomis.size()-1);
//                        deceitPoints++;
//                    }
//                }
//
//            }
            for (int jj = 0; jj < numMax; jj++) {
                double biggestNaomi = naomis.remove(0);
                double biggestKen = kens.get(kens.size() - 1);
                if(biggestNaomi > biggestKen){
                    deceitPoints++;
                    kens.remove(0);
                }
                else{
                    kens.remove(kens.size()-1);
                }
            }


            System.out.printf("Case #%d: %d %d%n", ii, normalPoints, deceitPoints);
            out.printf("Case #%d: %d %d%n", ii, normalPoints, deceitPoints);
        }
        out.close();
    }
}
