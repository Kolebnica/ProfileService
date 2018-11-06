package pojo;

public class ResponseError {

    private int status;
    private String[] messages;

    /**
     * @param status HTTP status code of the error
     * @param message User-friendly message about the error.
     */
    public ResponseError(int status, String message) {
        this.status = status;
        this.messages = new String[]{message};
    }

    /**
     * @param status HTTP status code of the error
     * @param messages User-friendly messages about the error.
     */
    public ResponseError(int status, String[] messages) {
        this.status = status;
        this.messages = messages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public static ResponseError error404() {
        return new ResponseError(404, "Not found.");
    }

    public static ResponseError error500() {
        return new ResponseError(500, "Server error.");
    }

}