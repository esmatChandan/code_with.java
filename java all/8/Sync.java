class PrintDemo {
    public void printCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   " + i);
            }
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

class ThreadA extends Thread {
    private Thread t;
    private String threadName;
    PrintDemo PD;

    ThreadA(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized (PD) {
            PD.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class Sync {

    public static void main(String args[]) {
        PrintDemo PD = new PrintDemo();

        ThreadA T1 = new ThreadA("Thread - 1 ", PD);
        ThreadA T2 = new ThreadA("Thread - 2 ", PD);

        T1.start();
        T2.start();

        try {
            T1.join();
            T2.join();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
}