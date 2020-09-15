package thread.sync_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: concurrent
 * @Package: thread.singleton
 * @ClassName: FineCoarseLock
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/15 10:39
 * @Version: 1.0
 */
public class FineCoarseLock {
    int count = 0;

    /**
     * 如果我们给整个方法加锁，实际需要加锁的代码只有count++
     */
    public synchronized void m1() {
        try {
            //代表需要加锁代码前面的业务代码
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;

        try {
            //代表需要加锁代码后面的业务代码
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 细化后的锁的优点：其他线程可以先执行其他业务代码，当执行到count++时等待锁释放，然后获取锁就行了。获取锁的线程执行完count++就释放锁
     * 这样其他线程可以更快的拿到锁来执行
     */
    public void m2() {
        try {
            //代表需要加锁代码前面的业务代码
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (FineCoarseLock.class){
            count++;
        }
        try {
            //代表需要加锁代码后面的业务代码
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
