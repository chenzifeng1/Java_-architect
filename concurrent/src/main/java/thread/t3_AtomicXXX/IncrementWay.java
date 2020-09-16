package thread.t3_AtomicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @ProjectName: concurrent
 * @Package: thread.t3_AtomicXXX
 * @ClassName: IncrementWay
 * @Author: czf
 * @Description: 实现高并发下的递增 三种方式粗暴的测试
 * Synchronized：加锁方式，且在高并发情况，锁必会升级为重量级锁
 * AtomicLong: 采用CAS方式，使用乐观锁
 * LongAdder： 采用分段式锁，给并发线程分段加锁
 * @Date: 2020/9/15 14:11
 * @Version: 1.0
 */
public class IncrementWay {
    private long count1 = 0;
    private AtomicLong count2 = new AtomicLong(0);
    private LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        IncrementWay iw = new IncrementWay();
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        synchronized (lock) {
                            iw.count1++;
                        }
                    }
                }
            });
        }

        long start = System.currentTimeMillis();
        for (Thread i : threads) {
            i.start();
        }
        for (Thread i : threads) {
            i.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("synchronized: count " + iw.count1 + "  time :" + (end - start) );


            /*-------------------------------- AtomicLong  ---------------------------------------*/
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        iw.count2.incrementAndGet();
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread i : threads) {
            i.start();
        }
        for (Thread i : threads) {
            i.join();
        }
         end = System.currentTimeMillis();
        System.out.println("Atomic: count " + iw.count1 + "  time :" + (end - start));
        /*-------------------------------- LongAdder  ---------------------------------------*/
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        iw.count3.increment();
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread i : threads) {
            i.start();
        }
        for (Thread i : threads) {
            i.join();
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder: count " + iw.count1 + "  time :" + (end - start) );


    }

}
