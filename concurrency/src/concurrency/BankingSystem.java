package concurrency;

import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

class BankAccount {
    private double balance;
    private final StampedLock l = new StampedLock();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    // Deposit money (write operation)
    public void deposit(double amount) {
        lock.writeLock().lock();  // Acquiring the write lock
        try {
            System.out.println(Thread.currentThread().getName() + " is depositing: " + amount);
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
        } finally {
            lock.writeLock().unlock();  // Releasing the write lock
        }
    }
    
    // Withdraw money (write operation)
    public void withdraw(double amount) {
        lock.writeLock().lock();  // Acquiring the write lock
        try {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing: " + amount);
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " new balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient balance for withdrawal.");
            }
        } finally {
            lock.writeLock().unlock();  // Releasing the write lock
        }
    }
    
    // Check balance (read operation)
    public double getBalance() {
        lock.readLock().lock();  // Acquiring the read lock
        try {
            System.out.println(Thread.currentThread().getName() + " is reading balance: " + balance);
            return balance;
        } finally {
            lock.readLock().unlock();  // Releasing the read lock
        }
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Create some threads to perform read and write operations
        Thread t1 = new Thread(() -> account.deposit(200), "Thread 1");
        Thread t2 = new Thread(() -> account.withdraw(150), "Thread 2");
        Thread t3 = new Thread(() -> account.getBalance(), "Thread 3");
        Thread t4 = new Thread(() -> account.getBalance(), "Thread 4");
// Start the threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
