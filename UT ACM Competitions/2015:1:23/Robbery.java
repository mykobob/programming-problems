import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Michael on 1/23/15.
 */
public class Robbery {
    public static void main(String... bob) throws Exception {
        Scanner in = new Scanner(new File("robbery.txt"));
//        Scanner in = new Scanner(new File("input.txt"));
        PrintWriter out = new PrintWriter("robbery.out");
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int numRooms = in.nextInt();
            int numDoors = in.nextInt();
            int[] rooms = new int[numRooms];
            for (int j = 0; j < numRooms; j++) {
                rooms[j] = in.nextInt();
            }
            Door[] doors = new Door[numDoors];
            for (int j = 0; j < numDoors; j++) {
                doors[j] = new Door(in.nextInt(), in.nextInt(), in.nextInt());
            }
//            System.out.println(Arrays.toString(doors));
            int ans = solve(doors, 1, rooms, 0, rooms[0]);
            System.out.println(ans);
        }
        out.close();
        System.exit(0);
    }

    public static int solve(Door[] doors, int unlockedRooms, int[] rooms, int index, int value) {
        if(index == rooms.length) {
            return value;
        }
        if(unlockedRooms == 1 << (doors.length) - 1) {
//            System.out.println("val " + value);
//            System.out.println(Integer.toBinaryString(unlockedRooms));
            return value;
        }
        int best = -(1 << 25);
        for(int i = 0;i<doors.length;i++) {
            Door d = doors[i];
//            if(i == 1) {
//                System.out.println("hi");
//            }
            if(d.src == index) {
                if(!d.paid) {
                    unlockedRooms ^= 1 << d.dest;
                    d.paid = true;
                    int tmp = solve(doors, unlockedRooms, rooms, d.dest, value + rooms[d.dest] - d.cost);
                    d.paid = false;
                    best = Math.max(best, tmp);
                    unlockedRooms ^= 1 << d.dest;
                }
            }
            else if(d.dest == index) {
                if(!d.paid) {
                    unlockedRooms ^= 1 << d.src;
                    d.paid = true;
                    int tmp = solve(doors, unlockedRooms, rooms, d.src, value + rooms[d.src] - d.cost);
                    d.paid = false;
                    best = Math.max(best, tmp);
                    unlockedRooms ^= 1 << d.src;
                }
            }
        }
        return best == -(1 << 25) ? 0 : best;
    }

    static class Door {
        int src, dest;
        int cost;
        boolean paid;
        public Door(int s, int d, int c) {
            src = s;
            dest = d;
            cost = c;
            paid = false;
        }
        public String toString() {
            return String.format("%d -> %d: %d", src, dest, cost);
        }
    }
}
