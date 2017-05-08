package Client;

import Server.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientController {

   private static String name;

    public void setConnection() throws ClassNotFoundException {

        try {
            Socket connect = new Socket("127.0.0.1", 1110);
            final PrintWriter sendText = new PrintWriter(connect.getOutputStream(),true);
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            sendText.println(name);

            final ObjectInputStream stream = new ObjectInputStream(connect.getInputStream());
            final ObjectOutputStream send2 = new ObjectOutputStream(connect.getOutputStream());
            Message ser = new Message();
            Thread write = new Thread(() -> {

                try {
                    while (true) {
                        String text = in.readLine();
                        ser.setMessage(text);
                        System.out.print(ser.getMessage());
                        ser.setRecipientName("John_Doe");
                        send2.writeObject(ser);
                    }
                }catch (IOException  ex){ex.printStackTrace();}

    });
            Thread read = new Thread(() -> {

                try {
                    while (true) {
                        Message tr = (Message) stream.readObject();
                        System.out.println(tr.getMessage());
                    }
                }catch (IOException | ClassNotFoundException  ex){ex.printStackTrace();}

            });


            write.start();
            read.start();

        } catch (IOException  ex) {
            System.out.println("Connection problems");
        }



    }

    public static void main(String[] args)
    {

        System.out.println("Insert name");
        name = new Scanner(System.in).nextLine();

        ClientController c = new ClientController();
        try {
            c.setConnection();
        }catch (ClassNotFoundException ex){ex.printStackTrace();}
    }

}
