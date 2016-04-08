import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Piggyback {
    public static void main(String...bob) throws Exception {
        Scanner in = new Scanner(new File("piggyback.in"));
        int bessie = in.nextInt();
        int elsie = in.nextInt();
        int both = in.nextInt();
        int numNodes = in.nextInt();
        int numEdges = in.nextInt();
        in.nextLine();

        Node[] graph = new Node[numNodes + 1];

        for (int i = 0; i < numEdges; i++) {
            String[] input = in.nextLine().split(" ");
            int src = Integer.parseInt(input[0]);
            int dest = Integer.parseInt(input[1]);
            if(graph[src] == null) {
                graph[src] = new Node(src);
            }
            if(graph[dest] == null) {
                graph[dest] = new Node(dest);
            }
            graph[src].addNbor(graph[dest]);
            graph[dest].addNbor(graph[src]);
        }

        // Do the one for dist for bessie (zero)
        Queue<Node> frontier = new LinkedList<Node>();
        frontier.add(graph[1]);
        graph[1].distToOne = 0;
        Set<Node> visited = new HashSet<Node>();
        visited.add(graph[1]);

        while(!frontier.isEmpty()) {
            Node next = frontier.poll();
                for(Node n : next.nbors) {
                    if(visited.add(n)) {
                        n.distToOne = next.distToOne + 1;
                        frontier.add(n);
                    }
                }
        }

        // Do the one for dist for elsie (one)
        frontier = new LinkedList<Node>();
        frontier.add(graph[2]);
        graph[2].distToTwo = 0;
        visited = new HashSet<Node>();
        visited.add(graph[2]);

        while(!frontier.isEmpty()) {
            Node next = frontier.poll();
                for(Node n : next.nbors) {
                    if(visited.add(n)) {
                        n.distToTwo = next.distToTwo + 1;
                        frontier.add(n);
                    }
                }
        }

        // Do the one for dist for the goal (numEdges)
        frontier = new LinkedList<Node>();
        frontier.add(graph[numNodes]);
        graph[numNodes].distToGoal= 0;
        visited = new HashSet<Node>();
        visited.add(graph[numNodes]);

        while(!frontier.isEmpty()) {
            Node next = frontier.poll();
                for(Node n : next.nbors) {
                    if(visited.add(n)) {
                        n.distToGoal = next.distToGoal + 1;
                        frontier.add(n);
                    }
            }
        }

//        for(Node n : graph) {
//            System.out.println(n);
//        }

        Node goal = graph[numNodes];

        int notTogether = goal.distToOne * bessie + goal.distToTwo * elsie;
//        System.out.println(notTogether);

        for(int i = 1; i <numNodes;i++) {
            Node n = graph[i];
            int togetherToGoal = n.distToGoal * both;
            int justBessie = n.distToOne * bessie;
            int justElsie = n.distToTwo * elsie;

            int total = togetherToGoal + justBessie + justElsie;
            notTogether = Math.min(notTogether, total);
        }
        PrintWriter out = new PrintWriter(new File("piggyback.out"));
        System.out.println(notTogether);
        out.println(notTogether);

        out.close();
        System.exit(0);
    }

    static class Node {
        List<Node> nbors;
        int distToGoal;
        int distToTwo;
        int distToOne;
        int id;

        public Node(int num) {
            id = num;
            nbors = new ArrayList<Node>();
            distToGoal = 0;
            distToTwo = 0;
            distToOne =  0;
        }

        public void addNbor(Node next) {
            nbors.add(next);
        }

        public int hashCode() {
            return id;
        }

        public String toString() {
            return String.format("%d: %d %d %d", id, distToOne, distToTwo, distToGoal);
        }
    }
}
