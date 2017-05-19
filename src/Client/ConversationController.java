package Client;

public class ConversationController {

    private String text = "";
    private String recipient;

    public void setText(String text) {
        this.text = text;
    }
    public void setText(String text, String recipient) {
        this.text = text;
        this.recipient = recipient;
    }

    public void conversationStart(ClientController c, Controller controll) {
        while (true) {
            if (text != "") {
                c = new ClientController();
                try {
                    c.setConnection(text, controll);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                text = "";
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
        while (true) {
            if (text != "") {
                c.setMessage(text);
                text = "";
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

}
