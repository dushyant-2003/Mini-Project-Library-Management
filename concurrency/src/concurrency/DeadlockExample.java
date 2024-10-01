package concurrency;

public class DeadlockExample {

    // Shared resources
    private final Object resource1 = new Object();
    private final Object resource2 = new Object();

    public static void main(String[] args) {
        DeadlockExample deadlockSolution = new DeadlockExample();

        // Thread A tries to lock resources in a consistent order (resource1 -> resource2)
        Thread threadA = new Thread(() -> {
            synchronized (deadlockSolution.resource1) {
                System.out.println("Thread A: Holding lock on Resource 1...");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread A: Waiting for lock on Resource 2...");
                synchronized (deadlockSolution.resource2) {
                    System.out.println("Thread A: Acquired lock on Resource 2...");
                }
            }
        });

        // Thread B also locks resources in the same order (resource1 -> resource2)
        Thread threadB = new Thread(() -> {
            synchronized (deadlockSolution.resource2)
            {
                System.out.println("Thread B: Holding lock on Resource 1...");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread B: Waiting for lock on Resource 2...");
                synchronized (deadlockSolution.resource1) {
                    System.out.println("Thread B: Acquired lock on Resource 2...");
                }
            }
        });

        threadA.start();
        
        try {
			threadA.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        threadB.start();
    }
}

