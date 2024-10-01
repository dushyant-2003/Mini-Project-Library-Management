package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class BankAccount1 {
    public double balance;
   ReentrantLock lock = new ReentrantLock();

    public BankAccount1(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit money into the account
    public void deposit(double amount) {
        lock.lock();  // Acquire the lock
        try {
            System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited. New balance: " + balance);
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    // Withdraw money from the account
    public void withdraw(double amount) {
        lock.lock();  // Acquire the lock
        try {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew. New balance: " + balance);
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    // Transfer money from one account to another
    public void transfer(BankAccount1 toAccount, double amount) {
        // Acquiring lock in this method, then deposit is reentrant
        lock.lock();  // Acquire the lock for the 'from' account
        try {
            System.out.println(Thread.currentThread().getName() + " is transferring " + amount + " to another account.");
            if (this.balance >= amount) {
                this.withdraw(amount);  // Withdraw from the 'from' account
                toAccount.deposit(amount);  // Deposit into the 'to' account
                System.out.println(Thread.currentThread().getName() + " transfer complete.");
            } else {
                System.out.println(Thread.currentThread().getName() + ": Insufficient funds to transfer.");
            }
        } finally {
            lock.unlock();  // Release the lock for the 'from' account
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        BankAccount1 accountA = new BankAccount1(1000);
        BankAccount1 accountB = new BankAccount1(500);

        // Create threads to perform transfers and deposits
        Thread t1 = new Thread(() -> accountA.transfer(accountB, 200), "Thread 1");
        Thread t2 = new Thread(() -> accountA.deposit(300), "Thread 2");
        Thread t3 = new Thread(() -> accountB.transfer(accountA, 100), "Thread 3");

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance in Account A: " + accountA.balance);
        System.out.println("Final balance in Account B: " + accountB.balance);
    }
}

