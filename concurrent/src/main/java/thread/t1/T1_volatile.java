package thread.t1;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: concurrent
 * @Package: thread.t1
 * @ClassName: T1_volatile
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/14 20:49
 * @Version: 1.0
 */
public class T1_volatile {
    private /*volatile*/ Boolean running = true;

    public void m() {
        System.out.println("t1 start");
        while (running){

        }
        System.out.println("t1 end");
    }

    public static void main(String[] args) {
        T1_volatile t = new T1_volatile();
        new Thread(t::m, "t1").start();
        /* lambda表达式等价于下面的写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        }).start();
        */
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
