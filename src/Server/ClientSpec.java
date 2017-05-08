package Server;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSpec {

    protected Socket socket;
    protected ObjectOutputStream out;
    protected String clientName;

    public ClientSpec(Socket socket, String clientName) {
        this.socket = socket;
        this.clientName = clientName;

        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException ex){ex.printStackTrace();}
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public String getClientName() {
        return clientName;
    }
}
