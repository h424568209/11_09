//懒汉模式 多线程下不安全，单线程环境下正确
public class SingLetonLazy {
    private SingLetonLazy (){}
    //可见性
    private static SingLetonLazy instance = null;
    //getInstance 被第一次调用时候，意味着有人需要instance
    //再进行初始化
    public static SingLetonLazy getInstance(){
        //原子开始
        if(instance == null){
            instance = new SingLetonLazy();
        }
        return  instance ;
        //原子结束
    }
}
