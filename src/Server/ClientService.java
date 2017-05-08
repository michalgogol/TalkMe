package Server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientService implements Runnable  {

    private Socket clientSocket;
    private List<ClientSpec> users;
    private String currentClientName;

    public ClientService(Socket clientSocket, List<ClientSpec> users) {
        this.clientSocket = clientSocket;
        this.users = users;
        currentClientName = users.get(users.size()-1).getClientName();
    }

    @Override
    public void run() {

        try {
            ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream clientInput = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {

                ServerResponse resp = (ServerResponse) clientInput.readObject();


                switch (resp.getType())
                {
                    case "Configuration":
                        output = getRecipentStream(resp.getRecipientName());
                        break;
                    case  "FromClient":
                        resp.setSenderName(currentClientName);
                        resp.setRecipientName(resp.getRecipientName());
                        resp.setMessage(resp.getMessage());
                        output.writeObject(resp);
                        break;
                    case  "FromRecipient":
                        System.out.println(resp.getMessage());
                        break;
                }



        }
        }catch (IOException | ClassNotFoundException ex){ex.printStackTrace();}
    }

    public Socket getRecipientSocket(String userName)
    {
        for(ClientSpec spec : users)
        {
            if(spec.getClientName().equals(userName))
                return spec.getSocket();
        }

        return clientSocket;
    }

    public ObjectOutputStream getRecipentStream(String userName) throws IOException
    {

        for(ClientSpec spec : users)
        {
            if(spec.getClientName().equals(userName))
               return spec.getOut();
        }
        return new ObjectOutputStream(clientSocket.getOutputStream());
    }





}
