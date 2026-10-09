package LLD.MultiThreadingAndConcurrency.DeadlockAndPrevention;

class BankAccount{
    private final String name;
    private int balance;

    public BankAccount(String name, int balance){
        this.balance = balance;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public synchronized void deposit(int amount){
        this.balance += amount;
    }
    public synchronized void withdraw(int amount){
        if(amount > this.balance){
            System.out.println("Invalid amount");
            return;
        }
        this.balance -= amount;
    }
    public int getBalance(){
        return this.balance;
    }
}

class TransferTask implements Runnable{
    private final BankAccount from;
    private final BankAccount to;
    private  final int amount;

    public TransferTask(BankAccount from , BankAccount to , int amount){
        this.amount = amount;
        this.to = to;
        this.from = from;
    }

    @Override
    public void run(){
        synchronized (from){
            System.out.println(Thread.currentThread().getName() +
                    " locked " + from.getName());
            try { Thread.sleep(100); } catch (InterruptedException ignored) {}

            // Second lock: 'to' account - may block if another thread owns it
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() +
                        " locked " + to.getName());
                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from " +
                        from.getName() + " to " + to.getName());
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) throws InterruptedException{
        BankAccount accountA = new BankAccount("Account-A", 1000);
        BankAccount accountB = new BankAccount("Account-B", 1000);

        // T1 transfers A to B
        Thread t1 = new Thread(new TransferTask(accountA, accountB, 100), "T1");
        // T2 transfers B to A (reverse order - causes deadlock)
        Thread t2 = new Thread(new TransferTask(accountB, accountA, 200), "T2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // This line is never reached - deadlock occurred
        System.out.println("Both threads finished execution.");
    }
}
