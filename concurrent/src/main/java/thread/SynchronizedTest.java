package thread;

/**
 * @ProjectName: concurrent
 * @Package: thread
 * @ClassName: SynchronizedTest
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/10 21:01
 * @Version: 1.0
 */
public class SynchronizedTest implements Runnable{
    private static int count = 10;

    public synchronized static void m(){  //这里等同于 synchronized(SynchronizedTest.class)  锁是SynchronizedTest.class这个对象
        System.out.println("m start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count--;
        System.out.println(Thread.currentThread().getName()+":"+ count);
        System.out.println("m end");
    }

    public static void m2(){
        count++;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        for (int i=0 ;i<10 ; i++){
            new Thread(new SynchronizedTest(),"SynchronizedTest"+i).start();
        }

    }

    @Override
    public void run() {
        m();
        m2();
    }
}
