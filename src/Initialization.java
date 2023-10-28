import java.util.*;

public class Initialization{    //初始化
    Scanner sc=null;
    List<AbstractFunction> itemName =new ArrayList<AbstractFunction>();   //项目名称，里面存放所有操作名称
    public Initialization(Scanner sc){   //构造方法中传入scanner，再把各项目名称存到List类型变量里面
        this.sc=sc;

        this.itemName.add(new Registion(this.sc));   //注册选项
        this.itemName.add(new LogIn(this.sc));   //登录选项
        this.itemName.add(new Exit(this.sc));   //退出选项
    }
    public boolean showMenu(){
        System.out.println("*******菜*****单*******");
        int i=1;
        for(AbstractFunction fangFa:itemName){
            System.out.print(i+"."+fangFa.getName()+" ");
            i++;
        }
        return true;
    }
}