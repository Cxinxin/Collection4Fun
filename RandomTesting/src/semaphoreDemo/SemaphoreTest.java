package semaphoreDemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new MyThread("thread a", semaphore);
        new MyThread("thread b", semaphore);
        new MyThread("thread c", semaphore);

       /* Lock lock=new ReentrantLock();

        new LockDemo("thread a",lock);
        new LockDemo("thread b",lock);
        new LockDemo("thread c",lock);*/

    }
}
