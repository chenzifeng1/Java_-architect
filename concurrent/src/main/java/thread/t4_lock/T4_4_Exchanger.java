package thread.t4_lock;

import java.util.concurrent.Exchanger;

/**
 * @author chenzifeng
 * @Package thread.t4_lock
 * @Description: Exchange 线程之间交换数据
 * 一个线程执行到exchange的时候，被阻塞，等待下一个线程来执行exchange交换数据
 * 只能发生在两个线程之间
 * @date 2020/9/2222:02
 */
public class T4_4_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread( ()->{
            String s = "T1";
            try {
                s =exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" s:"+s);
        },"T1").start();

        new Thread( ()->{
            String s = "T2";
            try {
                s =exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" s:"+s);
        },"T2").start();

    }
}
