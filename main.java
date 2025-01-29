import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Predefined list of products
        List<Item> availableItems = new ArrayList<>();
        availableItems.add(new Item("101", "Laptop", 1200.0));
        availableItems.add(new Item("102", "Mouse", 20.0));
        availableItems.add(new Item("103", "Keyboard", 50.0));
        availableItems.add(new Item("104", "Monitor", 300.0));
        availableItems.add(new Item("105", "Headphones", 80.0));

        System.out.println("Available Products:");
        for (Item item : availableItems) {
            System.out.println("ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getPrice());
        }

        System.out.println("\nEnter customer details:");

        System.out.print("Is the customer a VIP (yes/no)? ");
        String customerType = scanner.nextLine().equalsIgnoreCase("yes") ? "VIP" : "REGULAR";

        System.out.print("Enter customer id: ");
        String customerId = scanner.nextLine();

        System.out.print("Enter customer first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter customer last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        System.out.print("Enter customer delivery address: ");
        String deliveryAddress = scanner.nextLine();

        Customer customer = new Customer(customerId, firstName, lastName, email, deliveryAddress, customerType);

        System.out.println("\nEnter order details:");

        System.out.print("Enter order id: ");
        String orderId = scanner.nextLine();

        System.out.print("Enter order name: ");
        String orderName = scanner.nextLine();

        System.out.print("Enter delivery address for the order: ");
        String orderDeliveryAddress = scanner.nextLine();

        System.out.print("Is this a VIP order (yes/no)? ");
        boolean isVIPOrder = scanner.nextLine().equalsIgnoreCase("yes");

        // Choose items for the order
        List<Item> selectedItems = new ArrayList<>();
        while (true) {
            System.out.print("\nEnter the ID of the product you want to add to the order (or type 'done' to finish): ");
            String itemId = scanner.nextLine();
            if (itemId.equalsIgnoreCase("done")) {
                break;
            }

            Item selectedItem = null;
            for (Item item : availableItems) {
                if (item.getId().equals(itemId)) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem != null) {
                selectedItems.add(selectedItem);
                customer.addFavoriteItem(selectedItem);
                System.out.println("Added " + selectedItem.getName() + " to the order.");
            } else {
                System.out.println("Invalid item ID. Please try again.");
            }
        }

        System.out.print("\nEnter payment type (CREDIT CARD / CASH / CHECK / OTHER): ");
        String paymentType = scanner.nextLine();

        try {
            Order order = new Order(orderId, orderName, orderDeliveryAddress, selectedItems, customer, paymentType, isVIPOrder);
            System.out.println("\nOrder created successfully!");
            System.out.println("Total Price: " + order.getTotalPrice());

            System.out.println("\nCustomer's favorite items:");
            for (Item item : customer.getFavoriteItems()) {
                System.out.println(item.getName());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
