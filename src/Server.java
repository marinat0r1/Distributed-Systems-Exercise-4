import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;

public class Server {
    public static void main(String[] args) {

        System.out.println("The Server is up");

        ShoppingBasket basket1 = new ShoppingBasket("Basket 1", new HashSet<>());
        ShoppingBasket basket2 = new ShoppingBasket("Basket 2", new HashSet<>());
        ShoppingBasket basket3 = new ShoppingBasket("Basket 3", new HashSet<>());

        try {
            DatagramSocket aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println(" Request: " + new String(request.getData(), 0, request.getLength()));
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
