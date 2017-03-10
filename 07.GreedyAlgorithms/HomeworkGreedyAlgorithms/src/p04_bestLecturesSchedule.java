import java.util.*;

public class p04_bestLecturesSchedule {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Set<Lecture> lectures = new TreeSet<>();

        int n = Integer.parseInt(in.nextLine().split(" ")[1]);
        for (int i = 0; i < n; i++) {
            String[] lectureArgs = in.nextLine().split(": ");
            String name = lectureArgs[0];
            String[] timeArgs = lectureArgs[1].split(" – ");
            int startTime = Integer.parseInt(timeArgs[0]);
            int endTime = Integer.parseInt(timeArgs[1]);

            Lecture lecture = new Lecture(name, startTime, endTime);
            lectures.add(lecture);
        }

        Deque<Lecture> takenLectures = new LinkedList<>();
        final int[] time = {lectures.iterator().next().endTime};
        takenLectures.add(lectures.iterator().next());

        lectures.stream()
                .skip(1)
                .forEach(l -> {
                    if (l.startTime >= time[0]) {
                        takenLectures.add(l);
                        time[0] = l.endTime;
                    }
                });
        for (Lecture lecture : lectures) {
            if (lecture.startTime >= time[0]) {
                takenLectures.add(lecture);
                time[0] = lecture.endTime;
            }
        }

        for (Lecture takenLecture : takenLectures) {
            System.out.println(takenLecture);
        }
    }

    private static class Lecture implements Comparable<Lecture> {
        private String name;
        private int startTime;
        private int endTime;

        public Lecture(String name, int startTime, int endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture other) {
            return Integer.compare(this.endTime, other.endTime);
        }

        @Override
        public String toString() {
            return String.format("%d - %d -> %s", this.startTime, this.endTime, this.name);
        }
    }
}
