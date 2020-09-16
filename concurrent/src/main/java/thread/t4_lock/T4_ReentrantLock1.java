package thread.t4_lock;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: concurrent
 * @Package: thread.t4_lock
 * @ClassName: T4_ReentrantLock1
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/15 14:36
 * @Version: 1.0
 */
public class T4_ReentrantLock1 {
    /**
     * 由于是同步方法，线程需要持有类对象的锁时才能访问
     */
    public synchronized  void m1(){
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            //这里m1,m2在同一个线程中执行，虽然是两个synchronized方法，但是由于sync锁是可重入的，因此可以执行m2方法
            if(i == 2 ){
                m2();
            }

        }
    }
    /**
     * 由于是同步方法，线程需要持有类对象的锁时才能访问,故m1与m2持有的是同一把锁，会出现锁竞争
     */
    public synchronized void m2(){
        System.out.println("m2 start ...");
    }

    public static void main(String[] args) {
        T4_ReentrantLock1 tr = new T4_ReentrantLock1();
        //第一个线程启动后的一秒钟之后，第二个线程也启动了，但是由于两个方法需要同一把锁，而线程一首先获得了锁，因此线程二即使启动也无法执行m2方法
        new Thread(tr::m1).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(tr::m2).start();


    }
}
