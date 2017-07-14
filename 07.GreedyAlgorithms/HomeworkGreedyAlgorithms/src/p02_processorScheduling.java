import java.util.*;

public class p02_processorScheduling {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int tasksCount = Integer.parseInt(in.nextLine().substring(7));

        Set<Task> tasks = new TreeSet<>();
        for (int i = 0; i < tasksCount; i++) {
            String[] tokens = in.nextLine().split(" - ");
            int value = Integer.parseInt(tokens[0]);
            int deadline = Integer.parseInt(tokens[1]);

            Task task = new Task(i + 1, value, deadline);
            tasks.add(task);
        }

        Task withSmallestDeadLine = null;
        Task withBiggestDeadLine = null;
        List<Task> taken = new ArrayList<>();
        for (Task task : tasks) {
            if (taken.size() == 0) {
                taken.add(task);
                withSmallestDeadLine = task;
                withBiggestDeadLine = task;
            } else {
                if (task.deadline > withBiggestDeadLine.deadline) {
                    taken.add(task);
                    withBiggestDeadLine = task;
                } else {
                    // counts the tasks with smaller or equal deadline than current taks
                    int count = 0;
                    for (Task t : taken) {
                        if (t.deadline <= task.deadline) count++;
                    }

                    if (count < task.deadline && withBiggestDeadLine.deadline + count > taken.size()) {
                        taken.add(task);
                    }
                }
            }
        }


        List<String> indeces = new ArrayList<>();
        final int[] totalValue = {0};
        taken.stream().sorted((t1, t2) -> {
            int cmp = Integer.compare(t1.deadline, t2.deadline);
            if (cmp == 0) {
                cmp = Integer.compare(t2.value, t1.value);
            }

            return cmp;
        }).forEach(t -> {
            indeces.add(t.index + "");
            totalValue[0] += t.value;
        });

        System.out.println(String.format("Optimal schedule: %s", String.join(" -> ", indeces)));
        System.out.println(String.format("Total value: %d", totalValue[0]));
    }

    private static class Task implements Comparable<Task> {
        private int index;
        private int value;
        private int deadline;

        public Task(int index, int value, int deadline) {
            this.index = index;
            this.value = value;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(other.value, this.value);
        }

        @Override
        public String toString() {
            return String.format("v: %d; d: %d", this.value, this.deadline);
        }
    }
}
