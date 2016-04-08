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
public class Deceit {

    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("deceit.in"));
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

            System.out.println("namoi " + naomis);
            System.out.println("ken   " + kens);

            int normalPoints = 0;
            for(int jj = 0;jj<numMax;jj++){
                double naomi = naomis.remove(0);
                double ken = kens.get(kens.size() - 1);
//                System.out.println("ken = " + ken + " namoi = " + naomi);
                if(naomi > ken){
                    kens.remove(0);
                    normalPoints++;
                }
                else{
                    kens.remove(ken);
                }
            }

            naomis = new ArrayList<Double>(naomisOrig);
            kens = new ArrayList<Double>(kensOrig);

            Collections.sort(naomis, Collections.reverseOrder());
            Collections.sort(kens);

            int deceitPoints = 0;

            for (int jj = 0; jj < numMax; jj++) {
                if(kens.size() == 1){
                    deceitPoints += kens.get(0) > naomis.get(0)?0:1;
                }
                else {
                    for (int kk = 0; kk < naomis.size(); kk++) {
                        List<Double> greater = new ArrayList<Double>();
                        for (int mm = 0; mm < kens.size(); mm++) {
                            if(kens.get(mm) > naomis.get(kk)){
                                greater.add(kens.get(mm));
                            }
                        }
                    }
                }

            }


            System.out.printf("Case #%d: %d %d%n", ii, deceitPoints, normalPoints);
            out.printf("Case #%d: %d %d%n", ii, deceitPoints, normalPoints);
        }
        out.close();
    }
}
