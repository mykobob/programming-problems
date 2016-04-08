import java.io.File;
import java.util.*;

/**
 * Created by Michael on 4/11/15.
 */
public class Dijkstra {

    static String[][] mat = {"1 i j k".split(" "), "i -1 k -j".split(" "), "j -k -1 i".split(" "), "k j -i -1".split(" ")};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("dijkstra.in"));
        int times = in.nextInt();
        outer:
        for (int i = 0; i < times; i++) {
            int L = in.nextInt(), K = in.nextInt();
            String str = in.next();
            if(L * K < 3) {
                System.out.printf("Case #%d: NO\n", i + 1);
                continue outer;
            }
            StringBuilder strSpaces = new StringBuilder();
            int[] spacesLocation = new int[L * K - 1];
            for (int j = 0; j < str.length(); j++) {
                strSpaces = strSpaces.append(str.charAt(j));
                strSpaces = strSpaces.append(" ");
            }
//            System.out.println(strSpaces);
            StringBuilder data = new StringBuilder();
            int spacesLocationIndex = 0;
            for (int j = 0; j < K; j++) {
                data = data.append(strSpaces);
            }
            data = data.deleteCharAt(data.length() - 1);
            for (int j = 0; j < data.length(); j++) {
                if(data.charAt(j) == ' ')
                    spacesLocation[spacesLocationIndex++] = j;
            }
//            System.out.println("data " + data);

            String[] prefix = new String[L * K];
            prefix[0] = str.charAt(0)+ "";
            for (int j = 1; j < spacesLocation.length; j++) {
                int start = spacesLocation[(j - 1)] + 1;
                int end = spacesLocation[j];
                prefix[j] = getVal(prefix[j-1], data.substring(start, end));
            }
            int lastOne = spacesLocation[spacesLocation.length - 1] + 1;
            String tmp = data.substring(lastOne);
            prefix[L * K - 1] = getVal(prefix[L * K - 2], tmp);

            System.out.println(Arrays.toString(prefix));

            String last = prefix[prefix.length-1];
            String lookFor = "";
            switch (last) {
                case "k": lookFor = "1"; break;
                case "-i": lookFor = "i"; break;
                case "i": lookFor = "j"; break;
                default:
                    lookFor = "k";
            }


//            for (int j = 0; j < spacesLocation.length; j++) {
//                for (int k = j + 1; k < spacesLocation.length; k++) {
////                    StringBuilder a = new StringBuilder(data.substring(0, spacesLocation[j]));
////                    System.out.println("a " + a);
////                    System.out.println("b " + b);
////                    System.out.println("c " + c);
////                    String ansA = reduce(a);
//                    String ansA = getVal(prefix[j], prefix[0]);
//                    if(!ansA.equals("i")) {
//                        continue;
//                    }
////                    StringBuilder b = new StringBuilder(data.substring(spacesLocation[j] + 1, spacesLocation[k]));
////                    String ansB = reduce(b);
//                    String ansB = getVal(prefix[k], prefix[j]);
//                    if(!ansB.equals("j")) {
//                        continue;
//                    }
////                    StringBuilder c = new StringBuilder(data.substring(spacesLocation[k] + 1));
////                    String ansC = reduce(c);
//                    String ansC = getVal(prefix[prefix.length - 1], prefix[k]);
//                    if(!ansC.equals("k")) {
//                        continue;
//                    }
//                    System.out.printf("Case #%d: YES\n", i + 1);
//                    continue outer;
//                }
////                System.out.println(memo);
//            }
            System.out.printf("Case #%d: NO\n", i + 1);
        }
    }



    public static String reduce(StringBuilder str) {
        String toString = str.toString();
        if(memo.containsKey(toString)) {
            return memo.get(toString);
        }
        while(str.length() != 1) {
//            if(str.length() >= 30) {
//                System.exit(0);
//            }
//            System.out.println("str " + str + ".");
            int aStart = 0;
            int aEnd = str.indexOf(" ", 0);
            String first = str.substring(aStart, aEnd);

            int bStart = aEnd + 1;
            int bEnd = str.indexOf(" ", bStart);
            if(bEnd < 0) {
                bEnd = str.length();
            }
            String second = str.substring(bStart, bEnd);
            str = str.delete(aStart, bEnd + 1);

//            System.out.printf("First %s, Second %s\n", first, second);

            String result = getVal(first, second);
//            System.out.printf("Result %s, Str %s\n", result, str);
            if(str.length() == 0) {
                memo.put(toString, result);
                return result;
            } else {
                str = str.insert(0, result + " ");
            }
        }
        memo.put(toString, toString);
        return toString;
    }

    static Map<String, String> memo = new HashMap<>();

    public static String getVal(String a, String b) {
        boolean negative = false;
        if(a.contains("-")) {
            negative = !negative;
            a = a.replaceAll("-", "");
        }
        if(b.contains("-")) {
            negative = !negative;
            b = b.replaceAll("-", "");
        }
        int aIdx = getIndex(a);
        int bIdx = getIndex(b);
        String toReturn = mat[aIdx][bIdx];
        if(negative) {
            if(toReturn.contains("-")) {
                toReturn = toReturn.replace("-", "");
            } else {
                toReturn =  "-" + toReturn;
            }
        }
        return toReturn;
    }

    public static int getIndex(String ch) {
        switch (ch) {
            case "1": return 0;
            case "i": return 1;
            case "j": return 2;
            case "k": return 3;
        }
        return -1;
    }

}
