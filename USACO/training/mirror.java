/*
ID: ethankwan
NAME: Ethan Kwan
LANG: JAVA
*/

import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class mirror
{
    public static void main(String[]args) throws IOException
    {
        File file2 = new File("mirror.out");
        PrintWriter writer = new PrintWriter(file2);
        File file = new File("mirror.in");
        Scanner s = new Scanner(file);
        int sizew = s.nextInt();
        int sizel = s.nextInt();
        s.nextLine();
        char[][] field = new char[sizew][sizel];
        String theString = new String("");
        int i = 0;
        while(s.hasNext())
        {
            theString = s.nextLine();
            char[] charArray = theString.toCharArray();
            int p = 0;
            for(char u: charArray)
            {
                field[i][p] = u;
                p++;
            }
            i++;
        }
        int highscore = 0;
        int counter;
        int q;
        int w;
        String dir;
        char[][]changed;

        for(int x = 0; x < 2;x++)
        {
            for(int y = 0; y < field[x].length;y++)
            {
                changed = field;
                //STARTING FIRST LAST ROWS LASERS
                dir = "d";
                counter = 0;
                q = x;
                w = y;

                if(x == 1)
                {
                    dir = "u";
                    counter = 0;
                    q = changed.length-1;
                    w = y;

                }
                do
                {
                    if(changed[q][w] == '/' && dir == "d")
                    {
                        changed[q][w] = 'k';
                        dir = "l";
                        counter++;
                        w = w-1;
                    }
                    else if(changed[q][w] == '/' && dir == "u")
                    {
                        changed[q][w] = 'k';
                        dir = "r";
                        counter++;
                        w = w +1;
                    }
                    else if(changed[q][w] == '/' && dir == "r")
                    {
                        changed[q][w] = 'k';
                        dir = "u";
                        counter++;
                        q = q -1;

                    }
                    else if(changed[q][w] == '/' && dir == "l")
                    {
                        changed[q][w] = 'k';
                        dir = "d";
                        counter++;
                        q = q+1;
                    }
                    else if(changed[q][w] == '\\' && dir == "d")
                    {
                        changed[q][w] = 'k';
                        dir = "r";
                        counter++;
                        w = w+1;
                    }
                    else if(changed[q][w] == '\\' && dir == "u")
                    {
                        changed[q][w] = 'k';
                        dir = "l";
                        counter++;
                        w = w-1;
                    }
                    else if(changed[q][w] == '\\' && dir == "r")
                    {
                        changed[q][w] = 'k';
                        dir = "d";
                        counter++;
                        q = q-1;
                    }
                    else if(changed[q][w] == '\\' && dir == "l")
                    {
                        changed[q][w] = 'k';
                        dir = "u";
                        counter++;
                        q = q-1;

                    }
                    else if(changed[q][w] == 'k')
                    {
							/*highscore = -1*/;
                    }
                }
                while(q >=0 && q<changed.length && w>=0 && w <changed[q].length /*&& changed[q][w]!='k'*/);
                if(counter>highscore && highscore != -1)
                {
                    highscore = counter;
                }

            }
        }
        //GOING SIDWAYS
        for(int y = 0; y < 2;y++)
        {
            for(int x = 0; x < field.length;x++)
            {
                changed = field;	//STARTING FIRST LAST ROWS LASERS
                dir = "r";
                counter = 0;
                q = x;
                w = 0;

                if(y == 1)
                {
                    dir = "l";
                    counter = 0;
                    q = x;
                    w = field[x].length-1;

                }
                do
                {
                    if(changed[q][w] == '/' && dir == "d")
                    {
                        changed[q][w] = 'k';
                        dir = "l";
                        counter++;
                        w = w-1;
                    }
                    else if(changed[q][w] == '/' && dir == "u")
                    {
                        changed[q][w] = 'k';
                        dir = "r";
                        counter++;
                        w = w +1;
                    }
                    else if(changed[q][w] == '/' && dir == "r")
                    {
                        changed[q][w] = 'k';
                        dir = "u";
                        counter++;
                        q = q-1;
                    }
                    else if(changed[q][w] == '/' && dir == "l")
                    {
                        changed[q][w] = 'k';
                        dir = "d";
                        counter++;
                        q = q+1;
                    }
                    else if(changed[q][w] == '\\' && dir == "d")
                    {
                        changed[q][w] = 'k';
                        dir = "r";
                        counter++;
                        w = w+1;
                    }
                    else if(changed[q][w] == '\\' && dir == "u")
                    {
                        changed[q][w] = 'k';
                        dir = "l";
                        counter++;
                        w = w-1;
                    }
                    else if(changed[q][w] == '\\' && dir == "r")
                    {
                        changed[q][w] = 'k';
                        dir = "d";
                        counter++;
                        q = q+1;
                    }
                    else if(changed[q][w] == '\\' && dir == "l")
                    {
                        changed[q][w] = 'k';
                        dir = "u";
                        counter++;
                        q = q-1;
                    }
                    else if(changed[q][w] == 'k')
                    {
							/*highscore = -1*/;
                    }

                }
                while(q >=0 && q<changed.length && w>=0 && w <changed[q].length && changed[q][w]!='k');
                if(counter>highscore && highscore != -1)
                {
                    highscore = counter;
                }

            }
        }
        writer.print(highscore);
        writer.close();














    }
}