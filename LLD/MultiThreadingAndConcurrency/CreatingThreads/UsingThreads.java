package LLD.MultiThreadingAndConcurrency.CreatingThreads;

class SMSThread extends Thread{
    public void run(){
        try{
            Thread.sleep(3000);
            System.out.println("SMS Sent using Thread.");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class EmailThread extends Thread{
    public void run(){
        try{
            Thread.sleep(2000);
            System.out.println("Email Sent using Thread.");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ETACalculationThread extends Thread{
    public void run(){
        try{
            Thread.sleep(5000);
            System.out.println("ETA Calculated using Thread. Estimated Time: 25 minutes.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class UsingThreads {
    public static void main(String[] args) {

        SMSThread smsThread = new SMSThread();
        EmailThread emailThread = new EmailThread();
        ETACalculationThread etaCalculationThread = new ETACalculationThread();

        System.out.println("Task Started.\n");

        smsThread.start();
        System.out.println("Task 1 ongoing...");

        emailThread.start();
        System.out.println("Task 2 ongoing...");

        etaCalculationThread.start();
        System.out.println("Task 3 ongoing...");

        try{
            smsThread.join();
            emailThread.join();
            etaCalculationThread.join();
            System.out.println("All tasks completed.");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

