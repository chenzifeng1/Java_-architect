package thread.sync_volatile;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: concurrent
 * @Package: thread.singleton
 * @ClassName: VolatileTest
 * @Author: czf
 * @Description:
 * volatile作用：
 *  1. 保证线程可见性
 *  2. 防止指令重排序
 *  但是volatile不能保证原子性，不能代替synchronized
 * @Date: 2020/9/15 10:10
 * @Version: 1.0
 */
public class VolatileTest {
    private   volatile int count = 0;
    public /*synchronized*/ void m(){
        for(int i=0;i<10000;i++){
            //count++在汇编层会有很多条指令，volatile不能保证这些指令原子性执行，因此很有可能出现多个线程在指令执行的不同阶段对m进行写操作

            count++;
        }
    }

    public static void main(String[] args) {
        VolatileTest vt = new VolatileTest();
        List<Thread> lists = new LinkedList<>();
        for (int i =0;i<10 ;i++){
            lists.add( new Thread(vt::m,"T-"+i)) ;
        }
        lists.forEach((o)->{
            o.start();
        });
        lists.forEach((o)->{
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(vt.count);
    }
}
