package ukol2.server;

public class Message {
    private String message;
    private String userFrom;
    private String userTo;

    Message(String message, String userFrom, String userTo) {
        this.message = message;
        this.userFrom = userFrom;
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }
    public String getUserFrom() {
        return userFrom;
    }
    public String getUserTo() {return userTo;}
}
