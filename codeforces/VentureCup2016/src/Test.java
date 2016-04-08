import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    public static void main(String... bob) throws Exception {
//        String[] str = "num(1,1,1) num(1,2,2) num(1,3,3) num(1,4,4) num(1,5,5) num(2,1,2) num(3,1,3) num(4,1,4) num(5,1,5) num(2,2,5) num(2,3,4) num(2,4,1) num(2,5,3) num(3,2,1) num(3,3,5) num(3,4,2) num(3,5,4) num(4,2,3) num(4,3,2) num(4,4,5) num(4,5,1) num(5,2,4) num(5,3,1) num(5,4,3) num(5,5,2)".split(" ");
//        help();
//        findCenter();
        String str = "levenshtein: levenshtein_recursive('acb', 'ab')\\nlevenshtein: levenshtein_recursive('cb', 'b')\\nlevenshtein: levenshtein_recursive('cb', '')\\nlevenshtein: levenshtein_recursive -> 2\\nlevenshtein: levenshtein_recursive('b', 'b')\\nlevenshtein: levenshtein_recursive('', '')\\nlevenshtein: levenshtein_recursive -> 0\\nlevenshtein: levenshtein_recursive -> 0\\nlevenshtein: levenshtein_recursive('b', '')\\nlevenshtein: levenshtein_recursive -> 1\\nlevenshtein: levenshtein_recursive -> 1\\nlevenshtein: levenshtein_recursive -> 1\\n\"";
        String tmp = "levenshtein: levenshtein_recursive('acb','ab')\\nlevenshtein: levenshtein_recursive('cb','b')\\nlevenshtein: levenshtein_recursive('cb','')\\nlevenshtein: levenshtein_recursive -> 2\\nlevenshtein: levenshtein_recursive('b','b')\\nlevenshtein: levenshtein_recursive('','')\\nlevenshtein: levenshtein_recursive -> 0\\nlevenshtein: levenshtein_recursive -> 0\\nlevenshtein: levenshtein_recursive('b','')\\nlevenshtein: levenshtein_recursive -> 1\\nlevenshtein: levenshtein_recursive -> 1\\nlevenshtein: levenshtein_recursive -> 1\\n";
    }

    private static void analyze (String str) {
        String[] sentences = str.split("[.?]");
        List<Integer> lengths = new ArrayList<>();
        int sum = 0;
        for (String sentence : sentences) {
            int words = sentence.split(" ").length;
            lengths.add(words);
            sum += words;
        }
        System.out.println(lengths);
        System.out.println(sum + " words");
        System.out.println(sum / sentences.length);
    }

    private static void findCenter() {
        Scanner in = new Scanner(System.in);
        double[] nums = {0, 0, 0};
        int count = 0;
        while (true) {
            String[] str = in.nextLine().split(" ");
            if (str.length == 1) {
                break;
            }
            for (int i = 0; i < 3; i++) {
                nums[i] += Double.parseDouble(str[i]);
            }
            ++count;
        }
        for (int i = 0; i < 3; i++) {
            nums[i] /= count;
        }
        System.out.println(Arrays.toString(nums));
    }

    private static void help () {
        double[] centeroid = {4.73, 12.7, 21.4};
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            double[] document = {in.nextDouble(), in.nextDouble(), in.nextDouble()};
            for (int i = 0; i < 3; i++) {
                System.out.printf("(%.1f - %.3f)^2 + ", document[i], centeroid[i]);
            }
            System.out.println();
            double ans = 0;
            for (int i = 0; i < 3; i++) {
                System.out.printf("(%.3f)^2 ", document[i] - centeroid[i]);
                ans += (document[i] - centeroid[i]) * (document[i] - centeroid[i]);
            }
            System.out.println(ans);
        }
    }

    private static void solveLatinCube() {
        String[] str = new Scanner(System.in).nextLine().split(" ");
        int size = (int)Math.sqrt(str.length);
        int[][] num = new int[size][size];
        for (String s : str) {
            s = s.substring(4, s.length() - 1);
            String[] nums = s.split(",");
            int r = i(nums[0]) - 1, c = i(nums[1]) - 1, v = i(nums[2]);
            num[r][c] = v;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d ", num[i][j]);
            }
            System.out.println();
        }
        System.out.println(valid(num) ? "VALID" : "NOT VALID");
    }

    public static boolean valid (int[][] num) {
        for (int i = 0; i < num.length - 1; i++) {
            if (num[0][i] > num[0][i + 1]) {
                return false;
            }
        }
        for (int i = 0; i < num.length - 1; i++) {
            if (num[i][0] > num[i + 1][0]) {
                return false;
            }
        }
        for (int i = 0; i < num.length; i++) {
            Set<Integer> nums = new HashSet<>();
            for (int j = 0; j < num[i].length; j++) {
                nums.add(num[i][j]);
            }
            if (nums.size() != num[i].length) {
                return false;
            }
        }

        for (int i = 0; i < num[0].length; i++) {
            Set<Integer> nums = new HashSet<>();
            for (int j = 0; j < num.length; j++) {
                nums.add(num[j][i]);
            }
            if (nums.size() != num.length) {
                return false;
            }
        }
        return true;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
