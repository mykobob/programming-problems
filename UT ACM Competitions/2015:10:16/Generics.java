import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Generics {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Generics"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        outer:
        for (int i = 0; i < T; i++) {
            String str = in.readLine();
            Stack<Character> stuff = new Stack<Character>();
            Stack<Integer> stuffIdx = new Stack<Integer>();
            List<Pair> anglets = new ArrayList<Pair>();
            List<Pair> hardBrackets = new ArrayList<Pair>();
            Set<Integer> commas = new HashSet<Integer>();
            boolean good = true;
            dance:
            for (int j = 0; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (ch == '<') {
                    stuff.push(ch);
                    stuffIdx.push(j);
                } else if (ch == '>') {
                    if (stuff.isEmpty()) {
                        good = false;
                        break dance;
                    } else {
                        if (stuff.pop() != '<') {
                            good = false;
                            break dance;
                        } else {
                            anglets.add(new Pair(stuffIdx.pop(), j));
                        }
                    }
                } else if (ch == '[') {
                    stuff.push(ch);
                    stuffIdx.push(j);
                } else if (ch == ']') {
                    if (stuff.isEmpty()) {
                        good = false;
                        break dance;
                    } else {
                        if (stuff.pop() != '[') {
                            good = false;
                            break dance;
                        } else {
                            hardBrackets.add(new Pair(stuffIdx.pop(), j));
                        }
                    }
                } else if (ch == ',') {
                    commas.add(j);
                }
            }
            if (!good || !stuff.isEmpty()) {
                System.out.println("INCORRECT");
            } else {
                for (int j = 0; j < hardBrackets.size(); j++) {
                    int start = hardBrackets.get(j).start;
                    int end = hardBrackets.get(j).end;
                    if (start+1 != end) {
                        System.out.println("INCORRECT");
                        continue outer;
                    }
                }
                for (int j = 0; j < anglets.size(); j++) {
                    int start = anglets.get(j).start;
                    int end = anglets.get(j).end;
                    String sub = str.substring(start + 1, end);
                    int commaIdx = 0;
                    while (true) {
                        int tmpIdx = sub.indexOf(",", commaIdx);
                        if (tmpIdx == -1) {
                            break;
                        }
                        commaIdx = tmpIdx + 1;
                        commas.remove(tmpIdx + start + 1);
//                        System.out.println(tmpIdx + start + 1);
                    }
                    String[] split = sub.split(",");
                    for (String s : split) {
                        if (s.isEmpty()) {
                            System.out.println("INCORRECT");
                            continue outer;
                        }
                    }
                }
                if (!commas.isEmpty()) {
                    System.out.println("INCORRECT");
                } else {
                    System.out.println("CORRECT");
                }
            }
        }
    }

    static class Pair {
        int start, end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + " " + end;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
