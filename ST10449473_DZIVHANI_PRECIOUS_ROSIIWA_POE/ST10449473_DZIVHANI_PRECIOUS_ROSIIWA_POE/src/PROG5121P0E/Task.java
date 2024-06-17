/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROG5121P0E;

import java.util.ArrayList;

/**
 *
 * @author Dzivhani Precious Rosiiwa
 */


public class Task {

    public static int returnTotalHours(Task[] tasks) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
private String taskName;
private int taskNumber;
private String taskDescription;
private String developerDetails;
private double taskDuration;
private String taskID;
private String taskStatus;
public Task(String taskName, int taskNumber, String taskDescription, String
developerDetails, double taskDuration, String taskStatus) {
this.taskName = taskName;
this.taskNumber = taskNumber;
this.taskDescription = taskDescription;
this.developerDetails = developerDetails;
this.taskDuration = taskDuration;
this.taskStatus = taskStatus;
this.taskID = createTaskID();
}
// Getters and Setters
public String getTaskName() { return taskName; }
public void setTaskName(String taskName) { this.taskName = taskName; }
public int getTaskNumber() { return taskNumber; }
public void setTaskNumber(int taskNumber) { this.taskNumber = taskNumber; }
public String getTaskDescription() { return taskDescription; }
public void setTaskDescription(String taskDescription) { this.taskDescription =
taskDescription; }
public String getDeveloperDetails() { return developerDetails; }
public void setDeveloperDetails(String developerDetails) { this.developerDetails =
developerDetails; }
public double getTaskDuration() { return taskDuration; }
public void setTaskDuration(double taskDuration) { this.taskDuration = taskDuration; }
public String getTaskID() { return taskID; }
public void setTaskID(String taskID) { this.taskID = taskID; }
public String getTaskStatus() { return taskStatus; }
public void setTaskStatus(String taskStatus) { this.taskStatus = taskStatus; }
public boolean checkTaskDescription() {
return taskDescription.length() <= 50;
}
public String createTaskID() {
String taskNamePrefix = taskName.substring(0, 2).toUpperCase();
String developerNameSuffix = developerDetails.substring(developerDetails.length() -
3).toUpperCase();
return taskNamePrefix + ":" + taskNumber + ":" + developerNameSuffix;
}
public String printTaskDetails() {
return "Task Status: " + taskStatus +
"\nDeveloper Details: " + developerDetails +
"\nTask Number: " + taskNumber +
"\nTask Name: " + taskName +
"\nTask Description: " + taskDescription +
"\nTask ID: " + taskID +
"\nDuration: " + taskDuration;
}
public static double returnTotalHours(ArrayList<Double> taskDurations) {
    double totalHours = 0;
    for (double duration : taskDurations) {
        totalHours += duration;
    }
    return totalHours;
}
}



