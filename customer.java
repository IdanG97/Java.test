import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String deliveryAddress;
    private String customerType; // REGULAR / VIP
    private Double discount;
    private List<Item> favoriteItems = new ArrayList<>();
    private Gift gift;

    public Customer(String id, String firstName, String lastName, String email, String deliveryAddress, String customerType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.customerType = customerType;
        if (customerType.equals("VIP")) {
            // Assign a random discount between 10 and 20 percent for VIP customers
            this.discount = 10 + (Math.random() * 10);
        } else {
            this.discount = 0.0;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getCustomerType() {
        return customerType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void addFavoriteItem(Item item) {
        for (Item i : favoriteItems) {
            if (i.getName().equals(item.getName())) {
                return; // Prevent duplicate item by name
            }
        }
        favoriteItems.add(item);
    }

    public void takeGift(Gift gift) {
        this.gift = gift;
    }

    public void openGift() {
        if (gift != null) {
            gift.openGift();
        } else {
            System.out.println("No gift available.");
        }
    }

    public List<Item> getFavoriteItems() {
        return favoriteItems;
    }
}
