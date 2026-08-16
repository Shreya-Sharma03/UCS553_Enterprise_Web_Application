package food.utility;
import food.model.Discountable;
import food.model.FoodOrder;
public class OrderUtility{
    public static boolean validateAmount(double amount){
        return amount>0;
    }
    public static boolean validateCustomerName(String customerName){
        return customerName!=null && !customerName.trim().isEmpty();
    }
    public static void generateOrderSummary(FoodOrder order){
        Discountable discountable=(Discountable) order;
        double amount=order.getAmount();
        double discount=discountable.applyDiscount();
        double deliveryCharge=order.calculateDeliveryCharge();
        double finalPayable=amount-discount+deliveryCharge;
        System.out.println("Restaurant      : "+FoodOrder.getRestaurantName());
        System.out.println("Order ID        : "+order.getOrderId());
        System.out.println("Customer Name   : "+order.getCustomerName());
        System.out.println("Bill Amount     : Rs. "+amount);
        System.out.println("Discount        : Rs. "+discount);
        System.out.println("Delivery Charge : Rs. "+deliveryCharge);
        System.out.println("Final Payable   : Rs. "+finalPayable);
    }
}
