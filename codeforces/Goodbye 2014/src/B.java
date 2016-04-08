import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    public static void main(String...bob) throws Exception {
//        BufferedReader in = new BufferedReader(new FileReader("goodbye2014B.in"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int numNums = Integer.parseInt(in.readLine());
        int[] nums = new int[numNums];
        String[] data = in.readLine().split(" ");
        Map<Integer, Integer> positions = new HashMap<>();
        for (int i = 0; i < numNums; i++) {
            nums[i] = Integer.parseInt(data[i]) - 1;
            positions.put(nums[i], i);
        }
        best = Arrays.copyOf(nums, numNums);

        Map<Integer, List<Integer>> canSwitch = new HashMap<>();
        for (int i = 0; i < numNums; i++) {
            String tmp = in.readLine();
            for (int j = 0; j < numNums; j++) {
                if(tmp.charAt(j) == '1') {
                    if(canSwitch.containsKey(i)) {
                        canSwitch.get(i).add(j);
                    } else {
                        canSwitch.put(i, new ArrayList<Integer>());
                        canSwitch.get(i).add(j);
                    }
                }
            }
        }

        solve(nums, numNums, canSwitch);
//        System.out.println(Arrays.toString(best));
        printArray(best, numNums);

//        boolean doneWithin = false;
//        for (int i = 0; i < numNums; i++) {
//            int index = positions.get(i);
//            if(!dfs(index, i, new boolean[numNums], nums, canSwitch)) {
//                printArray(nums, numNums);
//                doneWithin = true;
////                System.out.println(i);
//                break;
//            }
////            printArray(nums, numNums);
////            System.out.println();
//        }
//        if(!doneWithin) {
//            printArray(nums, numNums);
//        }
    }

    private static int[] best;

    public static boolean solve(int[] nums, int numNums, Map<Integer, List<Integer>> canSwitch) {

        for (int i = 0; i < numNums; i++) {
            if(best[i] != nums[i]) {
                if(nums[i] > best[i]) {
                    best = Arrays.copyOf(nums, numNums);
                }
            }
        }
//        printArray(nums, numNums);
//        System.out.println();

        for (int i = 0; i < numNums; i++) {
            List<Integer> arr = canSwitch.get(i);
            if(arr == null)
                continue;
            for (int j = 0; j < arr.size(); j++) {
                if(i < arr.get(j) && nums[i] > nums[arr.get(j)]) {
                    swap(i, arr.get(j), nums);
                    solve(nums, numNums, canSwitch);
                    swap(arr.get(j), i, nums);
                }
            }
        }
        return false;
    }

    public static void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void printArray(int[] nums, int numNums) {
        for (int j = 0; j < numNums - 1; j++) {
            System.out.print(nums[j] + 1 + " ");
        }
        System.out.print(nums[numNums - 1] + 1);
    }

    public static boolean dfs(int index, int targetNum, boolean[] travelledTo, int[] nums, Map<Integer, List<Integer>> canSwitch) {
        if(travelledTo[index]) {
            return false;
        }
        if(index == targetNum) {
            return true;
        }
        if(index < targetNum) {
            return false;
        }
        List<Integer> toSwitch = canSwitch.get(index);

        for (int i = 0; i < toSwitch.size(); i++) {
            int value = toSwitch.get(i);
            travelledTo[index] = true;
            int tmp = nums[value];
            nums[value] = nums[index];
            nums[index] = tmp;

            if(dfs(toSwitch.get(i), targetNum, travelledTo, nums, canSwitch)) {
                return true;
            }

            tmp = nums[value];
            nums[value] = nums[index];
            nums[index] = tmp;
            travelledTo[i] = false;
        }
        return false;
    }
}
