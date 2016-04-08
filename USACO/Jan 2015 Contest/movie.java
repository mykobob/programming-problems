import java.io.File;
import java.util.*;

/**
 * Created by Michael on 1/16/15.
 */
public class movie {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("movie.in"));
        int numMovies = in.nextInt();
        int targetLength = in.nextInt();
        in.nextLine();
        Movie[] movies = new Movie[numMovies];
        List<Data> zeros = new ArrayList<>();
        for (int i = 0; i < numMovies; i++) {
            movies[i] = new Movie(i, in.nextLine().split(" "));
            if(movies[i].startTimes[0] == 0) {
                zeros.add(new Data(movies[i], 0, 0, 0));
            }
        }

        Queue<List<Data>> q = new LinkedList<>();
        q.add(zeros);

        int min = 1 << 25;
        while (!q.isEmpty()) {
            List<Data> next = q.poll();
            System.out.println(next);
            for(Data d : next) {

                d.moviesSeen = d.moviesSeen | 1 << d.movie.id;
                List<Data> toAdd = new ArrayList<>();
                int nextTime = d.curTime + d.movie.length;
                System.out.println(nextTime);
                /*if(d.curTime >= targetLength) {
                    min = Math.min(min, d.distance);
                }
                if(d.distance > min) {
                    continue;
                }*/
                if(nextTime >= targetLength) {
                    min = Math.min(min, d.distance);
                }
                if(d.distance > min) {
                    continue;
                }
                for (int i = 0; i < movies.length; i++) {
                    if((d.moviesSeen >> (i) & 1) == 1)
                        continue;
                    if(movies[i].isRunning(nextTime)) {
                        // If movies[i] is still running i want to go to that movie
                        toAdd.addAll(movies[i].showingsRunning(nextTime, d.distance, d.moviesSeen));
//                        toAdd.add(new Data(movies[i], d.distance + 1, movies[i].startTime(nextTime)));

                    }
                }
                q.add(toAdd);
            }
        }
        System.out.println(min == 1 << 25 ? -1 : min);
    }

    static class Data {
        Movie movie;
        int distance;
        int curTime;
        int moviesSeen;
        public Data(Movie movie, int dist, int time, int seen) {
            this.movie = movie;
            distance = dist;
            curTime = time;
            moviesSeen = seen;
        }
        public String toString() {
            return String.format("CurTime: %d || Distance:%d || Movies seen: %s", curTime, distance, Integer.toBinaryString(moviesSeen));
        }
    }

    static class Movie {
        int id;
        int length;
        int[] startTimes;


        public Movie(int id, String[] arr) {
            this.id = id;
            length = i(arr[0]);
            int numShows = i(arr[1]);
            startTimes = new int[numShows];
            for (int i = 0; i < numShows; i++) {
                startTimes[i] = i(arr[i + 2]);
            }
        }

        public List<Data> showingsRunning(int t, int dist, int seen) {
            List<Data> data = new ArrayList<>();
            int spot = Arrays.binarySearch(startTimes, t);
            if(spot < 0)
                spot = ~spot;
            int lower = spot - 1, high = spot;
            while(lower >= 0 && lower < startTimes.length) {
                if(startTimes[lower] + length > t) {
                    data.add(new Data(this, dist + 1, startTimes[lower], seen));
                }
                else if(startTimes[lower] + lower < t) {
                    break;
                }
                lower--;
            }

            while(high < startTimes.length) {
                if(startTimes[high] <= t && startTimes[high] + length > t) {
                    data.add(new Data(this, dist + 1, startTimes[high], seen));
                }
                else if(startTimes[high] > t) {
                    break;
                }
                high++;
            }
            return data;
        }

        public boolean isRunning(int t) {
            int spot = Arrays.binarySearch(startTimes, t);
            if(spot < 0)
                spot = ~spot;
            if(spot >= startTimes.length) {
                return false;
            }
            return startTimes[spot] + length > t;
        }

        public int startTime(int t) {
            int spot = Arrays.binarySearch(startTimes, t);
            if(spot < 0)
                return startTimes[~spot];
            return startTimes[spot];
        }

        public String toString() {
            return String.format("%d %d", id, length);
        }
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }
}
