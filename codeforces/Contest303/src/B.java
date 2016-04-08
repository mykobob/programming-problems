import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        int N = a.length();
        Set<Integer> diffs = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if(a.charAt(i) != b.charAt(i)) {
                diffs.add(i);
            }
        }
        if(diffs.size() % 2 == 1) {
            System.out.println("impossible");
        } else {
            int count = 0;
            for (int i = 0; i < N; i++) {
                if(diffs.contains(i) && count < diffs.size() / 2) {
                    System.out.print(a.charAt(i) == '0' ? '1' : '0');
                    count++;
                } else {
                    System.out.print(a.charAt(i));
                }
            }
            System.out.println();
        }
    }
}
