import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution20C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = in.readLine().split(" ");
        int N = i(tmp[0]), M = i(tmp[1]);
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String[] str = in.readLine().split(" ");
            int intA = i(str[0]), intB = i(str[1]), weight = i(str[2]);
            Node src = map.containsKey(intA) ? map.get(intA) : new Node(intA);
            Node dest = map.containsKey(intB) ? map.get(intB) : new Node(intB);
            src.distance = Integer.MAX_VALUE;
            dest.distance = Integer.MAX_VALUE;
            if (src.nbors.containsKey(intB)) {
                if (weight < src.nbors.get(intB).weight) {
                    src.nbors.put(intB, new Edge(dest, weight));
                }
            } else {
                src.nbors.put(intB, new Edge(dest, weight));
            }

            if (dest.nbors.containsKey(intA)) {
                if (weight < dest.nbors.get(intA).weight) {
                    dest.nbors.put(intA, new Edge(src, weight));
                }
            } else {
                dest.nbors.put(intA, new Edge(src, weight));
            }
            map.put(intA, src);
            map.put(intB, dest);
        }

//        System.out.println(map.get(10).nbors.keySet());
        int start = 1;
        int end = N;

        PriorityQueue<Node> frontier = new PriorityQueue<>();
        if (map.get(start) == null) {
            System.out.println(-1);
        } else {
            frontier.add(map.get(start));
            map.get(start).distance = 0;

            boolean done = false;
            while (!frontier.isEmpty()) {
                Node next = frontier.poll();
                if (next.id == end) {
                    done = true;
                    List<Integer> out = new ArrayList<>();
                    while(next != null) {
                        out.add(next.id);
                        next = next.before;
                    }
                    for (int i = out.size() - 1; i >= 0;i--) {
                        System.out.print(out.get(i) + " ");
                    }
                    break;
                }
//            System.out.println("id " + next.id);
                if (!next.visited) {
                    next.visited = true;
//                for (Node node : map.values()) {
//                    System.out.println(node);
//                }
//                System.out.println(next.id);
//                System.out.println();
                    for (int val : next.nbors.keySet()) {
                        Edge e = next.nbors.get(val);
                        if (!e.dest.visited) {
                            if (next.distance + e.weight < e.dest.distance) {
                                e.dest.before = next;
                                e.dest.distance = next.distance + e.weight;
                            }
                            frontier.add(e.dest);
                        }
                    }
                }
            }
            if (!done) {
                System.out.println(-1);
            }

        }
    }

    static class Node implements Comparable<Node> {
        int id;
        Map<Integer, Edge> nbors;
        boolean visited;
        int distance;
        Node before;

        public Node(int id) {
            this.id = id;
            visited = false;
            nbors = new HashMap<>();
            distance = 0;
        }

        public int compareTo(Node other) {
            return distance - other.distance;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                return ((Node) obj).hashCode() == hashCode();
            }
            return false;
        }

        public String toString() {
            return id + " " + distance;
        }
    }

    static class Edge {
        Node dest;
        int weight;

        public Edge(Node dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
