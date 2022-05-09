import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class Client {

    public static byte[] addShoppingItem(String name, int price, int quantity, String basketName) {

        Message message = new Message(name, price, quantity, basketName, BasketMethods.ADD_ITEM);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getBasketItems(String basketName) {

        Message message = new Message(basketName, BasketMethods.GET_ALL_ITEMS);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deserializeReply(byte[] reply) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(reply);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (String) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        try {
            // Init
            DatagramSocket aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getLocalHost();
            int serverPort = 6789;

            // Adding an item
            byte[] addItemCommand = addShoppingItem("Item1", 20, 30, "Basket 1");
            DatagramPacket request1 = new DatagramPacket(addItemCommand, addItemCommand.length, aHost, serverPort);
            aSocket.send(request1);

            byte[] buffer1 = new byte[1000];
            DatagramPacket reply1 = new DatagramPacket(buffer1, buffer1.length);
            aSocket.receive(reply1);
            System.out.println(deserializeReply(reply1.getData()));

            // Retrieving names of basket items
            byte[] retrieveItemsCommand = getBasketItems("Basket 1");
            DatagramPacket request2 = new DatagramPacket(retrieveItemsCommand, retrieveItemsCommand.length, aHost, serverPort);
            aSocket.send(request2);

            byte[] buffer2 = new byte[1000];
            DatagramPacket reply2 = new DatagramPacket(buffer2, buffer2.length);
            aSocket.receive(reply2);
            System.out.println(deserializeReply(reply2.getData()));

            // Close Socket
            aSocket.close();

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
