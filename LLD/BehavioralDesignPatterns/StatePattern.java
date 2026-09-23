package LLD.BehavioralDesignPatterns;

import java.util.*;

class OrderContext{
    private OrderState currState;

    public OrderContext(){
        this.currState = new OrderPlacedState();
    }

    public void setState(OrderState state){
        this.currState = state;
    }

    public void next(){
        currState.next(this);
    }

    public void cancel(){
        currState.cancelOrder(this);
    }
    public String getCurrentState(){
        return currState.getStateName();
    }
}

interface OrderState{
    void next(OrderContext context);
    void cancelOrder(OrderContext context);
    String getStateName();
}

class OrderPlacedState implements OrderState{
    public void next(OrderContext context){
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared.");
    }

    public void cancelOrder(OrderContext context){
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName(){
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState{
    public void next(OrderContext context){
        context.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery.");
    }

    public void cancelOrder(OrderContext context){
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    public String getStateName(){
        return "PREPARING_ORDER";
    }
}

class OutForDeliveryState implements OrderState{
    public void next(OrderContext context){
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered.");
    }

    public void cancelOrder(OrderContext context){
        System.out.println("Cannot cancel. Order is out for delivery.");
    }

    public String getStateName(){
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState{
    public void next(OrderContext context){
        System.out.println("Order is already delivered.");
    }

    public void cancelOrder(OrderContext context){
        System.out.println("Cannot cancel a delivered order.");
    }

    public String getStateName(){
        return "Order_Delivered";
    }
}

class CancelledState implements OrderState{
    public void next(OrderContext context){
        System.out.println("Cancelled order cannot move to next state.");
    }

    public void cancelOrder(OrderContext context){
        System.out.println("Order is already cancelled.");
    }

    public String getStateName(){
        return "Order_Cancelled";
    }
}


public class StatePattern {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        // display initial state
        System.out.println("Current State: " + order.getCurrentState());

        // moving through states
        order.next();  // ORDER_PLACED -> PREPARING
        order.next();  // PREPARING -> OUT_FOR_DELIVERY
        order.cancel(); // Should fail, as order is out for delivery
        order.next();  // OUT_FOR_DELIVERY -> DELIVERED
        order.cancel(); // Should fail, as order is delivered

        // display final state
        System.out.println("Final State: " + order.getCurrentState());
    }
}
