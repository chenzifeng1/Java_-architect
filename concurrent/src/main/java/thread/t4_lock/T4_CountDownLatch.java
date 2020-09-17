package thread.t4_lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: concurrent
 * @Package: thread.t4_lock
 * @ClassName: T4_CountDownLatch
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/17 13:38
 * @Version: 1.0
 */
public class T4_CountDownLatch {
    private static final int length = 100;

    Thread[] threads = new Thread[length];

    
    CountDownLatch latch = new CountDownLatch(length);


    public void usinCountDownLatch(){
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每调用一次countDown方法，门闩就-1
                latch.countDown();
            });
        }
        //此处开始启动线程
        for (int i = 0; i < length; i++) {
            threads[i].start();
        }
        try {
            latch.await();
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }

    }
}
