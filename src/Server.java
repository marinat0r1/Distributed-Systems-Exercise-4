import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;

public class Server {

    public static Message deserializeMessage(byte[] arr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arr);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Message) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] serializeReply(String reply) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(reply);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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

                Message message = deserializeMessage(request.getData());
                message.print();

                String replyString = "";

                if (message.getMethodName().equals(BasketMethods.ADD_ITEM)) {
                    switch (message.getBasketName()) {
                        case "Basket 1":
                            basket1.addItemToBasket(message.getItemName(), message.getItemPrice(), message.getItemQuantity());
                            replyString = "Succes";
                            break;
                        case "Basket 2":
                            basket2.addItemToBasket(message.getItemName(), message.getItemPrice(), message.getItemQuantity());
                            replyString = "Succes";
                            break;
                        case "Basket 3":
                            basket3.addItemToBasket(message.getItemName(), message.getItemPrice(), message.getItemQuantity());
                            replyString = "Succes";
                            break;
                        default:
                            replyString = "Basket not found";
                    }
                } else if (message.getMethodName().equals(BasketMethods.GET_ALL_ITEMS)){
                    switch (message.getBasketName()) {
                        case "Basket 1":
                            for (ShoppingItem item : basket1.getItems()) {
                                replyString = replyString.concat(" " + item.getName());
                            }
                            break;
                        case "Basket 2":
                            for (ShoppingItem item : basket2.getItems()) {
                                replyString.concat(" " + item.getName());
                            }
                            break;
                        case "Basket 3":
                            for (ShoppingItem item : basket3.getItems()) {
                                replyString.concat(" " + item.getName());
                            }
                            break;
                        default:
                            replyString = "No items";
                    }
                }

                System.out.println(replyString);
                byte[] replyByteArray = serializeReply(replyString);

                //System.out.println(" Request: " + new String(request.getData(), 0, request.getLength()));
                DatagramPacket reply = new DatagramPacket(replyByteArray,replyByteArray.length, request.getAddress(), request.getPort());
                aSocket.send(reply);

            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

