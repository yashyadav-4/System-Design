package LLD.MultiThreadingAndConcurrency.ThreadSafety;

class Counter {
    volatile int count = 0;

    public void increment() {
        count++; // Still unsafe!
    }
}

public class Volatile {
    public static void main(String[] args) {

    }
}
