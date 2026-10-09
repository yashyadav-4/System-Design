package LLD.MultiThreadingAndConcurrency.ProducerConsumerProblem;

import java.util.*;

class CoffeeMachine{
    private boolean isCoffeeReady = false;

    public synchronized void makeCoffee() throws InterruptedException{
        while(isCoffeeReady){
            wait();
        }

        System.out.println("Brewing coffee…");
        Thread.sleep(1000); // Simulate time to make coffee

        // Set the buffer to full (coffee is now ready)
        isCoffeeReady = true;
        System.out.println("Coffee ready!");

        // Notify the consumer thread that coffee is available
        notify();
    }

    public synchronized void drinkCoffee() throws InterruptedException{
        while(!isCoffeeReady){
            // Releases lock and waits until notified by producer
            wait();
        }

        // Simulate coffee consumption
        System.out.println("Drinking coffee…");
        Thread.sleep(1000); // Simulate time to drink coffee

        // Set the buffer to empty (coffee has been consumed)
        isCoffeeReady = false;
        System.out.println("Cup emptied - brew next!");

        // Notify the producer thread that it can brew again
        notify();
    }
}

public class SingleObjectProcessing {
    public static void main(String[] args) {
        // Shared CoffeeMachine object acts as the synchronized monitor
        CoffeeMachine machine = new CoffeeMachine();

        // Producer thread that continuously makes coffee
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    machine.makeCoffee(); // Try to produce coffee
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread that continuously drinks coffee
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    machine.drinkCoffee(); // Try to consume coffee
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        producer.start();
        consumer.start();
    }
}
