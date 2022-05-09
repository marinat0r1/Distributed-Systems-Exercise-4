import java.util.HashSet;

public class ShoppingBasket {

    private String name;
    private HashSet<ShoppingItem> items;

    public ShoppingBasket(String name, HashSet<ShoppingItem> items) {
        this.name = name;
        this.items = items;
        items = new HashSet<ShoppingItem>();
    }

    public ShoppingItem searchItem(String itemName) {
        for (ShoppingItem item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void addItemToBasket(String name, int price, int quantity) {
        items.add(new ShoppingItem(name, price, quantity));
    }

    public String getName() {
        return name;
    }

    public HashSet<ShoppingItem> getItems() {
        return items;
    }

}
