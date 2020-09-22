package thread.t4_lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenzifeng
 * @Title:
 * @Package thread.t4_lock
 * @Description:
 * @date 2020/9/2221:15
 */
public class T4_2_ReadWriteLock {

     private static Lock lock = new ReentrantLock();
     private static int value ;

     private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
     private static Lock readLock = readWriteLock.readLock();
     private static Lock writeLock = readWriteLock.writeLock();

     private static void secondSleep(int seconds){
         try {
             TimeUnit.SECONDS.sleep(seconds);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }

     public static void write(Lock lock, int v){
         lock.lock();
         try {
             System.out.println(Thread.currentThread().getName()+" 开始写 ");
             secondSleep(2);
             value = v;
             System.out.println("写操作完毕：value= "+ value);
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }

     public static void read(Lock lock){
         lock.lock();
         try {
             System.out.println(Thread.currentThread().getName()+" 读操作开始");
             secondSleep(1);
             System.out.println("读value："+value);
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             lock.unlock();
         }
     }

    public static void main(String[] args) {
//        Runnable readR = ()->read(readLock);
        Runnable readR = ()->read(lock);


//        Runnable writeR = ()->write(writeLock,new Random().nextInt(300));
        Runnable writeR = ()->write(lock,new Random().nextInt(300));

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }
}
