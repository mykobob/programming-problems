import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TransformCsv {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        String data;
        while ((data = in.nextLine()) != null) {
            int index = data.indexOf(",");
            StringBuilder personTimes = new StringBuilder();
            String name = data.substring(0, index);
            personTimes.append(name);
            personTimes.append("\n");


            List<AvailableTime> condensedTimes = new ArrayList<>();
            while (index < data.length() - 1) {

                index += 1;
                int endIndex = data.indexOf(",", index);
                int nextStartIndex = endIndex;
                if (data.charAt(index) == '"') { // multiple entries in the column
                    endIndex = data.indexOf("\"", index + 1);
                    index += 1;
                    nextStartIndex = endIndex + 1;
                }

                // This is time for one day
                AvailableTime today = new AvailableTime();
                condensedTimes.add(today);
                if (endIndex != -1) {
                    ArrayDeque<AvailableTime> eachDay = Arrays.stream(data.substring(index, endIndex).split(", "))
                            .map(AvailableTime::parse)
                            .collect(Collectors.toCollection(ArrayDeque::new));

                    AvailableTime starting = eachDay.pollFirst();
                    if (eachDay.isEmpty()) {
                        today.combine(starting);
                    } else {
                        while (!eachDay.isEmpty()) {
                            while (!eachDay.isEmpty() && starting.canMerge(eachDay.peekFirst())) {
                                starting = starting.merge(eachDay.pollFirst());
                            }
                            today.combine(starting);
                            if (!eachDay.isEmpty()) {
                                starting = eachDay.pollFirst();
                                if (eachDay.isEmpty()) {
                                    today.combine(starting);
                                }
                            }
                        }
                    }
                    index = nextStartIndex;
                } else {
                    break;
                }
            }
            condensedTimes.forEach(time -> {
                personTimes.append(time.toString());
                personTimes.append("\n");
            });

            out.write(personTimes.toString());
        }
        out.close();
    }

    static class WriteMe {
        private PrintWriter out;

        public WriteMe() {
            try {
                out = new PrintWriter(new BufferedWriter(new FileWriter("TransformedCsv2.in")));
            } catch (Exception e) {

            }
        }

        public void caseAns(int num, int ans) {
            caseAns(num, String.valueOf(ans));
        }

        public void caseAns(int num, String ans) {
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

        public FAST() throws Exception {
             in = new BufferedReader(new FileReader("schedules.csv"));
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
