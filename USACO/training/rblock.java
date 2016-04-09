import java.util.*;
import java.io.*;
public class rblock {
    static int[][] adjMat;
    static int MAX = 10000000;
    static int numFields;
    static Map<Integer, Node> map;
    public static void main(String...bob) throws Exception
    {
        for(int ii = 1;ii<=10;ii++){
            long start = System.nanoTime();
            main(ii);
            Scanner in = new Scanner(new File("rblock.out"));
            int ans = in.nextInt();
            in = new Scanner(new File("rblock/"+ii+".out"));
            int ans2 = in.nextInt();
            System.out.println(ans + " " + ans2);
            System.out.println((System.nanoTime()-start)/1e9);
        }
    }
    public static void main(int num) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("rblock/"+num+".in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
        String[] data = in.readLine().split(" ");
        numFields = Integer.parseInt(data[0]);
        int numPassages = Integer.parseInt(data[1]);
        adjMat = new int[numFields+1][numFields+1];
        map = new HashMap<Integer, Node>();
        for(int ii = 0;ii<=numFields;ii++)
            Arrays.fill(adjMat[ii], MAX);
        for(int ii = 0;ii<numPassages;ii++){
            String[] data2 = in.readLine().split(" ");
            int src = Integer.parseInt(data2[0]);
            int dest = Integer.parseInt(data2[1]);
            int dist = Integer.parseInt(data2[2]);
            Node srcNode = map.get(src);
            if(srcNode == null)
                srcNode = new Node(src);
            Node destNode = map.get(dest);
            if(destNode == null)
                destNode = new Node(dest);
            srcNode.addNbor(destNode, dist);
            destNode.addNbor(srcNode, dist);
            map.put(src, srcNode);
            map.put(dest, destNode);

            adjMat[src][dest] = dist;
            adjMat[dest][src] = dist;
//            System.out.println(src + " " + dest);
        }
//        disp();
//        System.out.println();
//        for(int kk = 1;kk<=numFields;kk++){
//            for(int ii = 1;ii<=numFields;ii++){
//                for(int jj = 1;jj<=numFields;jj++){
//                    if(adjMat[ii][kk] + adjMat[kk][jj] < adjMat[ii][jj])
//                        adjMat[ii][jj] = adjMat[ii][kk] + adjMat[kk][jj];
//                }
//            }
//        }
        int best = shortestPath();
        Node dest = map.get(numFields);
        List<Node> onShortestPath = new ArrayList<Node>();
        List<Edge> shortestEdges = new ArrayList<Edge>();
        while(dest != null){
            onShortestPath.add(dest);
            dest = dest.parent;
        }
        Collections.reverse(onShortestPath);
//        System.out.println(onShortestPath);
        for(int jj = 0;jj<onShortestPath.size()-1;jj++){
            Node a = onShortestPath.get(jj);
            for(Edge e : a.nbors)
                if(e.dest.ID == onShortestPath.get(jj+1).ID){
                    shortestEdges.add(e);
                    break;
                }
        }
        int bestDifference = Integer.MIN_VALUE;
//        System.out.println(shortestEdges);
        for(Edge e : shortestEdges){
            reset();
            e.weight *= 2;
            int dist = shortestPath();
            bestDifference = Math.max(dist - best, bestDifference);
            e.weight /= 2;
        }
     /*   for(int ii : map.keySet()){
            for(Edge e : map.get(ii).nbors)
            {
                reset();
//                System.out.println(map.get(ii));
//                System.out.println(map.values());
//                if(ii == 3 && e.dest.ID == 4)
//                    System.out.println("HIHIHI " + e.weight);
                e.weight *= 2;
//                if(ii == 3 && e.dest.ID == 4)
//                    System.out.println(e.weight);
                int dist = shortestPath();
//                System.out.println(dist);
                bestDifference = Math.max(bestDifference, dist-best);
                e.weight /= 2;
            }

        }*/
//        System.out.println(bestDifference);
        out.println(bestDifference);
        out.close();
//        System.exit(0);

//        System.out.println(shortestPath());
//        for(int ii : map.keySet())
//            System.out.println(map.get(ii));
//        disp();
//        System.out.println(adjMat[1][numFields]);

    }
    public static void reset(){
        for(int ii = 0;ii<=numFields;ii++)
        {
            Node a = map.get(ii);
            if(a == null)
                continue;
            a.distance = MAX;
        }
    }
    public static int shortestPath(){
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        Set<Node> visited = new HashSet<Node>();
        map.get(1).distance = 0;
        q.add(map.get(1));
        while(!q.isEmpty()){
            Node other = q.poll();
            if(other.ID ==numFields)
                return other.distance;

            if(visited.add(other)){
//                System.out.println(other);
                for(Edge e : other.nbors){
                    if(e.dest.distance > other.distance + e.weight){
                        e.dest.distance = other.distance + e.weight;
                        e.dest.parent = other;
                    }
                    q.add(e.dest);
                }
            }
        }
        return -1;
    }

    public static void disp(){
        for(int[] ii : adjMat)
        {
            for(int jj : ii)
                System.out.printf("%2d ", jj==MAX?0:jj);
            System.out.println();
        }
    }
}
class Node implements Comparable<Node>{
    List<Edge> nbors;
    Node parent;
    int distance;
    int ID;
    List<Integer> pathTaken;
    public Node(int ID){
        this.ID = ID;
        distance = 10000000;
        nbors = new ArrayList<Edge>();
        pathTaken = new ArrayList<Integer>();
        parent = null;
    }
    public boolean equals(Object other){
        return hashCode() == other.hashCode();
    }
    public int hashCode(){
        return ID * 6236;
    }
    public void addNbor(Node o, int w){
        nbors.add(new Edge(o, w));
    }
    public int compareTo(Node other){
        return distance-other.distance;
    }
    public String toString(){
        return String.format("ID: %d  dist: %d", ID, distance);
    }
}
class Edge{
    int weight;
    Node dest;
    public Edge(Node d, int w){
        dest = d;
        weight = w;
    }
    public String toString(){
        return dest + " |||| weight " + weight;
    }
}