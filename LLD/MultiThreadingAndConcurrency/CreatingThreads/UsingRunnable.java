package LLD.MultiThreadingAndConcurrency.CreatingThreads;

import java.util.*;

class SMSTask implements Runnable{
    public void run(){
        try{
            Thread.sleep(1000);
            System.out.println("SMS Sent using Runnable.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailTask implements Runnable{
    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            System.out.println("Email Sent using Runnable.");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ETATask implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(5000);
            System.out.println("ETA Calculated using Runnable. Estimated Time: 25 minutes.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}


public class UsingRunnable {
    public static void main(String[] args) {

        SMSTask smsTask = new SMSTask();
        EmailTask emailTask = new EmailTask();
        ETATask etaTask = new ETATask();

        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);
        Thread etaThread = new Thread(etaTask);

        System.out.println("Task Started.\n");

        // Start all threads
        smsThread.start();
        System.out.println("Task 1 ongoing...");

        emailThread.start();
        System.out.println("Task 2 ongoing...");

        etaThread.start();
        System.out.println("Task 3 ongoing...");

        // Wait for all threads to finish
        try {
            smsThread.join();
            emailThread.join();
            etaThread.join();
            System.out.println("All tasks completed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
