package food.service;

import food.model.FoodOrder;
import food.utility.OrderUtility;

public class OrderService{
    public void displayAllOrders(FoodOrder[] orders){
        for (FoodOrder order:orders) {
            OrderUtility.generateOrderSummary(order);
        }
    }
}
