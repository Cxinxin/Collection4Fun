//一个关于线程的经典面试题，要求用三个线程，按顺序打印1,2,3,4,5.... 71,72,73,74, 75.
//线程1先打印1,2,3,4,5, 然后是线程2打印6,7,8,9,10, 然后是线程3打印11,12,13,14,15.
//接着再由线程1打印16,17,18,19,20....以此类推, 直到线程3打印到75。

public class PrintBlockByThread implements Runnable {

    private static final Object LOCK = new Object();

   static int currentNumber = 1;

    int threadID;

    int limitNumber = 75;

    public PrintBlockByThread(int threadID) {
        this.threadID = threadID;
    }


    @Override
    public void run() {
        synchronized (LOCK) {
            while (currentNumber < limitNumber) {
                if (currentNumber / 5 % 3 == threadID) {
                    System.out.print("Thread " + threadID+": ");
                    for (int j = 0; j < 5; j++) {
                        System.out.print(currentNumber++ + ",");
                    }
                        System.out.println();
                        LOCK.notifyAll();

                }else {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        int threadAmount = 3;
        for (int i = 0; i < threadAmount; i++) {
            new Thread(new PrintBlockByThread(i)).start();
        }
    }
}
