package LLD.MultiThreadingAndConcurrency.ThreadSafety;


import java.util.concurrent.atomic.AtomicInteger;

class PurchaseAtomicCounter {

    // a thread-safe integer backed by hardware-level CAS
    private final AtomicInteger likes = new AtomicInteger(0);

    // atomically add 1 to the like counter
    public void incrementLikes() {
        int prev, next;
        do {

            // Step 1  – read the current value.
            //           (May be outdated if another thread wins the race.)
            prev = likes.get();

            // Step 2  – compute the desired next value.
            next = prev + 1;

            // Step 3  – attempt to swap:
            /*          “If current value is still ‘prev’, set it to ‘next’.”
             *          Returns true on success, false if another thread
             *          already changed the value (retry needed).
             */
        } while (!likes.compareAndSet(prev, next));
    }

    // Read-only accessor
    public int getCount() {
        return likes.get();
    }
}

public class Atomic {
    public static void main(String[] args) throws InterruptedException{
        PurchaseAtomicCounter counter = new PurchaseAtomicCounter();

        Runnable task = ()->{
          for(int i = 0; i < 1000; i++){
              counter.incrementLikes();
          }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
