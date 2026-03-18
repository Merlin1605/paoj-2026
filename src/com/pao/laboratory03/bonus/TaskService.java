package com.pao.laboratory03.bonus;

import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static TaskService instance;
    private final Map<String, Task> tasksById = new HashMap<>();
    private final Map<Priority, List<Task>> tasksByPriority = new HashMap<>();
    private final List<String> auditLog = new ArrayList<>();
    private int nextId = 1;

    private TaskService() {}

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public Task addTask(String title, Priority priority) {
        String id = String.format("T%03d", nextId++);
        if (tasksById.containsKey(id)) {
            throw new DuplicateTaskException("Task-ul cu id " + id + " exista deja");
        }

        Task task = new Task(id, title, priority);
        tasksById.put(id, task);
        tasksByPriority.computeIfAbsent(priority, k -> new ArrayList<>()).add(task);

        auditLog.add("[ADD] " + id + ": '" + title + "' (" + priority + ")");
        return task;
    }

    public void assignTask(String taskId, String assignee) {
        Task task = tasksById.get(taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task-ul '" + taskId + "' nu a fost gasit");
        }
        task.setAssignee(assignee);
        auditLog.add("[ASSIGN] " + taskId + " -> " + assignee);
    }

    public void changeStatus(String taskId, Status newStatus) {
        Task task = tasksById.get(taskId);
        if (task == null) {
            throw new TaskNotFoundException("Task-ul '" + taskId + "' nu a fost gasit");
        }

        if (!task.getStatus().canTransitionTo(newStatus)) {
            throw new InvalidTransitionException(task.getStatus(), newStatus);
        }

        Status oldStatus = task.getStatus();
        task.setStatus(newStatus);
        auditLog.add("[STATUS] " + taskId + ": " + oldStatus + " -> " + newStatus);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return tasksByPriority.getOrDefault(priority, Collections.emptyList());
    }

    public Map<Status, Long> getStatusSummary() {
        Map<Status, Long> summary = new HashMap<>();
        for (Status s : Status.values()) {
            long count = tasksById.values().stream().filter(t -> t.getStatus() == s).count();
            summary.put(s, count);
        }
        return summary;
    }

    public List<Task> getUnassignedTasks() {
        return tasksById.values().stream()
                .filter(t -> t.getAssignee() == null)
                .collect(Collectors.toList());
    }

    public void printAuditLog() {
        System.out.println("=== Audit Log ===");
        auditLog.forEach(System.out::println);
    }

    public double getTotalUrgencyScore(int baseDays) {
        return tasksById.values().stream()
                .filter(t -> t.getStatus() != Status.DONE && t.getStatus() != Status.CANCELLED)
                .mapToDouble(t -> t.getPriority().calculateScore(baseDays))
                .sum();
    }
}