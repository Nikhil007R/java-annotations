package com.bridgelabz.javaannotations.sampleProblems.beginnerProblems;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

// Step 2: Apply it to methods in a class
class FeatureDevelopment {

    @Todo(task = "Implement user login", assignedTo = "Nikhil", priority = "HIGH")
    public void loginFeature() {
        // pending task
    }

    @Todo(task = "Add password reset flow", assignedTo = "Aman")
    public void resetPasswordFeature() {
        // pending task
    }

    @Todo(task = "Connect to payment gateway", assignedTo = "Nikhil", priority = "LOW")
    public void paymentIntegration() {
        // pending task
    }

    public void completedFeature() {
        // already done
    }
}

// Step 3: Use Reflection to display all pending tasks
public class TodoTracker {
    public static void main(String[] args) {
        Class<FeatureDevelopment> cls = FeatureDevelopment.class;

        System.out.println("Pending TODO Tasks:\n");

        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Task: " + todo.task());
                System.out.println("Assigned To: " + todo.assignedTo());
                System.out.println("Priority: " + todo.priority());
                System.out.println();
            }
        }
    }
}
