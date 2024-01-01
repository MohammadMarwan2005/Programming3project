package com.mohammadmarwan.Additional;

public class IsValid {

    public static final IsValid NICE = new IsValid(true, "Good Password");
    private boolean isValid;
    private String cause = "";

    public IsValid(boolean isValid, String cause) {
        this.isValid = isValid;
        this.cause = cause;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getCause() {
        return cause;
    }
}