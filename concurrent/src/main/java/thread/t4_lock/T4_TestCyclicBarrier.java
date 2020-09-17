package thread.t4_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ProjectName: concurrent
 * @Package: thread.t4_lock
 * @ClassName: T4_TestCyclicBarrier
 * @Author: czf
 * @Description: 相当于拦截线程的栅栏，线程来的时候阻塞线程，等线程数到达预期数时放开阻塞
 * new CyclicBarrier(20,()->{}).await();将当前线程挡住，如果放在线程run()方法外面执行，挡住的是当前住线程，且因为主线程被阻塞，循环无法继续
 * @Date: 2020/9/17 14:19
 * @Version: 1.0
 */
public class T4_TestCyclicBarrier {
    public static void main(String[] args) {
        /* CyclicBarrier 构造方法
          public CyclicBarrier(int parties, Runnable barrierAction)
        第一个参数 parties：在barrier被tripped之前一定要激活的线程的数量
        第二个参数 barrierAction 到达激活线程时 所触发的操作
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20,()->{
            System.out.println("满人，发车");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                }catch (BrokenBarrierException e){
                    System.out.println("BrokenBarrie");
                }catch (InterruptedException e){
                    System.out.println("Interrupted");
                }
            }).start();

        }
    }
}
