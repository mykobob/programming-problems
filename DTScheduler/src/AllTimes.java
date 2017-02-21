import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class AllTimes implements Comparable<AllTimes> {
    private static String[] DAYS = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" };
    private AvailableTime[] dailyTimes;
    private int totalPpl = 0;

    public AllTimes(AvailableTime[] times) {
        // Representing M-F
        dailyTimes = times;
    }

    // This checks to see if that all people can make {this}'s scheduled times.
    public boolean valid(List<Person> people) {
        int[] days = new int[5];
        for (Person person : people) {
            List<Integer> canMakeDays = person.canMakeDays(this);
            canMakeDays.forEach(day -> days[day]++);
            totalPpl += canMakeDays.size();
        }
        return Arrays.stream(days).allMatch(peoplePerDay -> peoplePerDay >= 2);
    }

    public String who(List<Person> people) {
        StringBuilder[] out = new StringBuilder[DAYS.length];
        for (int i = 0; i < DAYS.length; i++) {
            out[i] = new StringBuilder();
            out[i].append(DAYS[i]);
            out[i].append(" ");
            out[i].append(dailyTimes[i]);
            out[i].append(": ");
        }
        for (Person person : people) {
            List<Integer> canMakeDays = person.canMakeDays(this);
            for (Integer canMakeDay : canMakeDays) {
                out[canMakeDay].append(person.getName());
                out[canMakeDay].append(" ");
            }
        }
        return Arrays.stream(out).collect(Collectors.joining("\n"));
    }

    // If this set of times intersects with another set of times for every day of the week
    public boolean contains(AllTimes other) {
        for (int i = 0; i < dailyTimes.length; i++) {
            AvailableTime thisTime = dailyTimes[i];
            AvailableTime otherTime = other.dailyTimes[i];
            if (thisTime.overlaps(otherTime)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> canMakeDays(AllTimes other) {
        List<Integer> canMakeDays = new ArrayList<>();
        for (int i = 0; i < dailyTimes.length; i++) {
            AvailableTime thisTime = dailyTimes[i];
            AvailableTime otherTime = other.dailyTimes[i];
            if (thisTime.overlaps(otherTime)) {
                 canMakeDays.add(i);
            }
        }
        return canMakeDays;
    }

    public boolean sameTTH() {
        return dailyTimes[1].equals(dailyTimes[3]);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < DAYS.length; i++) {
            out.append(DAYS[i]);
            out.append(": ");
            out.append(dailyTimes[i]);
            out.append("\n");
        }
        return out.toString();
    }

    @Override
    public int compareTo(AllTimes other) {
        return other.totalPpl - totalPpl;
    }
}
