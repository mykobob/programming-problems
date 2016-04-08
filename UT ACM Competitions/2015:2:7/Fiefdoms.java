import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 2/7/15.
 */
public class Fiefdoms {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input (6).txt"));
        PrintWriter out = new PrintWriter("fiefdoms.out");
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int numCities = in.nextInt();
            int numRoads = in.nextInt();
            int capA = in.nextInt(), capB = in.nextInt();
            int totalCost = 0;
            Map<Integer, Node> graph = new HashMap<Integer, Node>();
            for (int j = 0; j < numRoads; j++) {
                int src = in.nextInt();
                int dest = in.nextInt();
                int cost = in.nextInt();
                totalCost += cost;
                if(!graph.containsKey(src)) {
                    graph.put(src, new Node(src));
                }
                if(!graph.containsKey(dest)) {
                    graph.put(dest, new Node(dest));
                }

                graph.get(src).addNbor(graph.get(dest), cost);
                graph.get(dest).addNbor(graph.get(src), cost);
            }

            int ans = doublePrims(graph, capA, capB, totalCost);
            System.out.println(ans);
            out.println(ans);
//            System.out.println();
        }
        out.close();
    }

    private static int doublePrims(Map<Integer, Node> graph, int capA, int capB, int total) {



        PriorityQueue<Edge> frontier = new PriorityQueue<Edge>();
        frontier.addAll(graph.get(capA).nbors);
        frontier.addAll(graph.get(capB).nbors);
        graph.get(capA).visit = true;
        graph.get(capB).visit = true;

        while(!frontier.isEmpty()) {
            Edge next = frontier.poll();
            Node n = next.dest;
            if(!n.visit) {
//                System.out.println(n + " " + next.cost);
                n.visit = true;
                total -= next.cost;
                for(Edge e : n.nbors) {
                    frontier.add(e);
                }
            }
        }
        return total;
    }

    static class Node {
        List<Edge> nbors;
        int id;
        boolean visit;

        public Node(int tmp) {
            id = tmp;
            nbors = new ArrayList<Edge>();
            visit = false;
        }

        public void addNbor(Node n, int c) {
            nbors.add(new Edge(n, c));
        }

        public String toString() {
            return id + "";
        }
    }

    static class Edge implements Comparable<Edge> {
        Node dest;
        int cost;

        public Edge(Node d, int c) {
            dest = d;
            cost = c;
        }

        public int compareTo(Edge other) {
            return other.cost - cost;
        }
    }
}
