package threadPac;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLatch {

    public static void main(String args[]){

        ExecutorService executorService= Executors.newCachedThreadPool();

        CountDownLatch latch=new CountDownLatch(3);

        Work w1=new Work(latch,"Andy");
        Work w2=new Work(latch,"Nick");
        Work w3=new Work(latch,"Danny");

        Boss boss=new Boss(latch);

        executorService.execute(w3);
        executorService.execute(w2);
        executorService.execute(w1);
        executorService.execute(boss);

        executorService.shutdown();

    }


    //https://blog.csdn.net/yujin753/article/details/46125283
}
