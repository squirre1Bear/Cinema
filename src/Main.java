import java.util.*;
import DAO.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);   //实例化Scanner
        Initialization chuShiHua=new Initialization(sc);   //调用ChuShiHua（初始化）类构造方法，传入sc，完成系统初始化
        System.out.println("欢迎来到信院影城！");

        while(true) {
            chuShiHua.showMenu();  //显示菜单
            System.out.print("\n<菜单>请输入您要使用功能的数字：\n>>");
            int i = 1;
            int xuanZe = sc.nextInt();    //记录用户的选择
            if(xuanZe==3)
                break;
            for (AbstractFunction fangFa : chuShiHua.itemName) {    //搜索用户输入的功能
                if (i == xuanZe) {
                    fangFa.run();
                    break;
                }
                i++;
            }
        }

        System.out.println("感谢您选择信院影城，下次见！");
    }
}
