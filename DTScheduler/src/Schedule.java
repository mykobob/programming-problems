import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Schedule {

    private static final int NUM_DAYS = 5;
    private static final int[] HOURS = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[] MINUTES = {0, 30};

    private int eventDuration; // in minutes
    private List<AllTimes> allTimes;

    public Schedule(int eventDuration) {
        this.eventDuration = eventDuration;
        this.allTimes = new ArrayList<>();
        generateAllTimes();
    }

    /**
     * Determines a time every day of the week so that everyone who has provided availability will be included
     * @return AllTimes object representing a time every day of the week.
     */
    public AllTimes findOptimalTime(List<Person> people) {
        List<AllTimes> validTimes = findAllTimes(people);

        return validTimes.get(0);
    }

    public List<AllTimes> findAllTimes(List<Person> people) {
        return allTimes.stream()
                .filter(times1 -> times1.valid(people))
                .filter(AllTimes::sameTTH)
                .sorted()
                .collect(Collectors.toList());
    }

    private void generateAllTimes() {
        helper(0, new AvailableTime[5]);
    }

    private void helper(int dayOfWeek, AvailableTime[] times) {
        if (dayOfWeek == NUM_DAYS) {
            allTimes.add(new AllTimes(Arrays.copyOf(times, times.length)));
            return;
        }
        for (int HOUR : HOURS) {
            for (int MINUTE : MINUTES) {

                int endHour = HOUR;
                int endMinute = MINUTE + eventDuration;
                if (endMinute >= 60) {
                    endMinute -= 60;
                    endHour++;
                }
                times[dayOfWeek] = new AvailableTime(HOUR, MINUTE, endHour, endMinute);
                helper(dayOfWeek + 1, times);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("schedules.in"));
        List<Person> people = new ArrayList<>();
        while (in.hasNextLine()) {
            String name = in.nextLine();
            AvailableTime[] times = new AvailableTime[NUM_DAYS];
            for (int i = 0; i < NUM_DAYS; i++) {
                times[i] = AvailableTime.parse(in.nextLine());
            }
            people.add(new Person(name, new AllTimes(times)));
        }

        Schedule dt = new Schedule(60);
        System.out.println("finished recursion");
        List<AllTimes> times = dt.findAllTimes(people);
        for (AllTimes time : times) {
            System.out.println(time.who(people));
            System.out.println();
        }
    }
}
