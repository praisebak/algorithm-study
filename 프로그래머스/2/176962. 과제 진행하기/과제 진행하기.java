import java.util.*;

class Solution {
    class Task implements Comparable<Task> {
        String name;
        int start;
        int playtime;

        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Task other) {
            return this.start - other.start;
        }
    }

    public int parseTime(String s) {
        String[] parts = s.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public String[] solution(String[][] plans) {
        List<String> finishedTasks = new ArrayList<>();
        Stack<Task> pausedTasks = new Stack<>();
        Task[] tasks = new Task[plans.length];

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int start = parseTime(plans[i][1]);
            int playtime = Integer.parseInt(plans[i][2]);
            tasks[i] = new Task(name, start, playtime);
        }
        
        Arrays.sort(tasks);

        for (int i = 0; i < tasks.length; i++) {
            if (i == tasks.length - 1) {
                finishedTasks.add(tasks[i].name);
                break;
            }

            Task currentTask = tasks[i];
            Task nextTask = tasks[i + 1];

            int availableTime = nextTask.start - currentTask.start;

            if (availableTime >= currentTask.playtime) {
                finishedTasks.add(currentTask.name);
                int remainingTime = availableTime - currentTask.playtime;

                while (remainingTime > 0 && !pausedTasks.isEmpty()) {
                    Task paused = pausedTasks.peek();
                    if (remainingTime >= paused.playtime) {
                        finishedTasks.add(pausedTasks.pop().name);
                        remainingTime -= paused.playtime;
                    } else {
                        paused.playtime -= remainingTime;
                        remainingTime = 0;
                    }
                }
            } else {
                currentTask.playtime -= availableTime;
                pausedTasks.push(currentTask);
            }
        }

        while (!pausedTasks.isEmpty()) {
            finishedTasks.add(pausedTasks.pop().name);
        }

        return finishedTasks.toArray(new String[0]);
    }
}