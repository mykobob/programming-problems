import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution747C {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out;
        out = new WriteMe();
        int numServers = in.nextInt(), numTasks = in.nextInt();
        Task[] tasks = new Task[numTasks];
        int[] servers = new int[numServers];

        for (int i = 0; i < numTasks; i++) {
            tasks[i] = new Task(in.nextInt(), in.nextInt(), in.nextInt());
        }
        Arrays.sort(tasks);

        for (int i = 0; i < numTasks; i++) {
            Task cur = tasks[i];
            List<Integer> usableServers = new ArrayList<>();
            for (int j = 0; j < numServers; j++) {
                if (servers[j] <= cur.timeStart) {
                    usableServers.add(j);
                    if (usableServers.size() == cur.servers) {
                        break;
                    }
                }
            }
            if (usableServers.size() < cur.servers) {
                System.out.println(-1);
            } else {
                usableServers.forEach(serverId -> servers[serverId] = cur.timeStart + cur.timeNeeded);
                System.out.println(usableServers.stream().reduce((a, b) -> a + b).get() + usableServers.size());
            }
        }

        out.close();
    }

    static class Task implements Comparable<Task> {
        int timeStart, servers, timeNeeded;

        public Task(int timeStart, int servers, int timeNeeded) {
            this.timeStart = timeStart;
            this.servers = servers;
            this.timeNeeded = timeNeeded;
        }

        @Override
        public int compareTo(Task o) {
            return timeStart - o.timeStart;
        }
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution747C.out")));
            } catch (Exception e) {

            }
        }

        public void caseInt(int num, int ans) {
            caseStr(num, String.valueOf(ans));
        }

        public void caseStr(int num, String ans) {
            out.printf("Case #%d: %s\n", num, ans);
        }

        public void writeln(String str) {
            out.println(str);
        }

        public void write(String str) {
            out.print(str);
        }

        public void close() {
            out.close();
        }
    }

    static class FAST {
        private BufferedReader in;
        private StringTokenizer str;

        public FAST() {
            in = new BufferedReader(new InputStreamReader(System.in));
            // in = new BufferedReader(new FileReader("Solution747C.in"));
        }

        private String next() {
            while (str == null || !str.hasMoreElements()) {
                try {
                    str = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                }
            }
            return str.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() throws Exception {
            return in.readLine();
        }
    }
}
