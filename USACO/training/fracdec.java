import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
ID: michael138
LANG: JAVA
TASK: fracdec
*/
public class fracdec {
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String... bob) throws Exception {
        long start = System.nanoTime();
        in = new BufferedReader(new FileReader("fracdec.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        String[] data = in.readLine().split(" ");
        int top = Integer.parseInt(data[0]);
        int bottom = Integer.parseInt(data[1]);
        int origTop = top, origBottom = bottom;
//        System.out.print(top / bottom + ".");
        out.print(top/bottom+".");

        if(top >= bottom)
            top -= (top/bottom) * bottom;
        top*=10;
//        System.out.println(top + " " + bottom);
        List<Integer> remainders = new ArrayList<Integer>();
        Set<Integer> remainCheck = new HashSet<Integer>();
//        remainders.add(origTop%origBottom);
        remainders.add(top/10);
        remainCheck.add(top/10);
        List<Integer> answers = new ArrayList<Integer>();
        StringBuilder ans = new StringBuilder();
        int repeatStart = 0;
        boolean canDivide = false;
        long start2 = System.nanoTime();
//        System.out.println((start2-start)/1e9);
        outer:
        for(int ii = 0;ii<bottom;ii++){
            int remainder = top%bottom;
//            System.out.println("remainder " + remainder);
            ans.append(top / bottom);
            answers.add(top / bottom);
            if(remainder == 0){
                remainders.add(remainder);
                remainCheck.add(remainder);
                canDivide = true;
//                System.out.println("terminates ");
                break;
            }
            if(!remainCheck.add(remainder)){
//                System.out.println("hi");
                for(int jj = 0;jj<remainders.size();jj++){
                    if(remainders.get(jj) == remainder){
    //                    ans = ans.substring(0, ans.length()-1);
    //                    answers.remove(answers.size()-1);
    //                    System.out.println("repeated");
                        repeatStart = jj;
                        break outer;
                    }
                }
            }
            else{
                top = remainder * 10;
                remainders.add(remainder);
            }
        }
//        System.out.println("finished?");
//        System.out.println(repeatStart);
//        System.out.println("ACTUAL ALGORITHM TAKES " + (System.nanoTime()-start2)/1e9);
        if(canDivide){
//            System.out.println(answers);
//            System.out.println(ans);

                out.print(ans);
//            System.out.println();
            out.println();
        }
        else{
//            ans = ans.substring(0, ans.length()-1);
//            System.out.println(ans);
//            if(repeatStart != 0)
//                repeatStart--;
//            System.out.println(ans);
//            System.out.println(answers);
            if(repeatStart == 0){
//                System.out.println("hi2");
//                long start = System.nanoTime();
//                start = System.nanoTime();
                out.print("(");
                if(ans.length() > 76){
//                    System.out.println("hi");
//                    boolean first = true;
                    for(int jj = 0;jj<ans.length();jj++){
//                        if(first && jj%76 == 72){
//                            out.println();
////                            System.out.println("hi");
//                            first = false;
//                        }
//                        else if(!first && jj%76 == 0 && jj > 76)
//                            out.println();
//                        out.print(ans.charAt(jj));
//                        System.out.println(origTop + " "+  origBottom + " " + Math.log(origTop/origBottom));
                        if(jj%76 == (76-(origTop > origBottom? (int)Math.log(origTop/origBottom)+2:3)))
                            out.println();
                        out.print(ans.charAt(jj));

                    }
                }
                else
                    out.print(ans);
                out.print(")");
                out.println();
//                System.out.println((System.nanoTime()-start)/1e9);
//                System.out.println((System.nanoTime()-start)/1e9);
//                System.out.println("(".concat(ans).concat(")"));
            }
            else{
//                System.out.println(ans.substring(0, repeatStart) + "(" + ans.substring(repeatStart)+")");
//                System.out.println("hi");
//                out.println(ans.substring(0, repeatStart).concat("(").concat(ans.substring(repeatStart)).concat(")"));

                for(int jj = 0;jj<repeatStart;jj++)
                    out.print(ans.charAt(jj));
                out.print("(");
                for(int jj = repeatStart;jj<ans.length();jj++){
//                        if(first && jj%76 == 72){
//                            out.println();
////                            System.out.println("hi");
//                            first = false;
//                        }
//                        else if(!first && jj%76 == 0 && jj > 76)
//                            out.println();
//                        out.print(ans.charAt(jj));
//                        System.out.println(origTop + " "+  origBottom + " " + Math.log(origTop/origBottom));
                    if(jj%76 == (76-(origTop > origBottom? (int)Math.log(origTop/origBottom)+2:repeatStart+2)))
                        out.println();
                    out.print(ans.charAt(jj));

                }
                out.print(")");
                out.println();
            }
//            System.out.println(repeatedStr);
//            out.println(repeatedStr);
        }
//        System.out.println(ans);



//        out.println((System.nanoTime()-start)/1e9);
        out.close();
//        String ans2 = new Scanner(new File("fracdec.out")).nextLine();
//        ans2 = ans2.substring(4, ans2.length()-1);
        /*System.out.println(("186411327289395945808291738820413568299887949475399816644596108790872975\n" +
                "4507487012325557706020169094428033003972700417642864418865233778139961291636\n" +
                "9563002954059284913924824284404604257919934806967505347865946826932871549353\n" +
                "1628807171233574411734745849037384129571152083121116430681470917795660588774\n" +
                "5747173270856677192625038199042477335234796781094020576550881124579810532749\n" +
                "3124172354079657736579403076296220841397575634104105123764897626566160741570\n" +
                "7446266680248548436385861261077722318427218091066517265967199755526128145054\n" +
                "4973006009982683100743608026892125904044005296933890190485891820311704186615\n" +
                "0555159417337272079046551899765712539472343893246409290007130487929102577162\n" +
                "0658042171742894978099215646327798716512172761536110828155240908627890394214\n" +
                "1183660996231027808902923500050932056636446979729041458694102067841499439747\n" +
                "3769990832229805439543648772537435061627788530100845472140165019863502088214\n" +
                "3220943261688906998064581847815014770296424569624121422023021289599674034837\n" +
                "5267393297341346643577467658144035856167872058673729245186920647855760415605\n" +
                "5821534073545889783029438728735866354283385963125190995212386676173983905470\n" +
                "1028827544056228990526637465620861770398288682897015381481104206987878170520\n" +
                "5256188244881328308037078537231333401242742181929306305388611592136090455332\n" +
                "5863298359987776306407252724865030049913415503718040134460629520220026484669\n" +
                "4509524294591015585209330752775797086686360395232759498828562697361719466232\n" +
                "0464500356524396455128858103290210858714474890496078231638993582560863807680\n" +
                "5541407762045431394519710705918304981155139044514617500254660283182234898645\n" +
                "2072934705103392074971987368849954161149027197718243862687175308138942650504\n" +
                "2273607008250993175104410716104716308444534990322909239075073851482122848120\n" +
                "6071101151064479983701741876336966486706733217887338290720179280839360293368\n" +
                "6462259346032392788020780279107670367729448915147193643679331771416929815625\n" +
                "9549760619333808699195273505144137720281144952633187328104308851991443414485\n" +
                "0769074055210349393908526026280941224406641540185392686156667006213710909646\n" +
                "5315269430579606804522766629316491799938881532036263624325150249567077518590\n" +
                "2006723031476011001324233472547621472955077926046653763878985433431801976163\n" +
                "7974941428134868085973311602322501782621982275644290516451054293572374452480\n" +
                "3911581949679128043190384027707038810227156972598553529591524905775695222573\n" +
                "0875012733014159111744932260364673525516960374859936844249770805745135988591\n" +
                "2193134358765406947132525211368035041254965875522053580523581542222674951614\n" +
                "5461953753692574106142406030355505755322399918508709381684832433533666089436\n" +
                "6914536008964041968014668432311296730161963940103901395538351838647244575735\n" +
                "9682183966588570846490781297748803096669043495976367525720688601405724763165\n" +
                "9366405215442599572170724253845370276051746969542630131404706122033207700926\n" +
                "9634307833350310685545482326576347152898034022613833146582458999694407660181\n" +
                "3181216257512478353875929510033615157380055006621167362738107364775389630233\n" +
                "2688193949271671590098808189874707140674340429866558011612508913109911378221\n" +
                "4525822552714678618722624019557909748395640215951920138535194051135784862992\n" +
                "7676479576245288784761128654375063665070795558724661301823367627584801874299\n" +
                "6842212488540287256799429560965671793827034735662626056840175206274829377610\n" +
                "2679026179077111133747580727309768768462870530712030151777528776611999592543\n" +
                "5469084241621676683304471834572680044820209840073342161556483650809819700519\n" +
                "5069776917591932362228786798410919832942854232453906488744015483345217479881\n" +
                "8376286034430070286238158296832026077212997860853621269226851380258734847713\n" +
                "1506570235306101660385046348171539166751553427727411632881735764490170113069\n" +
                "1657329122949984720383009065906081287562391769379647550168075786900275033105\n" +
                "8368136905368238769481511663440969746358357950494040949373535703371702149332\n" +
                "7900580625445655495568911072629112763573393093613120097789548741978201079759\n" +
                "6006926759702556789243149638382397881226443923805643271875318325353977793623\n" +
                "3065091168381379240093714984211062442701436283997147804828358969135173678313\n" +
                "1302842008760313741468880513395130895385555668737903636548843842314352653560\n" +
                "1507588876438830599979627177345421208108383416522359172863400224101049200366\n" +
                "7108077824182540490985025975348884587959661811143933992054599164714271162269\n" +
                "5324437200774167260873994091881430172150351431190791484160130386064989304268\n" +
                "1063461342569012936742385657532851176530508301925231740857695833757767138637\n" +
                "0581644086788224508505653458286645614749923601915045329530406437811958846898\n" +
                "2377508403789345013751655291840684526841193847407558317204848731791789752470\n" +
                "2047468676785168585107466639502903127228277477844555363145563817866965468065\n" +
                "6004889477437098910053987980034633798512783946215748191911989406132219619028\n" +
                "2163593765916267698889681165325455841906896200468574921055312213507181419985\n" +
                "7390241417948456758683915656514210043801568707344402566975654476927778343689\n" +
                "5181827442192115717632678007537944382194152999898135886727106040541917082611\n" +
                "7958643170011205052460018335540389120912702454925129876744422939798309055719\n" +
                "6699602729958235713558113476622186003870836304369970459407150860751757155953\n" +
                "9574208006519303249465213405317306712845064683711928287664255882652541509626\n" +
                "1587042884791687888356931852908220433941122542528267291433228073749618009575\n" +
                "2266476520321890597942344911887542018946725068758276459203422634205969237037\n" +
                "7915860242436589589487623510237343383925842925537333197514515636141387389222\n" +
                "7768157278190893348273403280024447387185494550269939900173168992563919731078\n" +
                "7409595599470306610980951410817968829581338494448405826627279209534481002342\n" +
                "8746052765610675359070999286951207089742283793419578282571050219007843536722\n" +
                "0128348782723846388917184475909137210960578588163390037689721910970764999490\n" +
                "6794336355302027095854130589793215850056025262300091677701945604563512274625\n" +
                "6493837221146989915452785983498013649791178567790567383110930019354181521849\n" +
                "8522970357543037587857797697871040032596516247326067026586533564225323418559\n" +
                "6414383212794132627075481307935214423958439441784659264541102169705612712641\n" +
                "3364571661403687480900478761332382601609452989711724559437710094733625343791\n" +
                "3822960171131710298461851889579301212182947947438117551186716919629214627686\n" +
                "6659875725781807069369461138840786390954466741367016400122236935927472751349\n" +
                "6995008658449628195986553937047977997351533054904757054089844147906692472242\n" +
                "0291331363960476724050117143730263828053376795354996434756035448711418967097\n" +
                "8914128552510950392176836100641743913619231944585922379545686054802892940816\n" +
                "9501884486095548538249974533971681776510135479270652948966079250280126311500\n" +
                "4583885097280228175613731282469186105734949577263929917490068248955892838952\n" +
                "8369155546500967709076092492614851787715187939288988489355200162982581236630\n" +
                "3351329326678211266170927982071916063970663135377406539676072119792197208923\n" +
                "2963227055108485280635632066822858307018437404502393806661913008047264948558\n" +
                "6227971885504736681267189569114800855658551492309259447896506060914739737190\n" +
                "5877559335845981460731384333299378628909035346847305694203931954772333706835\n" +
                "0820006111846796373637567484975043292248140979932769685239889986757665274523\n" +
                "7852704492207395334623612101456656819802383620250585718651319140266883976774\n" +
                "9821737801772435570948354894570642762554751960884180503208719568096159722929\n" +
                "6118977284302740144647040847509422430477742691249872669858408882550677396353\n" +
                "2647448303962514006315575022919425486401140878068656412345930528674747886319\n" +
                "6495874503412447794641947641845777732504838545380462463074258938575939696444\n" +
                "9424467760008149129061831516756646633391056330854639910359580319853315676887\n" +
                "0326983803605989609860446164816135275542426403178160334114291535092187022511\n" +
                "9690333095650402363247427931139859427523683406335947845574004278292757461546\n" +
                "2972394825303045736986859529387796679229907303656921666496893144545176734236\n" +
                "5284710196597738616685341754100030559233981868187837424875216461240704899663\n" +
                "8484261994499337883263726189263522461036976673118060507283284099011918101252\n" +
                "9285932565957013344198838749108689008862177854741774472853213812773759804420\n" +
                "9025160435978404807986146480594886421513700723235204237547112152388713456249\n" +
                "3633492920444127533869817663237241519812570031577875114597127432005704390343\n" +
                "2820617296526433737394315982479372517062238973209738209228888662524192726902\n" +
                "3123153712946928796984822247122338800040745645309157583783233166955281654273\n" +
                "1995517979015992665783844351634919018029948049302230824080676377712132015890\n" +
                "8016705714576754609351125598451665478252011816237139655699297137618417031679\n" +
                "7392278700213914637873077314861974126515228684934297646938983396149536518284\n" +
                "6083324844657227258836711826423550982988693083426708770500152796169909340939\n" +
                "1871243760823062035244983192421309972496689416318630946317612305184883365590\n" +
                "3025364164204950595905062646429662829785066720994193745543445044310889273708\n" +
                "8723642660690638687990221045125802179892024039930732402974432107568503616176\n" +
                "0211877355607619435672812468167464602220637669349088316186207599062850157889\n" +
                "3755729856371600285219517164103086482632168686971579912396862585311194866048\n" +
                "6910461444433126209636345115615768564734643984924111235611694000203728226545\n" +
                "7879189161658347764082713659977589895079963328919221758174595090149740246511\n" +
                "1541204033818885606600794540083528572883773046755627992258327391260059081185\n" +
                "6982784964856880920851583986961393501069573189365386574309870632576143424671\n" +
                "4882346949169807476825914230416624223286136294183559132117754914943465417133\n" +
                "5438525007639808495467046959356218804115310176224915962106549862483447081593\n" +
                "1547315880615259244168279515126820821024752979525313232148314148925333604970\n" +
                "9687277172252215544463685443618213303453193439951105225629010899460120199653\n" +
                "6620148721605378425180808801059386778038097178364062340837323011103188346745\n" +
                "4415809310379953142507894468778649281858001426097585820515432413160843434857\n" +
                "8995619843129265559743302434552307222165631048181725578078842823673219924620\n" +
                "5561780584700010").replaceAll("\n", "").equals(ans2));*/
//        out.close();
//        System.out.printf("IT TAKES %f SECONDS LONG!!!!! WHY IS IT OVER 1 SECOND%n", (System.nanoTime()-start)/1e9);
        System.exit(0);
    }
    static int gcd(int a, int b){
        if(a == 0)
            return b;
        return gcd(b%a, a);
    }
}