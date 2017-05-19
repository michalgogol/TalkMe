package Client;

import Server.ClientSpec;
import Server.Message;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class ClientController {

    private List<ClientSpec> users;
    private String message = "";

    public void setConnection(String name, Controller controller) throws ClassNotFoundException {

        try {
            Socket connect = new Socket("127.0.0.1", 1110);
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            final ObjectOutputStream send2 = new ObjectOutputStream(connect.getOutputStream());
            Message ser = new Message();
            ser.setMessage(name);
            ser.setType("Configuration");
            send2.writeObject(ser);
            send2.flush();
            ser.setType("");


            Thread write = new Thread(() -> {

                try {
      /*
                    while (true) {
                        if (message != "") {
                            System.out.println(message);
                            message = "";
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
*/

                    while (true) {

                        if(ser.getType().equals("Configuration"))
                        {
                            send2.writeObject(ser);
                            send2.flush();
                        }
                     /*   String text = in.readLine();
                        ser2.setType("Configuration");
                        ser2.setMessage(text);
                        ser2.setRecipientName("John_Doe");
                        send2.writeObject(ser2);
                        send2.flush();*/

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });
            Thread read = new Thread(() -> {

                try {
                    Message receivedMessage;
                    ObjectInputStream stream = new ObjectInputStream(connect.getInputStream());

                    while (true) {

                        receivedMessage = (Message) stream.readObject();

                        if (receivedMessage.getOnlineUsers() != null) {

                            for (int i = 0; i < receivedMessage.getOnlineUsers().length; i++)
                                System.out.println(receivedMessage.getOnlineUsers()[i]);

                            controller.setCurrentUsers(receivedMessage.getOnlineUsers());
                            controller.changeText(receivedMessage.getOnlineUsers().toString());
                        } else
                            controller.changeText(receivedMessage.getMessage());


                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            });


            write.start();
            read.start();

        } catch (IOException ex) {
            System.out.println("Connection problems");
        }


    }

    public void setMessage(String message) {
        this.message = message;
    }

}
