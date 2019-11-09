//饿汉模式
public class SingLeton {

    private  SingLeton(){}
    private static final SingLeton instance = new SingLeton();
    public static SingLeton getInstance(){
        return instance;
    }
}
