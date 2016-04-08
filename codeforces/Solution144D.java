import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution144D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = in.readLine().split(" ");
        int N = i(tmp[0]), M = i(tmp[1]), cap = i(tmp[2]);
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            String[] str = in.readLine().split(" ");
            int intA = i(str[0]), intB = i(str[1]), weight = i(str[2]);
            Node src = map.containsKey(intA) ? map.get(intA) : new Node(intA);
            Node dest = map.containsKey(intB) ? map.get(intB) : new Node(intB);
            src.distance = Integer.MAX_VALUE;
            dest.distance = Integer.MAX_VALUE;
            Edge e = new Edge(src, dest, weight);
            if (src.nbors.containsKey(intB)) {
                if (weight < src.nbors.get(intB).weight) {
                    src.nbors.put(intB, e);
                }
            } else {
                src.nbors.put(intB, e);
            }
            e = new Edge(dest, src, weight);
            if (dest.nbors.containsKey(intA)) {
                if (weight < dest.nbors.get(intA).weight) {
                    dest.nbors.put(intA, e);
                }
            } else {
                dest.nbors.put(intA, e);
            }
            map.put(intA, src);
            map.put(intB, dest);
        }

        int L = i(in.readLine());

//        System.out.println(map.get(10).nbors.keySet());
        int start = cap;

        PriorityQueue<Node> frontier = new PriorityQueue<>();
        if (map.get(start) == null) {
            System.out.println(-1);
        } else {
            frontier.add(map.get(start));
            map.get(start).distance = 0;

            int count = 0;
            while (!frontier.isEmpty()) {
                Node next = frontier.poll();
                if (next.distance > L) {
                    continue;
                }
                if (!next.visited) {
//                    System.out.println("id " + next.id);
                    next.visited = true;
//                for (Node node : map.values()) {
//                    System.out.println(node);
//                }
//                System.out.println(next.id);
//                System.out.println();
                    for (int nborId : next.nbors.keySet()) {
                        Edge e = next.nbors.get(nborId);

//                        Edge other = next.nbors.get(nborId).dest.nbors.get(next.id);
//                        System.out.println();
//                        System.out.println(e.src.id + " --> " + e.dest.nbors + "   " + next.id + "   " + e.src.id + " ->> " + e.dest.id);
//                        System.out.println(e.src.id + " ---> " + e.dest.id + "  " + next.distance + " " + e.weight);

//                        System.out.println("nbor id " + e.dest.id + "   " + e.weight);
                        /*int distanceInEdge = L - next.distance;
                        if (next.distance + e.weight > L && (distanceInEdge) != e.covered && (other.weight - distanceInEdge) != (other.covered)) {
                            System.out.println(next.id + "  " + e.src.id + " -> " + e.dest.id + "   " + other.src.id + " -> " +other.dest.id);
                            System.out.println(next.distance + " " + L + " " + (next.distance + e.weight));
                            e.covered = distanceInEdge;
                            other.covered = other.weight - distanceInEdge;
//                            System.out.println("id2 " + next.id + " -> " + e.dest.id + "  weight " + e.covered + " " + distanceInEdge);
                            count++;
                            continue;
                        } else */
                        if (next.distance + e.weight < e.dest.distance) {
                            e.dest.before = next;
                            e.dest.distance = next.distance + e.weight;
                        }
//                        if (!e.dest.visited) {
//                        }
                            frontier.add(e.dest);
                    }
                }
            }
            for (int key : map.keySet()) {
                Node node = map.get(key);
                if (node.distance == L) {
                    count++;
                } else {
                    if (node.distance < L) {
                        for (int nborKey : node.nbors.keySet()) {
                            Edge e = node.nbors.get(nborKey);
                            if (node.distance + e.weight > L) {
                                int srcDist = L - node.distance;
                                int destDist = L - e.dest.distance;

                                if (srcDist + destDist == e.weight) {
                                    if (e.src.id < e.dest.id) {
                                        count++;
                                    }
                                } else if (srcDist + destDist < e.weight) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void printPath(Node node) {
        while(node != null) {
            System.out.print(node.id + " ");
            node = node.before;
        }
        System.out.println();
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
        Node src;
        Node dest;
        int weight;
        int covered = -1;

        public Edge(Node src, Node dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;

        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
