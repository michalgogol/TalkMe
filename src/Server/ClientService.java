package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientService implements Runnable {

    private Socket clientSocket;
    private ObjectOutputStream clientStream;
    private List<ClientSpec> users;
    private String currentClientName;

    public ClientService(Socket clientSocket, List<ClientSpec> users) {
        this.clientSocket = clientSocket;
        this.users = users;
        currentClientName = users.get(users.size() - 1).getClientName();
        clientStream = users.get(users.size() - 1).getOut();
    }

    @Override
    public void run() {

        Thread onlineUsers = new Thread(() ->
        {
            int onlineUsersCounter = 0;
            Message message = new Message();

            while (true) {

                if (onlineUsersCounter != users.size()) {
                    String[] usersNames = users.stream()
                            .filter(e -> !e.getClientName().equals(currentClientName))
                            .map(e->e.getClientName())
                            .toArray(String[]::new);


                    message.setOnlineUsers(usersNames);
                    onlineUsersCounter = users.size();


                    try {
                        clientStream.writeObject(message);
                        clientStream.reset();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        onlineUsers.start();

        try {


            ObjectInputStream clientInput = users.get(users.size() - 1).getIn();


            while (true) {

                Message resp = (Message) clientInput.readObject();
                setRecipentStream(resp.getRecipientName());
                ObjectOutputStream output = clientStream;
                switch (resp.getType()) {

                    case "Configuration":
                        output.writeObject(resp);
                        break;
                    case "FromClient":
                        resp.setSenderName(currentClientName);
                        resp.setRecipientName(resp.getRecipientName());
                        resp.setMessage(resp.getMessage());
                        output.writeObject(resp);
                        break;
                    case "FromRecipient":
                        System.out.println(resp.getMessage());
                        break;
                    default:
                        System.out.print(resp.getMessage());
                }


            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    public void setRecipentStream(String userName) throws IOException {

        for (ClientSpec spec : users) {
            if (spec.getClientName().equals(userName))
                clientStream = spec.getOut();

            System.out.println(clientStream.equals(spec.getOut()));


        }

    }


}
