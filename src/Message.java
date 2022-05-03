import java.io.Serializable;

public class Message implements Serializable {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String basketName;
    private BasketMethods methodName;

    public Message(String itemName, int itemPrice, int itemQuantity, String basketName, BasketMethods methodName) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.basketName = basketName;
        this.methodName = methodName;
    }
}
