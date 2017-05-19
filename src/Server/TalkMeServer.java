package Server;


import Client.Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TalkMeServer {

    private List<ClientSpec> users = new ArrayList<ClientSpec>();

    public void startServ() {
        final ExecutorService clientArea = Executors.newFixedThreadPool(10);


        Runnable serverTask = () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(1110);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                    users.add(new ClientSpec(clientSocket, ((Message) inputStream.readObject()).getMessage(), inputStream));
                    clientArea.submit(new ClientService(clientSocket, users));


                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Unable to process client request");
                e.printStackTrace();
            }
        };

        Thread server = new Thread(serverTask);
        server.start();

    }


    public static void main(String[] agrs) {
        TalkMeServer tm = new TalkMeServer();
        tm.startServ();

    }


}
