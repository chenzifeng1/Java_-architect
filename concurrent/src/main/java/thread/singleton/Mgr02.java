package thread.singleton;

/**
 * @ProjectName: concurrent
 * @Package: thread.singleton
 * @ClassName: Mgr02
 * @Author: czf
 * @Description: 线程不安全，当两个线程同时调用getInstance()时， singleton== null都会成立，则会new两个Mgr02对象
 * @Date: 2020/9/14 21:17
 * @Version: 1.0
 */
public class Mgr02 {
    private static Mgr02 singleton;

    public static Mgr02 getInstance(){
        if(singleton==null){
            singleton = new Mgr02();
        }
        return singleton;
    }
}
