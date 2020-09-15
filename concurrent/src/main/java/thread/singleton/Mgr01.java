package thread.singleton;

/**
 * @ProjectName: concurrent
 * @Package: thread.singleton
 * @ClassName: Mgr01
 * @Author: czf
 * @Description: 类加载到内存后，就初始化一个实例，由JVM保证线程安全
 * 简单实用，推荐使用
 * 唯一缺点：不管用到与否，当类加载到内存中，就会初始化实例
 * @Date: 2020/9/14 21:12
 * @Version: 1.0
 */
public class Mgr01 {

    private static final Mgr01 singleton = new Mgr01();

    public static Mgr01 getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        Mgr01 m1 = getInstance();
        Mgr01 m2 = getInstance();
        System.out.println(m1 == m2);
    }


}
