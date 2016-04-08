import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("B"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int N = i(testCases.nextToken());
        char[] cards = in.readLine().toCharArray();
        Arrays.sort(cards);
        Map<Character, Integer> out = new HashMap<>();
        out.put('B', 0);
        out.put('G', 0);
        out.put('R', 0);
        for (char card : cards) {
            Integer tmp = out.get(card);
            out.put(card, tmp + 1);
        }
        Map<Character, Integer> reduced = new HashMap<>();
        for (Character character : out.keySet()) {
            if (out.get(character) >= 2) {
                reduced.put(character, 2);
            } else {
                reduced.put(character, out.get(character));
            }
        }
        Character[] any2;
        if ((any2 = any2(reduced)).length > 0) {
            Character[] chs = anyEmpty(reduced);
            String ans = "BGR";
            switch (any2.length) {
                case 3:
                case 2:
                    System.out.println(ans);
                    break;
                case 1:
                    Character[] any1 = any1(reduced);
                    if (any1.length == 2) {
                        System.out.println(ans);
                    } else if (any1.length == 1){
                        for (int i = 0; i < any2.length; i++) {
                            ans = ans.replaceAll(any2[i] + "", "");
                        }
                        System.out.println(ans);
                    } else {
                        System.out.println(any2[0]);
                    }
            }
        } else {
            Character[] ch = anyEmpty(reduced);
            if (ch.length == 1) {
                System.out.println(ch[0]);
            } else {
                String ans = "BGR";
                for (int i = 0; i < ch.length; i++) {
                    ans = ans.replaceAll(ch[i]+"", "");
                }
                System.out.println(ans);
            }
        }
    }

    private static Character[] anyEmpty(Map<Character, Integer> reduced) {
        List<Character> absent = new ArrayList<>();
        for (Character character : reduced.keySet()) {
            if (reduced.get(character) == 0) {
                absent.add(character);
            }
        }
        return absent.toArray(new Character[0]);
    }

    private static Character[] any2(Map<Character, Integer> reduced) {
        List<Character> absent = new ArrayList<>();
        int count = 0;
        for (Character character : reduced.keySet()) {
            if (reduced.get(character) == 2) {
                absent.add(character);
            }
        }
        return absent.toArray(new Character[0]);
    }

    private static Character[] any1(Map<Character, Integer> reduced) {
        List<Character> absent = new ArrayList<>();
        int count = 0;
        for (Character character : reduced.keySet()) {
            if (reduced.get(character) == 1) {
                absent.add(character);
            }
        }
        return absent.toArray(new Character[0]);
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
