package thread.t3_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: concurrent
 * @Package: thread.t3_AtomicXXX
 * @ClassName: T01_AtomicInteger
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/15 10:52
 * @Version: 1.0
 */
public class T01_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);

    public void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "T-" + i));
        }

        threads.forEach((o) -> {
            o.start();
        });
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
