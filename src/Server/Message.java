package Server;


import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID =12358903454875L;
    private String message;
    private String recipientName;
    private String senderName;
    private String type;
    private String[] onlineUsers;

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

    public String[] getOnlineUsers() {

        return onlineUsers;
    }

    public void setOnlineUsers(String[] onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
}
