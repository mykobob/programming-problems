import java.util.*;

public class Factory {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        outer:
        while(T-- >0) {
            int numMachines = in.nextInt();
            Node[] map = new Node[numMachines];
            for (int i = 0; i < numMachines; i++) {
                map[i] = new Node(i, in.nextInt());
            }

            boolean bad = false;
            for (int i = 0; i < numMachines; i++) {
                int numDepend = in.nextInt();
                Node get = map[i];
                for (int j = 0; j < numDepend; j++) {
                    int num = in.nextInt() - 1;
                    if(num == j) {
                        bad = true;
                    } else {
                        get.depend.add(map[num]);
                    }
                }
            }
            if(bad) {
                System.out.println("ESCHER FACTORY");
                continue outer;
            }

            List<Node> starts = getNoDepend(map);
            if(starts.size() != 0) {
                for (int i = 0; i < starts.size(); i++) {
                    Node get = starts.get(i);
                    get.done = true;
                    get.timeTaken = get.length;
                }

                for (int i = 0; i < map.length; i++) {
                    dfs(map[i]);
                }
                for (int i = 0; i < map.length - 1; i++) {
                    System.out.print(map[i].timeTaken + " ");
                }
                System.out.println(map[map.length - 1].timeTaken);
            } else {
                System.out.println("ESCHER FACTORY");
            }
        }
    }

    public static void dfs(Node node) {
        if(node.done) {
            return;
        }

        List<Node> parents = node.depend;
        int max = 0;
        for (int i = 0; i < parents.size(); i++) {
            dfs(parents.get(i));
            max = Math.max(max, parents.get(i).timeTaken);
        }
        node.timeTaken += max + node.length;
    }

    public static ArrayList<Node> getNoDepend(Node[] map) {
        ArrayList<Node> toReturn = new ArrayList<Node>();
        for (int i = 0; i < map.length; i++) {
            Node get = map[i];
            if(get.depend.size() == 0) {
                toReturn.add(get);
            }
        }
        return toReturn;
    }

    static class Node {
        List<Node> depend;
        int id;
        int length;
        int timeTaken;
        boolean done;
        public Node(int i, int len) {
            id = i;
            depend = new ArrayList<>();
            length = len;
            timeTaken = 0;
            done = false;
        }
        public String toString() {
            return id + " " + length + " " + depend.size();
        }
    }
}
