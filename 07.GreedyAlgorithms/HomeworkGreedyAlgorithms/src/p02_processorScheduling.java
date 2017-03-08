import java.util.Scanner;
import java.util.TreeSet;

public class p02_processorScheduling {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        TreeSet<Task> tasksSet = new TreeSet<>();
        int tasks = Integer.parseInt(in.nextLine().split("\\s+")[1]);
        for (int i = 0; i < tasks; i++) {
            String[] tasksArgs = in.nextLine().split(" – ");
            int value = Integer.parseInt(tasksArgs[0]);
            int deadline = Integer.parseInt(tasksArgs[1]);

            Task task = new Task(value, deadline);
            tasksSet.add(task);
        }

        int line = 1;
        while (!tasksSet.isEmpty()) {
            Task task = tasksSet.pollLast();
            if (task.deadline >= line) {
                System.out.println(task);
                line++;
            }
        }
    }

    private static class Task implements Comparable<Task> {
        private int value;
        private int deadline;

        public Task(int value, int deadline) {
            this.value = value;
            this.deadline = deadline;
        }


        @Override
        public int compareTo(Task other) {
            if (this.deadline == other.deadline) {
                return Integer.compare(this.value, other.value);
            }

            return Integer.compare(other.deadline, this.deadline);
        }

        @Override
        public String toString() {
            return String.format("v: %d; d: %d", this.value, this.deadline);
        }
    }
}
