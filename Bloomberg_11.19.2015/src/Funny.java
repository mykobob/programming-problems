import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Funny {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Funny"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int F = i(testCases.nextToken()), E = i(testCases.nextToken()), A = i(testCases.nextToken()), B = i(testCases.nextToken());
        Elevator[] elevators = new Elevator[E];
        for (int i = 0; i < E; i++) {
            StringTokenizer str = new StringTokenizer(in.readLine());
            elevators[i] = new Elevator(i(str.nextToken()), i(str.nextToken()), F);
        }
        if (good(A, B, elevators, new HashSet<Integer>())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static boolean good (int start, int end, Elevator[] elevators, Set<Integer> visited) {
        if (start == end) {
            return true;
        }
        if (visited.add(start)) {
            for (Elevator elevator : elevators) {
                if (elevator.floors.contains(start)) {
                    for (Integer floor : elevator.floors) {
                        if (good(floor, end, elevators, visited)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    static class Elevator {
        int jump;
        int start;
        int max;
        Set<Integer> floors;

        public Elevator(int jump, int start, int max) {
            this.jump = jump;
            this.start = start;
            this.max = max;
            floors = new HashSet<Integer>();
            genFloors();
        }

        private void genFloors() {
            int tmp = start;
            while (tmp < max) {
                floors.add(tmp);
                tmp += jump;
            }
        }


    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
