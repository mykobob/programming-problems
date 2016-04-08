import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution545C {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Solution545C"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int numTrees = i(testCases.nextToken());
        Tree[] trees = new Tree[numTrees];
        for (int i = 0; i < numTrees; i++) {
            StringTokenizer data = new StringTokenizer(in.readLine());
            trees[i] = new Tree(i(data.nextToken()), i(data.nextToken()));
        }
        int treeFall = trees[0].pos;
        int ans = 1;
        for (int i = 1; i < numTrees; i++) {
            if (trees[i].pos - trees[i].height > treeFall) {
//                System.out.println(treeFall);
                treeFall = trees[i].pos;
                ans++;
            } else if (i != numTrees - 1 && trees[i].pos + trees[i].height < trees[i + 1].pos){
                treeFall = trees[i].pos + trees[i].height;
                ans++;
            } else if (i == numTrees - 1) {
                ans++;
            } else {
                treeFall = trees[i].pos;
            }
        }

        System.out.println(ans);
    }

    static class Tree {
        int pos, height;

        public Tree(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
