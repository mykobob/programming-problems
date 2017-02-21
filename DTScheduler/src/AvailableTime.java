import java.io.*;
import java.util.*;

/**
 * This class represents the available times during one day
 */
public class AvailableTime {

    private List<Range> times;

    public AvailableTime() {
        times = new ArrayList<>();
    }

    public AvailableTime(Range time) {
        this();
        times.add(time);
    }

    public AvailableTime(int hour, int minute, int endHour, int endMinute) {
        this(new Range(hour, minute, endHour, endMinute));
    }



    // This checks to see if thisTimes intersects with the times in other
    public boolean overlaps (AvailableTime other) {
        for (Range thisTimes : times) {
            for (Range otherTimes : other.times) {
                if (thisTimes.contains(otherTimes)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String UNAVAILABLE = "can't make it";

    public static AvailableTime parse(String str) {
        if (UNAVAILABLE.equals(str)) {
            return new AvailableTime();
        }
        // Input is available times for one day
        // {hh}{a|p}:{mm}-{hh}{a|p}:{mm},...
        AvailableTime availableTime = new AvailableTime();
        String[] times = str.split(",");

        for (String time : times) {
            String[] parts = time.split("-");

            String[] startData = parts[0].split(":");
            int militaryConversion = startData[1].endsWith("p") ? 12 : 0;
            int startHour = Integer.parseInt(startData[0]);
            startHour += startHour == 12 ? 0 : militaryConversion;
            int startMinute = Integer.parseInt(startData[1].substring(0, startData[1].length() - 1));

            String[] endData = parts[1].split(":");
            militaryConversion = endData[1].endsWith("p") ? 12 : 0;
            int endHour = Integer.parseInt(endData[0]);
            endHour += endHour == 12 ? 0 : militaryConversion;
            int endMinute = Integer.parseInt(endData[1].substring(0, endData[1].length() - 1));

            availableTime.addNewTime(new Range(startHour, startMinute, endHour, endMinute));
        }
        return availableTime;
    }

    private Range firstOfTheDay() {
        return times.get(0);
    }

    private Range lastOfTheDay() {
        return times.get(times.size() - 1);
    }

    public boolean canMerge(AvailableTime other) {
        return lastOfTheDay().rightBefore(other.firstOfTheDay());
    }

    /*
     * Precondition: Each AvailableTime object must only have one time slot in them
     */
    public AvailableTime merge(AvailableTime other) {
        return new AvailableTime(lastOfTheDay().merge(other.firstOfTheDay()));
    }

    public void combine(AvailableTime other) {
        this.times.addAll(other.times);
    }

    private void addNewTime(Range range) {
        times.add(range);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < times.size(); i++) {
            out.append(times.get(i));
            if (i != times.size() - 1) {
                out.append(",");
            }
        }
        return out.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AvailableTime) {
            return times.equals(((AvailableTime)other).times);
        }
        return false;
    }

    static class Range {
        private int startHour;
        private int startMinute;
        private int endHour;
        private int endMinute;

        public Range(int startHour, int startMinute, int endHour, int endMinute) {
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
        }

        public int convertStartTime() {
            return startHour * 60 + startMinute;
        }

        public int convertEndTime() {
            return endHour * 60 + endMinute;
        }

        public boolean rightBefore(Range other) {
            return endHour == other.startHour && endMinute == other.startMinute;
        }

        public Range merge(Range other) {
            return new Range(startHour, startMinute, other.endHour, other.endMinute);
        }

        public boolean contains(Range other) {
            int thisStart = convertStartTime(), thisEnd = convertEndTime();
            int otherStart = other.convertStartTime();

            return thisStart <= otherStart && otherStart <= thisEnd && otherStart + 60 <= thisEnd;
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d-%02d:%02d", startHour, startMinute, endHour, endMinute);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return startHour == range.startHour &&
                    startMinute == range.startMinute &&
                    endHour == range.endHour &&
                    endMinute == range.endMinute;
        }
    }
}
