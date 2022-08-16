import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class SocketServer {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public SocketServer(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server iniciado");

            System.out.println("Aguardando cliente...");

            socket = server.accept();
            System.out.println("Cliente aceito");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            String line = "";

            while (!line.equals("Fim")) {

                try {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws IOException {

        SocketServer socketServer = new SocketServer(8081);
    }
}
