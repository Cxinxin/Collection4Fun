import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2个多线程交替打印1-100内数字,其中t1线程打印奇数,t2程打印偶数
 * 用Condition的解法
 */
public class OddAndEventUseCondition {

    public static  class PrintAB{
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        private volatile int state=0;

        /**
         * 打印奇数
         */
        public void print1() {
            print(1, c1, c2);
        }

        /**
         * 打印偶数
         */
        public void print2() {
            print(0, c2, c1);
        }

        public void print(int flag,Condition cuerrentConditon,Condition nextCondition){
           for(;;){
               try{
                   lock.lock();
                   //状态判断
                   while (state %2 ==flag){
                        cuerrentConditon.await();
                   }
                   if (state > 100){
                       return;
                   }

                   System.out.println(Thread.currentThread().getName()+": "+state++);
                   nextCondition.signal();

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                   lock.unlock();
               }
           }
       }
    }


    public static void main(String args[]){
        PrintAB printAB=new PrintAB();
        new Thread(() -> printAB.print1()).start();
        new Thread(() -> printAB.print2()).start();
    }

}
