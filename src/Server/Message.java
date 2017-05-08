package Server;


import java.io.Serializable;

public class Message implements Serializable {

    private String message;
    private String recipientName;
    private String senderName;
    private String type;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipientName() {
        return recipientName;
    }
}
