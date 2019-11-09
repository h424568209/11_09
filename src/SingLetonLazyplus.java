//保证懒汉模式下的线程安全
public class SingLetonLazyplus {

    private SingLetonLazyplus(){}
    private SingLetonLazyplus instance = null;
    public synchronized SingLetonLazyplus getInstance(){
        if(instance == null){
            instance = new SingLetonLazyplus();
        }
        return instance;
    }
}
