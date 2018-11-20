package semaphoreDemo;

import java.util.concurrent.Semaphore;


/**
 * 假设有一个全局范围内的数字作为共享资源，
 * 有三个线程需要访问这个数字来做一个处理，
 * 每次处理的时间比较长
 * 并且并发运行的话可能会影响最终的处理结果，
 * 所以要通过Semaphore来阻止并发
 */
public class MyThread implements Runnable {
    String name;
    Semaphore semaphore;

    public MyThread(String name, Semaphore semaphore) {
        this.name = name;
        this.semaphore = semaphore;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println(name + " 想要获取资源");
        try{
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " get a permit lar!");

        for(int i=0;i<5;i++){

            Shared.resources++;
            System.out.println("shared resources now is " + Shared.resources + ", by " + name);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

        System.out.println(name + " release a permit");
        semaphore.release();


    }

}
//https://blog.csdn.net/daguanjia11/article/details/78828009