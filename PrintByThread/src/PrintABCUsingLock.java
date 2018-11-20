import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程分别打印A，B，C，要求这三个线程一起运行，打印n次，输出形如“ABCABCABC....”的字符串。
 */
public class PrintABCUsingLock {
    private int printTimes;
    private int state;
    private Lock lock=new ReentrantLock();


    public PrintABCUsingLock(int printTimes) {
        this.printTimes = printTimes;
    }

   public static void main(String args[]){
        PrintABCUsingLock printABCUsingLock=new PrintABCUsingLock(10);
        new Thread(printABCUsingLock::printA).start();
        new Thread(printABCUsingLock::printB).start();
        new Thread(printABCUsingLock::printC).start();
   }

   public void printA(){
       print("A",0);
   }
    public void printB(){
        print("B",1);
    }
    public void printC(){
        print("C",2);
    }


   private void print(String name,int targetStatus){

       for(int i=0;i<printTimes;){
           lock.lock();
           if(state %3 == targetStatus){
               state++;
               i++;
               System.out.print(name+" ");
               //System.out.println("Thread :"+name+" and number is "+i);
           }
           lock.unlock();

       }
   }

}
