import java.util.List;

public class Person {
    private String name;
    private AllTimes times;

    public Person(String name, AllTimes times) {
        this.name = name;
        this.times = times;
    }

    public boolean canMake(AllTimes check) {
        return check.contains(times);
    }

    public List<Integer> canMakeDays(AllTimes schedule) {
        return schedule.canMakeDays(times);
    }

    public String getName() {
        return name.split(" ")[0];
    }
}
