import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Difficulty {
    public static void main(String... bob) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("Difficulty"));
        StringTokenizer testCases = new StringTokenizer(in.readLine());
        int T = i(testCases.nextToken());
        for (int i = 0; i < T; i++) {
            int numClasses = i(in.readLine());
            Map<String, Course> courses = new HashMap<String, Course>();
            for (int j = 0; j < numClasses; j++) {
                StringTokenizer courseData = new StringTokenizer(in.readLine());
                String name = courseData.nextToken();
                long time = l(courseData.nextToken());
                int numPrereqs = i(courseData.nextToken());
                Course course = new Course(name, time);
                if (numPrereqs != 0) {
                    StringTokenizer prereqs = new StringTokenizer(in.readLine());
                    for (int k = 0; k < numPrereqs; k++) {
                        String prereq = prereqs.nextToken();
//                        System.out.println("prereq = " + prereq);
                        course.prereq.put(prereq, null);
                    }
                }
                courses.put(name, course);
            }
            for (String str : courses.keySet()) {
                Course course = courses.get(str);
                Map<String, Course> betterMap = new HashMap<String, Course>();
                for (String prereq : course.prereq.keySet()) {
                    betterMap.put(prereq, courses.get(prereq));
                }
                course.prereq = betterMap;
            }
            for (Map.Entry<String, Course> stringCourseEntry : courses.entrySet()) {
                Course course = stringCourseEntry.getValue();
                if (course.difficulty == -1) {
                    course.difficulty = difficult(course);
                }
            }
            Course mostDifficult = null;
            for (Course course : courses.values()) {
                if (mostDifficult != null) {
                    if (mostDifficult.difficulty < course.difficulty) {
                        mostDifficult = course;
                    }
                } else {
                    mostDifficult = course;
                }
            }
//            System.out.println(courses);
            System.out.println(mostDifficult);
        }
    }

    public static long difficult(Course course) {
        long difficulty = course.time;
        for (String s : course.prereq.keySet()) {
            Course prereq = course.prereq.get(s);
            if (prereq.difficulty != -1) {
                difficulty += prereq.difficulty;
            } else {
                difficulty += difficult(prereq);
            }
        }
        course.difficulty = difficulty;
        return difficulty;
    }

    public static int i(String str) {
        return Integer.parseInt(str);
    }

    public static long l(String str) {
        return Long.parseLong(str);
    }

    static class Course {
        String name;
        long time;
        Map<String, Course> prereq;
        long difficulty;

        public Course(String name, long time) {
            this.name = name;
            this.time = time;
            difficulty = -1;
            this.prereq = new HashMap<String, Course>();
        }

        public String toString() {
            return name + " " + difficulty;
        }
    }
}
