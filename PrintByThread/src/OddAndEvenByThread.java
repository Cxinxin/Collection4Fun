public class OddAndEvenByThread {


    //奇数为false ,偶数为true
    public boolean flag;


    /**
     * 打印奇数的线程
     */
    public class OddThread implements Runnable{

        public OddAndEvenByThread oddAndEvenByThread;


        public OddThread(OddAndEvenByThread oddAndEvenByThread){
            this.oddAndEvenByThread=oddAndEvenByThread;
        }

        @Override
        public void run() {

            //本线程打印奇数,则从1开始
            int i = 1;
            while (i < 100) {

                //两个线程的锁的对象只能是同一个object
                synchronized (oddAndEvenByThread) {

                    if (!oddAndEvenByThread.flag) {
                        System.out.println("-----" + i);

                        i += 2;
                        oddAndEvenByThread.flag = true;
                        oddAndEvenByThread.notify();

                    } else {
                        try {
                            oddAndEvenByThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
    }


    /**
     * 打印偶数的线程
     */
    public class EvenThread implements Runnable{
        public OddAndEvenByThread oddAndEvenByThread;

        public EvenThread(OddAndEvenByThread oddAndEvenByThread){
            this.oddAndEvenByThread = oddAndEvenByThread;
        }

        @Override
        public void run() {
            int i=2;
            while (i<=100){
                synchronized (oddAndEvenByThread){
                    if(oddAndEvenByThread.flag){
                        System.out.println("-----------" + i);
                        i+=2;
                        flag=false;
                        oddAndEvenByThread.notify();

                    }else {
                        try {
                            oddAndEvenByThread.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        OddAndEvenByThread oddAndEvenByThread=new OddAndEvenByThread();
        new Thread(oddAndEvenByThread.new OddThread(oddAndEvenByThread)).start();
        new Thread(oddAndEvenByThread.new EvenThread(oddAndEvenByThread)).start();
    }
}
