package concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;



public class dd {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Creating threads for deposit and withdrawal
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(1);
            }
        });

        // Start both threads
        t1.start();
        t2.start();

         
        try {
            t1.join();  // Wait for t1 to complete
            t2.join();  // Wait for t2 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Now, both threads are finished, and we can safely print the final balance
        System.out.println("Final balance: " + account.getBalance());
    }
}
