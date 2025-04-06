package com.bridgelabz.javaannotations.sampleProblems;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
    String priority();
    String assignedTo();
}

// Step 2: Apply the annotation to some methods in a class
class TaskManager {

    @TaskInfo(priority = "High", assignedTo = "Nikhil")
    public void designDatabase() {
        System.out.println("Designing the database schema...");
    }

    @TaskInfo(priority = "Medium", assignedTo = "Aman")
    public void implementLogin() {
        System.out.println("Implementing login functionality...");
    }

    @TaskInfo(priority = "Low", assignedTo = "Priya")
    public void writeDocumentation() {
        System.out.println("Writing project documentation...");
    }
}

// Step 3: Use Reflection to access annotation values
public class CustomAnnotation {
    public static void main(String[] args) {
        Class<TaskManager> taskClass = TaskManager.class;

        for (Method method : taskClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TaskInfo.class)) {
                TaskInfo task = method.getAnnotation(TaskInfo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Priority: " + task.priority());
                System.out.println("Assigned To: " + task.assignedTo());}
        }
    }
}

