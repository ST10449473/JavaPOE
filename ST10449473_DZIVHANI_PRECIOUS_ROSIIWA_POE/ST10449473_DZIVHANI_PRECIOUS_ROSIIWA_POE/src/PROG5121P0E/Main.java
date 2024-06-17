/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PROG5121P0E;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Main {

    // Arrays to store task details
    private static ArrayList<String> developers = new ArrayList<>();
    private static ArrayList<String> taskNames = new ArrayList<>();
    private static ArrayList<String> taskIDs = new ArrayList<>();
    private static ArrayList<Double> taskDurations = new ArrayList<>();
    private static ArrayList<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        LogIn login = new LogIn();

        int value = 0;
        while (value != 3) {
            String[] options = {"Sign Up", "Log In", "Quit"};
            value = JOptionPane.showOptionDialog(null, "Do you want to sign up or log in?", 
                    "User Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (value) {
                case 0 -> {
                    String inputFirstName = JOptionPane.showInputDialog("Enter your first name:");
                    login.setFirstName(inputFirstName);
                    String inputLastName = JOptionPane.showInputDialog("Enter your last name:");
                    login.setLastName(inputLastName);
                    String inputUsername = JOptionPane.showInputDialog("Enter your username:");
                    login.setUsername(inputUsername);
                    String inputPassword = JOptionPane.showInputDialog("Enter your password:");
                    login.setPassword(inputPassword);
                    JOptionPane.showMessageDialog(null, login.registerUser());
                }
                case 1 -> {
                    String inputUsername = JOptionPane.showInputDialog("Enter your username:");
                    String inputPassword = JOptionPane.showInputDialog("Enter your password:");
                    JOptionPane.showMessageDialog(null, login.returnLoginStatus(inputUsername, inputPassword));
                    if (login.loginUser(inputUsername, inputPassword)) {
                        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban.");
                        int userValue = 0;
                        while (true) {
                            String[] taskOptions = {"Add tasks", "Show report", "Perform Operations", "Quit"};
                            userValue = JOptionPane.showOptionDialog(null, "What would you like to do?", 
                                    "Task Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, taskOptions, taskOptions[0]);

                            switch (userValue) {
                                case 0 -> createTask();
                                case 1 -> JOptionPane.showMessageDialog(null, generateReport());
                                case 2 -> performOperations();
                                case 3 -> {
                                    JOptionPane.showMessageDialog(null, "Exiting...");
                                    return;
                                }
                                default -> JOptionPane.showMessageDialog(null, "Choose a valid option.");
                            }
                        }
                    }
                }
                case 2 -> JOptionPane.showMessageDialog(null, "Exiting...");
                default -> JOptionPane.showMessageDialog(null, "Please choose a valid option.");
            }
        }
    }

    public static void createTask() {
        int numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
        Task[] tasks = new Task[numberOfTasks];

        for (int i = 0; i < numberOfTasks; i++) {
            Task task = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
            task.setTaskNumber(i);
            task.setTaskName(JOptionPane.showInputDialog("Enter the task name:"));
            task.setDeveloperDetails(JOptionPane.showInputDialog("Enter the developer details:"));
            task.setTaskDuration(Double.parseDouble(JOptionPane.showInputDialog("Enter the task duration:")));
            task.setTaskID(task.createTaskID());

            String[] statusOptions = {"To Do", "Done", "Doing"};
            int statusChoice = JOptionPane.showOptionDialog(null, "Select the task status:", "Task Status",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);
            task.setTaskStatus(statusOptions[statusChoice]);

            tasks[i] = task;

            if (task.checkTaskDescription()) {
                JOptionPane.showMessageDialog(null, "Task successfully captured");
                // Populate the arrays
                developers.add(task.getDeveloperDetails());
                taskNames.add(task.getTaskName());
                taskIDs.add(task.getTaskID());
                taskDurations.add(task.getTaskDuration());
                taskStatuses.add(task.getTaskStatus());
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
        }
    }

    public static String generateReport() {
        StringBuilder taskDetails = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            taskDetails.append("Task Status: ").append(taskStatuses.get(i))
                    .append("\nDeveloper Details: ").append(developers.get(i))
                    .append("\nTask Number: ").append(i)
                    .append("\nTask Name: ").append(taskNames.get(i))
                    .append("\nTask ID: ").append(taskIDs.get(i))
                    .append("\nDuration: ").append(taskDurations.get(i)).append("\n\n");
        }
        taskDetails.append("Total Hours: ").append(Task.returnTotalHours(taskDurations));
        return taskDetails.toString();
    }

    public static void performOperations() {
        String[] operations = {
                "Display tasks with status 'Done'", 
                "Display task with longest duration", 
                "Search for a task by name", 
                "Search tasks by developer", 
                "Delete a task by name"
        };

        int operationChoice = JOptionPane.showOptionDialog(null, "Select an operation:", "Operations",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, operations, operations[0]);

        switch (operationChoice) {
            case 0 -> displayTasksWithStatusDone();
            case 1 -> displayTaskWithLongestDuration();
            case 2 -> searchTaskByName();
            case 3 -> searchTasksByDeveloper();
            case 4 -> deleteTaskByName();
            default -> JOptionPane.showMessageDialog(null, "Choose a valid option.");
        }
    }

    public static void displayTasksWithStatusDone() {
        StringBuilder details = new StringBuilder("Tasks with status 'Done':\n");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if (taskStatuses.get(i).equalsIgnoreCase("Done")) {
                details.append("Developer: ").append(developers.get(i))
                        .append(", Task Name: ").append(taskNames.get(i))
                        .append(", Task Duration: ").append(taskDurations.get(i)).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, details.toString());
    }

    public static void displayTaskWithLongestDuration() {
        int maxIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(maxIndex)) {
                maxIndex = i;
            }
        }
        String details = "Task with the longest duration:\n" +
                "Developer: " + developers.get(maxIndex) +
                ", Duration: " + taskDurations.get(maxIndex);
        JOptionPane.showMessageDialog(null, details);
    }

    public static void searchTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to search:");
        int index = taskNames.indexOf(taskName);
        if (index != -1) {
            String details = "Task Found:\n" +
                    "Task Name: " + taskNames.get(index) +
                    ", Developer: " + developers.get(index) +
                    ", Task Status: " + taskStatuses.get(index);
            JOptionPane.showMessageDialog(null, details);
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    public static void searchTasksByDeveloper() {
        String developer = JOptionPane.showInputDialog("Enter the developer name to search tasks:");
        StringBuilder details = new StringBuilder("Tasks assigned to " + developer + ":\n");
        boolean found = false;
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                details.append("Task Name: ").append(taskNames.get(i))
                        .append(", Task Status: ").append(taskStatuses.get(i)).append("\n");
                found = true;
            }
        }
        if (found) {
            JOptionPane.showMessageDialog(null, details.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for the developer.");
        }
    }

    public static void deleteTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");
        int index = taskNames.indexOf(taskName);
        if (index != -1) {
            developers.remove(index);
            taskNames.remove(index);
            taskIDs.remove(index);
            taskDurations.remove(index);
            taskStatuses.remove(index);
            JOptionPane.showMessageDialog(null, "Task deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
}

