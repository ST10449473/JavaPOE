/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package PROG5121POE;

import PROG5121P0E.Task;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TaskTest {

    // Your existing tests
    @Test
    public void testTaskDescriptionLengthSuccess() {
        Task task = new Task("Login Feature", 1, "Create Login to authenticate users", "Robyn Harrison", 8, "To Do");
        assertTrue(task.checkTaskDescription());
    }

    @Test
    public void testTaskDescriptionLengthFailure() {
        Task task = new Task("Add Task Feature", 2, "Create Add Task feature to add taskusers which exceeds 50 characters", "Developer2", 10, "Doing");
        assertFalse(task.checkTaskDescription());
    }

    @Test
    public void testTaskIDGeneration() {
        Task task = new Task("Add Task", 1, "Task description", "Robyn", 8, "To Do");
        assertEquals("AD:1:BYN", task.createTaskID());
    }

    @Test
    public void testRemainingTaskIDs() {
        String[] expectedTaskIDs = {"CR:0:IKE", "CR:1:ARD", "CR:2:THA", "CR:3:ND"};
        Task[] tasks = new Task[expectedTaskIDs.length];
        for (int i = 0; i < expectedTaskIDs.length; i++) {
            tasks[i] = new Task("Create", i, "Task description", "Mike", 8, "To Do");
        }
        for (int i = 0; i < expectedTaskIDs.length; i++) {
            assertEquals(expectedTaskIDs[i], tasks[i].createTaskID());
        }
    }

    @Test
    public void testTotalTaskDuration() {
        Task task1 = new Task("Task1", 1, "Task description", "Developer1", 10, "To Do");
        Task task2 = new Task("Task2", 2, "Task description", "Developer2", 12, "To Do");
        Task[] tasks = {task1, task2};
        int totalDuration = (int) Task.returnTotalHours(tasks);
        assertEquals(22, totalDuration);
    }

    @Test
    public void testTotalTaskDurationWithMoreData() {
        Task[] tasks = new Task[5];
        int[] durations = {10, 12, 55, 11, 1};
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task("Task" + (i + 1), i + 1, "Task description", "Developer" + (i + 1), durations[i], "To Do");
        }
        int totalDuration = Task.returnTotalHours(tasks);
        assertEquals(89, totalDuration);
    }

    // New tests for array functionalities
    private ArrayList<String> developers;
    private ArrayList<String> taskNames;
    private ArrayList<String> taskIDs;
    private ArrayList<Double> taskDurations;
    private ArrayList<String> taskStatuses;

    @Before
    public void setUp() {
        // Initialize arrays with test data
        developers = new ArrayList<>();
        taskNames = new ArrayList<>();
        taskIDs = new ArrayList<>();
        taskDurations = new ArrayList<>();
        taskStatuses = new ArrayList<>();

        // Adding test data
        developers.add("Mike Smith");
        developers.add("Edward Harrington");
        developers.add("Samantha Paulson");
        developers.add("Glenda Oberholzer");

        taskNames.add("Create Login");
        taskNames.add("Setup Database");
        taskNames.add("Create Reports");
        taskNames.add("User Authentication");

        taskIDs.add("CL:0:ITH");
        taskIDs.add("SD:1:TON");
        taskIDs.add("CR:2:LSON");
        taskIDs.add("UA:3:ZER");

        taskDurations.add(5.0);
        taskDurations.add(8.0);
        taskDurations.add(7.0);
        taskDurations.add(11.0);

        taskStatuses.add("To Do");
        taskStatuses.add("Doing");
        taskStatuses.add("To Do");
        taskStatuses.add("Done");
    }

    @Test
    public void testDeveloperArrayPopulation() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrington", "Samantha Paulson", "Glenda Oberholzer"};
        assertArrayEquals(expectedDevelopers, developers.toArray());
    }

    @Test
    public void testLongestTaskDuration() {
        int maxIndex = 0;
        for (int i = 1; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > taskDurations.get(maxIndex)) {
                maxIndex = i;
            }
        }
        assertEquals("Glenda Oberholzer", developers.get(maxIndex));
        assertEquals(Double.valueOf(11.0), taskDurations.get(maxIndex));
    }

    @Test
    public void testSearchTaskByName() {
        String taskName = "Create Login";
        int index = taskNames.indexOf(taskName);
        assertEquals("Mike Smith", developers.get(index));
        assertEquals(taskName, taskNames.get(index));
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developer = "Samantha Paulson";
        ArrayList<String> expectedTasks = new ArrayList<>();
        ArrayList<String> expectedStatuses = new ArrayList<>();
        
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developer)) {
                expectedTasks.add(taskNames.get(i));
                expectedStatuses.add(taskStatuses.get(i));
            }
        }
        
        assertEquals(1, expectedTasks.size());
        assertEquals("Create Reports", expectedTasks.get(0));
        assertEquals("To Do", expectedStatuses.get(0));
    }

    @Test
    public void testDeleteTaskByName() {
        String taskNameToDelete = "Create Reports";
        int index = taskNames.indexOf(taskNameToDelete);

        if (index != -1) {
            developers.remove(index);
            taskNames.remove(index);
            taskIDs.remove(index);
            taskDurations.remove(index);
            taskStatuses.remove(index);
        }

        assertFalse(taskNames.contains(taskNameToDelete));
    }

    @Test
    public void testGenerateReport() {
        StringBuilder taskDetails = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            taskDetails.append("Task Status: ").append(taskStatuses.get(i))
                    .append("\nDeveloper Details: ").append(developers.get(i))
                    .append("\nTask Number: ").append(i)
                    .append("\nTask Name: ").append(taskNames.get(i))
                    .append("\nTask Description: N/A")
                    .append("\nTask ID: ").append(taskIDs.get(i))
                    .append("\nDuration: ").append(taskDurations.get(i)).append("\n\n");
        }
        taskDetails.append("Total Hours: ").append(Task.returnTotalHours(taskDurations));

        String expectedReport = "Task Status: To Do\nDeveloper Details: Mike Smith\nTask Number: 0\nTask Name: Create Login\nTask Description: N/A\nTask ID: CL:0:ITH\nDuration: 5.0\n\n" +
                "Task Status: Doing\nDeveloper Details: Edward Harrington\nTask Number: 1\nTask Name: Setup Database\nTask Description: N/A\nTask ID: SD:1:TON\nDuration: 8.0\n\n" +
                "Task Status: To Do\nDeveloper Details: Samantha Paulson\nTask Number: 2\nTask Name: Create Reports\nTask Description: N/A\nTask ID: CR:2:LSON\nDuration: 7.0\n\n" +
                "Task Status: Done\nDeveloper Details: Glenda Oberholzer\nTask Number: 3\nTask Name: User Authentication\nTask Description: N/A\nTask ID: UA:3:ZER\nDuration: 11.0\n\n" +
                "Total Hours: 31.0";

        assertEquals(expectedReport, taskDetails.toString());
    }
}



