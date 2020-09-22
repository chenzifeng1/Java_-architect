package thread.t4_lock;

import java.util.concurrent.Semaphore;

/**
 * @author chenzifeng
 * @Title:
 * @Package thread.t4_lock
 * @Description:
 * 信号量
 * 场景：限流 最终允许多少个线程执行
 * 相比于线程池来说，线程池是每个时间只有core个线程，
 * 而semaphore是同时可以有多个线程，但是只有n个线程可以获得许可 这个是来做线程之间的同步
 * @date 2020/9/2221:43
 */
public class T4_3_Semaphore {
    public static void main(String[] args) {
        //构造函数参数：允许的数量
//        Semaphore semaphore = new Semaphore(2);
        Semaphore semaphore = new Semaphore(2,true);

        new Thread( ()->{
            try {
                //取得一个信号量，每取一次，允许的数量就减一，到0的时候，再有线程来取就无法取得了
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+ " is running");
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName()+ " is running ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放掉取得的信号量
                semaphore.release();
            }
        }).start();

        new Thread( ()->{
            try {
                //取得一个信号量，每取一次，允许的数量就减一，到0的时候，再有线程来取就无法取得了
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+ " is running");
                Thread.sleep(300);
                System.out.println(Thread.currentThread().getName()+ " is running ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放掉取得的信号量
                semaphore.release();
            }
        }).start();
    }
}
