/**
 * 问题：启动3个线程A、B、C，使A打印0，然后B打印1，然后C打印2，A打印3，B打印4，C打印5，依次类推。
 思路：每个线程给定一个编号，每个线程判断当前是否已经轮到自己打印：如果没轮到，就wait等待；如果轮到，则打印当前数字，并唤醒其他线程。

 判断是否已经轮到自己打印：
 每个线程给定一个编号N，N从0开始；
 使用一个全局变量记录当前需要打印的数字C，C从0开始，每次打印之后加1；
 线程数量M；
 判断逻辑：C%M ==N
**/

public class PrintSequenceThread implements Runnable {

    private static final Object LOCK = new Object();

    /**
     * 当前即将打印的数字
     */
    private static int current = 0;

    /**
     * 当前线程编号，从0开始
     */
    private int threadNo;

    /**
     * 线程数量
     */
    private int threadCount;

    /**
     * 打印的最大数值
     */
    private int max;

    public PrintSequenceThread(int threadNo, int threadCount, int max) {
        this.threadNo = threadNo;
        this.threadCount = threadCount;
        this.max = max;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (LOCK) {
                // 判断是否轮到当前线程执行
                while (current % threadCount != threadNo) {
                    if (current > max) {
                        break;
                    }
                    try {
                        // 如果不是，则当前线程进入wait
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 最大值跳出循环
                if (current > max) {
                    break;
                }
                System.out.println("thread-" + threadNo + " : " + current);
                current++;
                // 唤醒其他wait线程
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        int threadCount = 3;
        int max = 10;
        for(int i=0;i<threadCount;i++) {
            new Thread(new PrintSequenceThread(i,threadCount, max)).start();
        }
    }

}
