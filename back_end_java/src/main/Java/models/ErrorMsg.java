package models;

public class ErrorMsg {
    private String message;

    // Constructors
    public ErrorMsg() {

    }
    public ErrorMsg(String message) {
        this.message = message;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
