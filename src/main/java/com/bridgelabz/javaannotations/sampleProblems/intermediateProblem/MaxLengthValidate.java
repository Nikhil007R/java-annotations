package com.bridgelabz.javaannotations.sampleProblems.intermediateProblem;

import java.lang.annotation.*;
import java.lang.reflect.Field;

// Step 1: Define the custom annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

// Step 2: Apply it to a User class field
class User {
    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;

        // Step 3: Validate using reflection
        validateMaxLength();
    }

    private void validateMaxLength() {
        Class<?> cls = this.getClass();
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength annotation = field.getAnnotation(MaxLength.class);
                field.setAccessible(true);

                try {
                    Object value = field.get(this);
                    if (value instanceof String) {
                        String strValue = (String) value;
                        if (strValue.length() > annotation.value()) {
                            throw new IllegalArgumentException(
                                    "Field '" + field.getName() + "' exceeds max length of " + annotation.value()
                            );
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getUsername() {
        return username;
    }
}

// Step 4: Test the validation
public class MaxLengthValidate {
    public static void main(String[] args) {
        try {
            User user1 = new User("Nikhil"); // Valid
            System.out.println("Username: " + user1.getUsername());

            User user2 = new User("ThisNameIsTooLong"); // Invalid, should throw error
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }
}
