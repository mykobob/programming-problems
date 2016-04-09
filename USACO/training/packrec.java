/*
ID: michael63
LANG: JAVA
TASK: packrec
*/
import sun.plugin.dom.css.Rect;

import java.util.*;
import java.io.*;

public class packrec
{
    public static void main(String...bob) throws Exception
    {
        String fileName = "packrec";
        Scanner in = new Scanner(new File(fileName+".in"));
        int minArea = Integer.MAX_VALUE;
        ArrayList<Rectangle> all = new ArrayList<Rectangle>();
        for(int ii = 0;ii<4;ii++)
        {
            int length = in.nextInt();
            int width = in.nextInt();
            all.add(new Rectangle(length,width));
        }
        minArea = Math.min(firstLayout(all), minArea);
        System.out.println(minArea);
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
    }
    public static int firstLayout(ArrayList<Rectangle> arr)
    {
        int minWidth = Integer.MAX_VALUE;
        int minHeight = Integer.MAX_VALUE;
        for(int ii = 0;ii<arr.size();ii++){
            Rectangle rect = arr.get(ii);
            for(int jj = 0;jj<2;jj++){
                for(int kk = 0;kk<jj;kk++)
                    rect.rotate();
                for(int kk = 0;kk<arr.size();kk++)
                {

                }
            }
        }
        return 1;
    }

    static class Rectangle
    {
        int length;
        int width;
        int area;
        public Rectangle(int l, int w)
        {
            length= l;
            width = w;
            area = l*w;
        }
        public void rotate()
        {
            int tmp = length;
            length = width;
            width = length;
        }

    }

}