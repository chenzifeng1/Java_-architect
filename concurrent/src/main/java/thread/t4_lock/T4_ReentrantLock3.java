package thread.t4_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: concurrent
 * @Package: thread.t4_lock
 * @ClassName: T4_ReentrantLock3
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/15 19:44
 * @Version: 1.0
 */
public class T4_ReentrantLock3 {
    public static void main(String[] args)  {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{

            try {
                //t2这里加的锁是可以被打断的
                lock.lockInterruptibly();
                System.out.println("t2 start ...");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end ...");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }finally {
                //要做一下加锁判断
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

    }
}
