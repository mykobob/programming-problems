import java.util.*;


public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int K = in.nextInt();
        String str = in.next();
        Map<Character, Integer> starts = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(!starts.containsKey(ch)) {
                starts.put(ch, i);
            }
        }

        if(starts.size() < K) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            if(K == 1) {
                System.out.println(str);
            } else {
                List<Integer> arr = new ArrayList<Integer>(starts.values());
                Collections.sort(arr);
//                System.out.println(arr);
                int i;
                for (i = 0; i < Math.min(K, arr.size()) - 1; i++) {
                    int start = arr.get(i);
                    int end = arr.get(i + 1);
                    System.out.println(str.substring(start, end));
                }
                if(i != Math.min(K, arr.size())) {
                    System.out.println(str.substring(arr.get(i)));
                }
            }
        }
    }
}
