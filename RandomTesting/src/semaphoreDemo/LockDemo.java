package semaphoreDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo implements Runnable {
    String name;
    Lock lock=null;

    public LockDemo(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
        new Thread(this).start();
    }

    @Override
    public void run() {

        System.out.println(name + "想要获取资源");

        lock.lock();

        System.out.println(name + "get a permit lar!");

        for(int i=0;i<5;i++){
            Shared.resources++;
            System.out.println("shared resources now is " + Shared.resources + ", by " + name);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        lock.unlock();
        System.out.println(name + "release a permit");
    }
}
