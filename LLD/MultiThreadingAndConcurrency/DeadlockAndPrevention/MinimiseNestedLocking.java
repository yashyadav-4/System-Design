package LLD.MultiThreadingAndConcurrency.DeadlockAndPrevention;

public class MinimiseNestedLocking {

    static class SharedResource {
        private final Object lock = new Object();
        private int data;

        public void update(int value) {
            synchronized (lock) {
                data = value;
                System.out.println(Thread.currentThread().getName() +
                        " updated data to " + value);
            }
        }

        public int read() {
            synchronized (lock) { return data; }
        }
    }

    public static void main(String[] args) {
        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        // Thread 1 does operations separately to avoid nested locking
        Runnable task1 = () -> {
            resource1.update(100);  // Lock 1 used and released quickly
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            resource2.update(200);  // Lock 2 used separately
        };

        // Thread 2 also performs updates separately
        Runnable task2 = () -> {
            resource2.update(300);
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            resource1.update(400);
        };

        new Thread(task1, "T1").start();
        new Thread(task2, "T2").start();
    }
}
