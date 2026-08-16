package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.OrderService;
import food.utility.OrderUtility;

public class Main{
    public static void main(String[] args){
        FoodOrder.setRestaurantName("Food Express");
        FoodOrder[] orders = new FoodOrder[6];
        orders[0]=new RegularOrder(101, "Shreya", 500);
        orders[1]=new PremiumOrder(102, "Rahul", 800);
        orders[2]=new RegularOrder(103, "Ananya", 1200);
        orders[3]=new PremiumOrder(104, "Aman", 700);
        orders[4]=new RegularOrder(105, "Riya", 1500);
        orders[5]=new PremiumOrder(106, "Karan", 900);
        for(FoodOrder order:orders){
            if(!OrderUtility.validateAmount(order.getAmount())){
                System.out.println("Invalid amount for Order ID: "+order.getOrderId());
            }
            if(!OrderUtility.validateCustomerName(order.getCustomerName())){
                System.out.println("Invalid customer name for Order ID: "+order.getOrderId());
            }
        }
        System.out.println("========== ONLINE FOOD DELIVERY SYSTEM ==========");
        OrderService orderService=new OrderService();
        orderService.displayAllOrders(orders);
        FoodOrder.displayTotalOrders();
    }
}
