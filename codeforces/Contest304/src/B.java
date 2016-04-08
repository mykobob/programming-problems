import java.util.*;

public class B {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Integer[] nums = new Integer[N];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            nums[i] = in.nextInt();
            if(map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]));
            } else {
                map.put(nums[i], 1);
            }
        }

        Arrays.sort(nums);

        int keys = 0;
        int index = 0;
        while(!map.isEmpty()) {
            if(index < N - 1) {
                if(nums[index].equals(nums[index + 1])) {
                    int num = nums[index + 1]++;
                    keys++;
                    map.put(num, map.get(num) - 1);
                    if (map.get(num) == 1) {
                        map.remove(num);
                    }

                    num = nums[index + 1];
                    if(!map.containsKey(num)) {
                        map.put(num, 0);
                    }
                    map.put(num, map.get(num) + 1);
                }
                index++;
                Arrays.sort(nums);
            } else {
                break;
            }
        }
        System.out.println(keys);
    }
}
