import DAO.DAOImp;
import DAO.User;
import DAO.UserService;

import java.util.*;

class Registion extends AbstractFunction {
    Scanner sc=null;
    String ID=null;   //记录输入的账号
    String passWord1=null;    //第一次输入的密码
    String passWord2=null;    //第二次输入的密码

    public Registion(Scanner sc){
        this.sc=sc;
    }

    DAOImp daoimp=new DAOImp();
    public void run() {
        boolean flag=false;
        System.out.print("<注册> 账号不少于5个字符，请输入账号：");
        sc.nextLine();
        ID=sc.nextLine();
        if(ID.length()<5){
            System.out.println("账号长度不能少于5个字符!");
            flag=true;
        }
        for(int i=0;i<daoimp.length;i++){
            if(ID.equals(daoimp.getUser(i).ID)){
                System.out.println("该账号已被使用，请重新输入！");
                flag=true;
            }
        }
        a:while(flag){
            System.out.print("<注册> 账号不少于5个字符，请输入账号：");
            ID=sc.nextLine();
            if(ID.length()<5){
                System.out.println("账号长度不能少于5个字符!");
                continue;
            }
            for(int i=0;i<daoimp.length;i++){
                if(ID.equals(daoimp.getUser(i).ID)){
                    System.out.println("该账号已被使用，请重新输入！");
                    continue a;   //直接进行下一次外层循环
                }
            }
            flag=false;
        }

        flag=true;
        a:while(flag){
            System.out.print("<注册> 密码至少为8位，且为大小字母、数字、特殊符号组合:");
            passWord1=sc.nextLine();
            if(passWord1.length()<8){
                System.out.println("密码长度至少为8位，请重新输入！");
                continue ;
            }

            boolean contains_Num_Words_Spec=false;
            contains_Num_Words_Spec=passWord1.matches(".*[a-zA-Z].*") && passWord1.matches(".*\\d.*") && passWord1.matches(".*[^a-zA-Z0-9].*");
            if(!contains_Num_Words_Spec){
                System.out.println("密码应为大小字母、数字、特殊符号组合，请重新输入！");
                continue;
            }
            flag=false;
        }

        System.out.print("<注册> 请确认密码：");
        passWord2=sc.nextLine();
        if(passWord2.equals(passWord1)){
            System.out.print("<注册> 请输入用户名：");
            String name=sc.nextLine();
            System.out.print("<注册> 请输入手机号：");
            String phoneNumber=sc.nextLine();
            System.out.print("<注册> 请输入邮箱：");
            String address=sc.nextLine();
            User newUser=new User();
            newUser.ID=ID;
            newUser.password=passWord1;
            newUser.userName=name;
            newUser.phoneNumber=phoneNumber;
            newUser.address=address;
            newUser.identity="customer";

            Date currentDate = new Date();       //获取注册时候的日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String date=year+"年"+month+"月"+day+"日";
            System.out.print(date+"前面是时间1");
            newUser.registration_time=date;
            newUser.consumption=0;

            UserService.daoimp.addMember(newUser);
            System.out.println("注册成功！");
        }
        else{
            System.out.println("<注册> 两次密码不一致！\n");
        }
        return ;
    }

    public String getName() {
        return "<注册>";
    }
}