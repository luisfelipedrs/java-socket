import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class SocketClient {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public SocketClient(String address, int port) {

        try {
            socket = new Socket(address, port);
            System.out.println("Conectado");

            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        }
        catch (UnknownHostException i) {
            System.out.println(i);
        }
        catch (IOException e) {
            System.out.println(e);
        }

        String line = "";

        while (!line.equals("Fim")) {

            try {
                line = input.readLine();
                output.writeUTF(line);
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        SocketClient socketClient = new SocketClient("127.0.0.1", 8081);
    }
}
