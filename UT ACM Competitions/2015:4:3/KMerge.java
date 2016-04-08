import java.util.*;

/**
 * Created by Michael on 4/3/15.
 */
public class KMerge {

    static int sortBy = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int D = in.nextInt(), Q = in.nextInt(), S = in.nextInt();
        in.nextLine();
        Map<Integer, List<Data>> data = new HashMap<>();
        List<Data> all = new ArrayList<>();
        for (int i = 0; i < D; i++) {
            String[] tmp = in.nextLine().split(" ");
            int id = i(tmp[1]);
            Data uh = new Data(tmp[0], i(tmp[1]), i(tmp[2]), i(tmp[3]), i(tmp[4]));
            if(!data.containsKey(id)) {
                data.put(id, new ArrayList<Data>());
            }
            data.get(id).add(uh);
            all.add(uh);
        }
        for (int i = 0; i < Q; i++) {
            int k = in.nextInt();
            String cmd = in.next().intern();
            sortBy = cmd == "id" ? 0 : cmd == "size" ? 1 : cmd == "lm" ? 2 : cmd == "lr" ? 3 : -1;
            int id = in.nextInt();
            if(id == -1) {
                Collections.sort(all);
                System.out.println(all.get(k).id);
            } else {
                Collections.sort(data.get(id));
                System.out.println(data.get(id).get(k).id);
            }
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }

    static class Data implements Comparable<Data> {

        String id;
        int shardID;
        int size;
        int lastModified;
        int lastRead;

        public Data(String id, int shardID, int size, int lastModified, int lastRead) {
            this.id = id;
            this.shardID = shardID;
            this.size = size;
            this.lastModified = lastModified;
            this.lastRead = lastRead;
        }

        @Override
        public int compareTo(Data o) {
            switch (sortBy) {
                case 0: // id
                    return id.compareTo(o.id);
                case 1: // size
                    return size - o.size;
                case 2: // lm
                    return lastModified - o.lastModified;
                case 3:
                    return lastRead - o.lastRead;
            }
            return -1;
        }
    }
}
