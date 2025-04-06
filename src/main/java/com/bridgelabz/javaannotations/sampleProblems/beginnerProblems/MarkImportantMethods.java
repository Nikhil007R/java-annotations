package com.bridgelabz.javaannotations.sampleProblems.beginnerProblems;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Step 1: Define the annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

// Step 2: Create a class and annotate methods
class ServiceManager {

    @ImportantMethod
    public void processPayment() {
        System.out.println("Processing payment...");
    }

    @ImportantMethod(level = "MEDIUM")
    public void generateInvoice() {
        System.out.println("Generating invoice...");
    }

    public void logActivity() {
        System.out.println("Logging user activity...");
    }
}

// Step 3: Use Reflection to detect and print annotated methods
public class MarkImportantMethods{
    public static void main(String[] args) {
        Class<ServiceManager> cls = ServiceManager.class;

        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod imp = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName());
                System.out.println("Level: " + imp.level());
            }
        }
    }
}

