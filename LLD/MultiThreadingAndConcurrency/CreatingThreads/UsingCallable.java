// with callable u can return data in run method

package LLD.MultiThreadingAndConcurrency.CreatingThreads;

import java.util.concurrent.*;

class ETACalculationTask implements Callable<String> {
    public String call() throws InterruptedException{
        Thread.sleep(5000);
        System.out.println("Calculation ETA using Callable.");
        return "ETA: 25 minutes";
    }
}

class SMStask implements Runnable{
    public void run (){
        try{
            Thread.sleep(2000);
            System.out.println("SMS Sent using Runnable.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class EMailtask implements Runnable{
    public void run (){
        try{
            Thread.sleep(3000);
            System.out.println("Email Sent using Runnable.");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class UsingCallable {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        SMStask smStask = new SMStask();
        EMailtask eMailtask = new EMailtask();
        ETACalculationTask  etaCalculationTask = new ETACalculationTask();

        Future<String> etaResult = executorService.submit(etaCalculationTask);

        executorService.submit(smStask);
        executorService.submit(eMailtask);

        try{
            System.out.println(etaResult.get());
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("Task failed with exception: " + e.getCause());
        } finally {
            // Always shut down executor pool to prevent resource leaks
            executorService.shutdown();
        }
    }
}
