package LLD.MultiThreadingAndConcurrency.CreatingThreads;
import java.util.*;

import java.util.concurrent.*;

// Implementing Callable to calculate ETA (only this task requires a return value)
class ETAcalculationTask implements Callable<String> {
    public String call() throws InterruptedException {
        Thread.sleep(5000); // Simulate 5-second delay for ETA calculation
        System.out.println("ETA calculated using Callable.");
        return "ETA: 25 minutes"; // Return the ETA result
    }
}

// Implementing Runnable to send SMS (no return value required)
class SmsTask implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000); // Simulate 2-second delay for SMS
            System.out.println("SMS Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Implementing Runnable to send Email (no return value required)
class Emailtask implements Runnable {
    public void run() {
        try {
            Thread.sleep(3000); // Simulate 3-second delay for Email
            System.out.println("Email Sent using Runnable.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) {
        // Create FutureTask object for ETA calculation task (since it returns a result)
        FutureTask<String> etaTask = new FutureTask<>(new ETAcalculationTask());

        // Create Runnable tasks for SMS and Email
        SMSTask smsTask = new SMSTask();
        EmailTask emailTask = new EmailTask();

        // Create Thread objects to run all tasks
        Thread etaThread = new Thread(etaTask);
        Thread smsThread = new Thread(smsTask);
        Thread emailThread = new Thread(emailTask);

        System.out.println("Task Started.\n");

        // Start all threads
        etaThread.start();
        System.out.println("Task 1 ongoing...");

        smsThread.start();
        System.out.println("Task 2 ongoing...");

        emailThread.start();
        System.out.println("Task 3 ongoing...");

        try {
            // Get the result from the FutureTask for ETA
            System.out.println((String)etaTask.get()); // Wait for ETA task to finish and print result

            // Wait for SMS and Email tasks to finish (no result needed)
            smsThread.join();
            emailThread.join();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}