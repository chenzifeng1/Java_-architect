package thread;

/**
 * @ProjectName: concurrent
 * @Package: thread
 * @ClassName: T0_HowToCreateThread
 * @Author: czf
 * @Description: 两种常见的线程实现方式
 * 1. 继承Thread类 重写run方法
 * 2. 实现Runnable接口 重写run方法
 * 但是两者调用时略有不同:
 * 继承Thread类的线程调用时直接调用start()即可
 * 实现Runnable接口的线程调用时，需要将对象作为构造函数的参数传入Thread的对象中
 * @Date: 2020/9/10 18:32
 * @Version: 1.0
 */
public class T0_HowToCreateThread {
    static class T1 extends Thread {
        static int i = 0;
        @Override
        public void run() {
            System.out.println("这里是线程1:" +i++);
        }
    }

    static class T2 implements Runnable {
        static int i = 0;
        @Override
        public void run() {
            System.out.println("这里是线程2:"+i++);
        }
    }

    public static void main(String[] args) {
        testJoin();
    }

    static void testSleep(int mills){
        new Thread(()->{
            try {
                for (int i =0;i<100;i++){
                    if (i%5==0){
                        System.out.println(Thread.currentThread().getName()+"：sleep before");
                        Thread.sleep(100);
                        System.out.println(Thread.currentThread().getName()+"：sleep after");
                    }
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }

    static void testYield(){
        new Thread(()->{
                for (int i =0;i<100;i++){
                    if (i%5==0){
                        System.out.println(Thread.currentThread().getName()+"：yield before");
                        Thread.yield();
                        System.out.println(Thread.currentThread().getName()+"：yield after");
                    }
                }
        }).start();
    }

    static void testJoin(){
       Thread t1 =  new Thread(()->{
           System.out.println("t1");
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
        });

        Thread t2 =  new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"：t2 join before");
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"：t2 join after");
        });
        t2.start();
        t1.start();
    }

}
