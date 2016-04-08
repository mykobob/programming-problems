import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Michael on 1/23/15.
 */
public class Split {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new File("input2.txt"));
        PrintWriter out = new PrintWriter("split.out");
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int ppl = in.nextInt();
            double totalCash = in.nextDouble();
            Person[] people = new Person[ppl];

            Set<String> tasks = new HashSet<String>();
            Map<String, Integer> instances = new HashMap<String, Integer>();
            for (int j = 0; j < ppl; j++) {
                String name = in.next();
                people[j] = new Person(name);
                int crimes = in.nextInt();
                in.nextLine();
                for (int k = 0; k < crimes; k++) {
                    String str = in.nextLine();
                    people[j].tasks.add(str);
                    tasks.add(str);
                    if (instances.containsKey(str)) {
                        instances.put(str, instances.get(str) + 1);
                    } else {
                        instances.put(str, 1);
                    }
                }
            }
            double perTask = totalCash / tasks.size();
            for (String str : tasks) {
                for (Person p : people) {
                    if (p.contains(str)) {
                        p.money += perTask / instances.get(str);
                    }
                }
            }
            System.out.printf("Case #%d:\n", i + 1);
            out.printf("Case #%d:\n", i + 1);
            for (Person p : people) {
                System.out.println(p);
                out.println(p);
            }
        }
        out.close();
        System.exit(0);
    }

    static class Person {
        Set<String> tasks;
        double money;
        String name;

        public Person(String tmp) {
            name = tmp;
            money = 0;
            tasks = new HashSet<String>();
        }

        public boolean contains(String task) {
            return tasks.contains(task);
        }

        public String toString() {
            return String.format("%s: %.2f", name, money);
        }

    }
}
