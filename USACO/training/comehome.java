import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: comehome
*/
public class comehome {
    static BufferedReader in;
    static PrintWriter out;
    static int MAX_DIST = 10000000;
    static Map<Character, Node> map;

    public static void main(String... bob) throws Exception {
        long start = System.nanoTime();
        in = new BufferedReader(new FileReader("comehome.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        // Use StringTokenizer
        //StringTokenizer st = new StringTokenizer(in.readLine());
        int numPaths = Integer.parseInt(in.readLine());
        map = new HashMap<Character, Node>();
//        List<Character> startingCharacters = new ArrayList<Character>();
        for(int ii = 0;ii<numPaths;ii++){
            String[] data = in.readLine().split(" ");
            char src = data[0].charAt(0);
            char dest = data[1].charAt(0);
            int cost = Integer.parseInt(data[2]);
            Node srcNode = map.get(src);
            if(srcNode == null)
                srcNode = new Node(src);
            Node destNode = map.get(dest);
            if(destNode == null)
                destNode = new Node(dest);
            srcNode.nbors.add(new Edge(destNode, cost));
            destNode.nbors.add(new Edge(srcNode, cost));
            map.put(src, srcNode);
            map.put(dest, destNode);
//            if(Character.isUpperCase(src))
//                startingCharacters.add(src);
        }
//        System.out.println((System.nanoTime()-start)/1e9);
//        start = System.nanoTime();
        int best = Integer.MAX_VALUE;
        char bestChar = ' ';
//        List<Character> done = new ArrayList<Character>();
//        System.out.println(map.size());

        PriorityQueue<Node> frontier = new PriorityQueue<Node>();
        Set<Node> visisted = new HashSet<Node>();
        map.get('Z').distance = 0;
        frontier.add(map.get('Z'));
        while(!frontier.isEmpty()){
            Node next = frontier.poll();
            if(visisted.add(next)){
                for(Edge e : next.nbors){
                    if(e.dest.distance > next.distance + e.cost){
                        e.dest.distance = next.distance + e.cost;
                    }
                    frontier.add(e.dest);
                }
            }
        }
        for(Node node : map.values())
        {
            if(node.distance < best && node.ID != 'Z' && Character.isUpperCase(node.ID)){
                best = node.distance;
                bestChar = node.ID;
            }
        }
        System.out.printf("%c %d%n", bestChar, best);
        out.printf("%c %d%n", bestChar, best);
//        System.out.println((System.nanoTime()-start)/1e9);
        System.out.printf("IT RAN FOR %.2f SECONDS!!!%n", (System.nanoTime()-start)/1e9);
        out.close();
        System.exit(0);
    }

    static class Node implements Comparable<Node>{
        char ID;
        List<Edge> nbors;
        int distance;
        public Node(char ch){
            ID = ch;
            nbors = new ArrayList<Edge>();
            distance = MAX_DIST;
        }
        public int compareTo(Node other){
            return distance-other.distance;
        }
        public int hashCode(){
            return ID * 235825;
        }
        public boolean equals(Object other){
            return hashCode() == other.hashCode();
        }

    }
    static class Edge{
        Node dest;
        int cost;
        public Edge(Node d, int c){
            dest = d;
            cost = c;
        }
    }
}
