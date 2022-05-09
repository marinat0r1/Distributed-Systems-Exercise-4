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

    public Message(String basketName, BasketMethods methodName) {
        this.itemName = "";
        this.itemPrice = 0;
        this.itemQuantity = 0;
        this.basketName = basketName;
        this.methodName = methodName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public BasketMethods getMethodName() {
        return methodName;
    }

    public void setMethodName(BasketMethods methodName) {
        this.methodName = methodName;
    }

    public void print() {
        System.out.println(itemName + " / " + itemPrice + " / " + itemQuantity + " / " + basketName + " / " + methodName);
    }
}
