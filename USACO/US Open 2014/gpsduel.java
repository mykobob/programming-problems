import java.util.*;
import java.io.*;

/*
ID: michael138
LANG: JAVA
TASK: gpsduel
*/
public class gpsduel {
    static BufferedReader in;
    static PrintWriter out;
    static Node[] nodes;
    static int numNodes, numEdges;

    public static void main(String... bob) throws Exception {
        in = new BufferedReader(new FileReader("gpsduel.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
        // Use StringTokenizer
        StringTokenizer st = new StringTokenizer(in.readLine());

        numNodes = Integer.parseInt(st.nextToken());
        numEdges = Integer.parseInt(st.nextToken());
        nodes = new Node[numNodes+1];
        for(int ii = 0;ii<numEdges;ii++){
            st = new StringTokenizer(in.readLine());
            int src = Integer.parseInt(st.nextToken());
            if(nodes[src] == null)
                nodes[src] = new Node(src);

            int dest = Integer.parseInt(st.nextToken());
            if(nodes[dest] == null)
                nodes[dest] = new Node(dest);

            int aDist = Integer.parseInt(st.nextToken());
            nodes[src].addEdge(true, nodes[dest], aDist);

            int bDist = Integer.parseInt(st.nextToken());
            nodes[src].addEdge(false, nodes[dest], bDist);
        }
       /* for(Node n : nodes){
            if(n!=null){
                System.out.println(n.num);
                System.out.println(n.nborsA);
                System.out.println(n.nborsB);
            }
        }*/






        nodes[1].distSoFarA = nodes[1].distSoFarB = nodes[1].dist = 0;



//        all.add(nodes[1]);
        int complaints = 0;

        Node current = nodes[1];
        dijkstra(true, 1);
//        reset();
        dijkstra(false, 1);
//        System.out.println(nodes[numNodes].distSoFarA + " " + nodes[numNodes].distSoFarB);
//        while(current.num != numNodes){
//
//        }
        while(current.num != numNodes){
            Node bestNext = null;
            for(int ii = 0;ii<current.nborsB.size();ii++){
                Edge eA = current.nborsA.get(ii);
                Edge eB = current.nborsB.get(ii);

                if(eA.cost < eB.cost){
                    bestNext = eA.dest;
                }
                else
                    bestNext = eB.dest;
            }
//            System.out.println("current " + current);

            if(bestNext.parentA.num != current.num) {
//                System.out.println("A: " + bestNext.parentA);
                reset(true);
                dijkstra(true, bestNext.num);
                complaints++;
            }

            if(bestNext.parentB.num != current.num) {
//                System.out.println("B: " + bestNext.parentB);
                reset(false);
                dijkstra(false, bestNext.num);
                complaints++;
            }

            current = bestNext;
//            System.out.println("complaints " + complaints);

        }
//        System.out.println(complaints);
        out.println(complaints);

//        while(!all.isEmpty()){
//            Node next = all.poll();
//
//            if(next.num == numNodes)
//                break;
//            Node aNext = a.poll();
//            Node bNext = b.poll();
//            System.out.printf("%s %s %s%n", next, aNext, bNext);
//            if(!next.equals(aNext))
//                complaints++;
//            if(!next.equals(bNext))
//                complaints++;
//
//            if(visited.add(next)){
//                for(int ii = 0;ii<next.nborsA.size();ii++){
//                    Edge aaEdge = next.nborsA.get(ii);
//                    Node aa = next.nborsA.get(ii).dest;
//                    aa.distSoFarA = Math.min(aa.distSoFarA, next.distSoFarA + aaEdge.cost);
//                    a.add(aa);
//
//                    Edge bbEdge = next.nborsB.get(ii);
//                    Node bb = next.nborsB.get(ii).dest;
//                    bb.distSoFarB = Math.min(bb.distSoFarB, next.distSoFarB + bbEdge.cost);
//                    b.add(bb);
//
//                    Node allEdge = next.nborsA.get(ii).dest;
//                    allEdge.dist = Math.min(allEdge.distSoFarA, allEdge.distSoFarB);
//                    all.add(allEdge);
//                }
//            }
//        }
//        System.out.println(complaints);

        out.close();
        System.exit(0);
    }
    static void reset(boolean a){
        for(Node n : nodes)
            if(n != null) {
                n.dist = Integer.MAX_VALUE;
                if(a) {
                    n.distSoFarA = Integer.MAX_VALUE;
                    n.parentA = null;
                }
                else {
                    n.distSoFarB = Integer.MAX_VALUE;
                    n.parentB = null;
                }
            }
    }
    static void dijkstra(boolean a, int start){

        Set<Node> visited = new HashSet<Node>();
        if(a){
            Comparator<Node> c = new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.distSoFarA-o2.distSoFarA;
                }
            };
            PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, c);
            nodes[start].distSoFarA = 0;
            frontier.add(nodes[start]);
            while(!frontier.isEmpty()){
                Node next = frontier.poll();
                if(next.num == numNodes)
                    break;
                if(visited.add(next)){
                    for(Edge e : next.nborsA){
                        if(e.dest.distSoFarA > next.distSoFarA + e.cost){
                            e.dest.distSoFarA = next.distSoFarA + e.cost;
                            e.dest.parentA = next;
                        }
                        frontier.add(e.dest);
                    }
                }
            }
        }
        else{
            Comparator<Node> c = new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.distSoFarB-o2.distSoFarB;
                }
            };
            PriorityQueue<Node> frontier = new PriorityQueue<Node>(1, c);
            frontier.add(nodes[start]);
            nodes[start].distSoFarB = 0;
            while(!frontier.isEmpty()){
                Node next = frontier.poll();
//                System.out.println(next.num);
                if(next.num == numNodes) {
                    break;
                }
//                System.out.println(next.num);
                if(visited.add(next)){
                    for(Edge e : next.nborsB){
                        if(e.dest.distSoFarB > next.distSoFarB + e.cost){
                            e.dest.distSoFarB = next.distSoFarB + e.cost;
                            e.dest.parentB = next;
                        }
                        frontier.add(e.dest);
                    }
                }
            }
        }
    }

    static class Node{
        int num;
        List<Edge> nborsA;
        List<Edge> nborsB;
        int distSoFarA;
        int distSoFarB;
        int dist;
        Node parentA, parentB;
        public Node(int n){
            num = n;
            nborsA = new ArrayList<Edge>();
            nborsB = new ArrayList<Edge>();
            distSoFarA = distSoFarB = dist = Integer.MAX_VALUE-100001;
        }
        public void addEdge(boolean a, Node end, int dist){
            if(a)
                nborsA.add(new Edge(this, end, dist));
            else
                nborsB.add(new Edge(this, end, dist));
        }

        public int hashCode(){
            return num;
        }

        @Override
        public boolean equals(Object other){
            return hashCode() == other.hashCode();
        }
        public String toString(){ return String.format("%d%n", num);}
    }
    static class Edge{
        int cost;
        Node src, dest;
        public Edge(Node s, Node de, int di) {
            src = s;
            dest = de;

            cost = di;
        }
        public String toString(){
            return String.format("%d -> %d:%d", src.num, dest.num, cost);
        }
    }

}
