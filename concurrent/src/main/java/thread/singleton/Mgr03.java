package thread.singleton;

/**
 * @ProjectName: concurrent
 * @Package: thread.singleton
 * @ClassName: Mgr03
 * @Author: czf
     * @Description: ${description}
 * @Date: 2020/9/14 21:23
 * @Version: 1.0
 */
public class Mgr03 {
    private static volatile Mgr03 singleton;

    public static Mgr03 getInstance(){
        /*第一次校验 但是这里可能会多个线程同时判为true
            之所以加第一次判断，因为多数情况下singleton都已经被初始化了，所以省略加锁会提高效率
        */
        if (singleton == null){
            synchronized (Mgr03.class){
                //第二次校验 这里由于是在同步代码区，所以每次只能由一个线程来访问
                if(singleton == null){
                    singleton = new Mgr03();
                }
            }
        }
        return singleton;
    }
}
