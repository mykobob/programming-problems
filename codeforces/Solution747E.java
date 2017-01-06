import java.io.*;
import java.util.*;

public class Solution747E {
    public static void main(String... bob) throws Exception {
        FAST in = new FAST();
        WriteMe out = new WriteMe();
        String[] commentData = in.nextLine().split(",");
        int[] idx = {0};
        while (idx[0] < commentData.length) {
            createStructure(commentData, idx, 1);
        }
        Collections.sort(allComments);
        out.write(levels);
        out.write("\n");
        int printIdx = 0;
        while (printIdx < allComments.size()) {
            int curIndex = printIdx;
            while (curIndex < allComments.size() && allComments.get(printIdx).level == allComments.get(curIndex).level) {
                out.write(allComments.get(curIndex).text);
                out.write(" ");
                curIndex++;
            }
            out.write("\n");
            printIdx = curIndex;
        }
        out.flush();
        out.close();
    }

    static List<Comment> allComments = new ArrayList<>();
    static int levels = 1;
    private static void createStructure(String[] commentData, int[] idx, int level) {
        levels = Math.max(levels, level);
        String text = commentData[idx[0]++];
        int subComments = Integer.parseInt(commentData[idx[0]++]);
        Comment comment = new Comment(text, level, idx[0]);
        for (int i = 0; i < subComments; i++) {
            createStructure(commentData, idx, level + 1);
        }
        allComments.add(comment);
    }

    static class Comment implements Comparable<Comment> {
        String text;
        int level, pos;

        public Comment(String text, int level, int pos) {
            this.text = text;
            this.level = level;
            this.pos = pos;
        }

        public String toString() {
            return text;
        }

        @Override
        public int compareTo(Comment o) {
            if (level == o.level) {
                return pos - o.pos;
            }
            return level - o.level;
        }
    }

    static class WriteMe {
        private OutputStream out;

        public WriteMe() {
            try {
//                out = new PrintWriter(new BufferedWriter(new FileWriter("Solution747D.out")));
                out = new BufferedOutputStream(System.out);
            } catch (Exception e) {

            }
        }

        public void write(int val) throws IOException {
            write(String.valueOf(val));
        }

        public void write(String str) throws IOException {
            out.write(str.getBytes());
        }

        public void flush() throws IOException {
            out.flush();
        }

        public void close() throws IOException {
            out.close();
        }
    }

    static class FAST {
        private BufferedReader in;
        private StringTokenizer str;

        public FAST() throws FileNotFoundException {
            in = new BufferedReader(new InputStreamReader(System.in));
//             in = new BufferedReader(new FileReader("Solution747D.in"));
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
