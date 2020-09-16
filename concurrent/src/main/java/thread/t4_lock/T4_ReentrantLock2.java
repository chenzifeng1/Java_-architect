package thread.t4_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: concurrent
 * @Package: thread.t4_lock
 * @ClassName: T4_ReentrantLock2
 * @Author: czf
 * @Description: 使用ReentrantLock来替代synchronized
 * synchronized是自动释放锁，当同步代码块的内容执行完成后，jvm会自动释放掉锁
 * 而ReentrantLock需要我们手动释放锁，这里注意一点，一定要将加锁写在 try{}中，
 * 释放锁写在finally{}中确保即使发送异常，也可以释放掉锁，不然锁就永远无法被其他线程获取
 * 相比synchronized，ReentrantLock有很多方法可以使用，更加方便
 * @Date: 2020/9/15 15:11
 * @Version: 1.0
 */
public class T4_ReentrantLock2 {
    Lock lock = new ReentrantLock();

    void m1() {

        /*
        这里lock.lock必须紧跟这try catch语句，
        1. 防止lock.lock与try/catch语句中间的代码抛出异常，这样锁就无释放
        2. 不能将lock.lock放在try的内部，其实如果放在第一行应该没问题，就是怕放在try中的异常代码之后，这样可能会造成对无锁对象进行解锁
         */
        lock.lock();
        try {

            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {

        } finally {
            //手动释放锁
            lock.unlock();
        }
    }

    void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
        }catch (InterruptedException e){

        }finally {
            //tryLock方法会返回是否加锁的信息，加锁成功返回true，加锁失败返回false
            if (locked){
                lock.unlock();
                System.out.println("释放锁："+lock.toString());
            }else {
                System.out.println("加锁失败，无须释放锁");
            }
        }
    }

    public static void main(String[] args) {
        T4_ReentrantLock2 t = new T4_ReentrantLock2();

        new Thread(t::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::m2).start();
    }

}
