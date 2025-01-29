import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Order {
    private String id;
    private String name;
    private String deliveryAddress;
    private List<Item> items = new ArrayList<>();
    private Customer customer;
    private double totalPrice;
    private String paymentType;
    private Date orderDate;
    private boolean isVIPOrder;

    public Order(String id, String name, String deliveryAddress, List<Item> items, Customer customer, String paymentType, boolean isVIPOrder) {
        this.id = id;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.customer = customer;
        this.paymentType = paymentType;
        this.isVIPOrder = isVIPOrder;
        this.orderDate = new Date();
        calculateTotalPrice();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    private void calculateTotalPrice() {
        double sum = 0;
        for (Item item : items) {
            sum += item.getPrice();
        }
        if (isVIPOrder) {
            if ("VIP".equals(customer.getCustomerType())) {
                totalPrice = sum * (1 - customer.getDiscount() / 100);
            } else {
                throw new IllegalArgumentException("Customer is not a VIP but the order is a VIP order.");
            }
        } else {
            totalPrice = sum;
        }
    }
}

