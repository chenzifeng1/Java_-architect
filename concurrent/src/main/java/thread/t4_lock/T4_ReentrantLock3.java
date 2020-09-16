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
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

//        Thread
    }
}
