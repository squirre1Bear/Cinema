package Identity;
import DAO.*;
import java.util.*;
import static DAO.UserService.daoimp;

public class Administrator {
    User user;
    Scanner sc = new Scanner(System.in);

    public Administrator(User user) {
        this.user = user;
    }

    public void Admin_Menu(int i) {
        System.out.print("用户名:" + this.user.userName);
        System.out.println("; 身份:管理员;");
        System.out.print("请选择：1.<密码管理>  2.<用户管理> 3.<退出登录>\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                if (!Admin_PassWord_Management(i)) {    //当选择退出的时候进入if语句
                    return;
                }
                pressEnterToContinue();
                Admin_Menu(i);    //选择其他选项的时候继续调用菜单
            case 2:
                Admin_User_Management();
                pressEnterToContinue();
                Admin_Menu(i);
            case 3:
                return ;
        }
    }
    //密码管理
    boolean Admin_PassWord_Management(int i) {
        System.out.print("<密码管理> 1.更改自身密码 2.重置经理前台密码 3.返回上一级 4.退出\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Admin_PassWord_ChangeSelf(i);
                break;
            case 2:
                Admin_PassWord_Resetting();
                break;
            case 3:
                break;
            case 4:
                return false;
        }
        return true;
    }

    //修改自己的密码
    void Admin_PassWord_ChangeSelf(int i) {
        String password1 = null;
        String password2 = null;
        System.out.print("<密码管理> 请输入新的密码：");
        String s = sc.nextLine();
        password1 = sc.nextLine();
        System.out.print("<密码管理> 请确认密码：");
        password2 = sc.nextLine();
        if (!password1.equals(password2)) {     //密码不一致的时候退出到管理员菜单
            System.out.println("<密码管理> 两次输入密码不一致！");
            return;
        } else {
            daoimp.updatePassword(password1, i);
            System.out.println("<密码管理> 密码修改成功！");
        }
    }

    void Admin_PassWord_Resetting() {     //重置经历或前台的密码
        System.out.print("<密码管理>请输入目标的用户名：");
        sc.nextLine();
        String name1 = sc.nextLine();
        for (int i = 0; i < UserService.daoimp.getLength(); i++) {
            if (daoimp.getUser(i).ID.equals(name1)) {     //可以搜到用户的时候将其密码重置为手机号
                Admin_Show_Information(i);
                System.out.print("是否重置该用户密码（是Y/否N）:");
                String choice = sc.nextLine();
                if (choice.equals("Y") || choice.equals("y")) {
                    daoimp.getUser(i).password = daoimp.getUser(i).phoneNumber;
                    System.out.println("重置成功！新密码为用户手机号码。");
                }
            }
        }
    }


    void Admin_User_Management() {
        System.out.println("<用户管理> 1.显示所有经理前台信息 2.修改经理前台信息 3.删除经理前台信息 4.信息查询 5.增加经理前台 ");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                for (int i = 0; i < daoimp.getLength(); i++) {
                    Admin_Show_Information(i);
                }
                break;
            case 2:
                Admin_Change_Information();
                break;
            case 3:
                Admin_Remove_Information();
                break;
            case 4:
                Admin_Search_Information();
                break;
            case 5:
                Admin_Add_Information();
                break;
        }
    }

    void Admin_Show_Information(int i) {
        System.out.println("<用户信息> ID:" + daoimp.getUser(i).ID + "; 用户名:" + daoimp.getUser(i).userName + "; 注册时间:" + daoimp.getUser(i).registration_time + "; 用户身份:" + daoimp.getUser(i).identity + "; 手机号:" + daoimp.getUser(i).phoneNumber + "; 邮箱:" + daoimp.getUser(i).address);
    }

    void Admin_Change_Information() {
        System.out.print("<用户管理>请输入目标的用户名：");
        sc.nextLine();
        String name1 = sc.nextLine();
        for (int i = 0; i < UserService.daoimp.getLength(); i++) {
            if (daoimp.getUser(i).ID.equals(name1)) {     //可以搜到用户
                Admin_Show_Information(i);
                System.out.print("选择你要修改的内容：1.用户类型 2.手机号 3.邮箱\n>>");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("输入修改后的用户类型(manager/seller/customer)：\n>>");
                        sc.nextLine();
                        String iden = sc.nextLine();
                        if (iden.equals("manager")) {
                            daoimp.updateIdentity("manager", i);
                            System.out.println("用户信息修改成功！");
                        } else if (iden.equals("seller")) {
                            daoimp.updateIdentity("seller", i);
                            System.out.println("用户信息修改成功！");
                        } else {
                            System.out.println("用户类型输入错误！");
                        }
                        break;
                    case 2:
                        System.out.print("请输入修改后的手机号:");
                        sc.nextLine();
                        String phone = sc.nextLine();
                        daoimp.updatePhoneNumber(phone, i);
                        System.out.println("用户信息修改成功！");
                        break;
                    case 3:
                        System.out.print("请输入修改后的邮箱：");
                        sc.nextLine();
                        String address = sc.nextLine();
                        daoimp.updateAddress(address, i);
                        System.out.println("用户信息修改成功！");
                        break;
                }
                return;
            }
        }
        System.out.println("用户名称输入错误！");
    }

    public void Admin_Remove_Information(){
        System.out.print("<用户删除>请输入目标的用户名：");
        sc.nextLine();
        String name1 = sc.nextLine();
        for (int i = 0; i < UserService.daoimp.getLength(); i++) {
            if (daoimp.getUser(i).ID.equals(name1)) {     //可以搜到用户,且该用户的标号为i
                Admin_Show_Information(i);
                System.out.print("请确认是否删除信息(是Y/否N):");
                String choice = sc.nextLine();     //判断是否确认删除
                if (choice.equals("Y") || choice.equals("y")) {
                    daoimp.deleteMember(i);
                    System.out.println("用户信息已删除！");
                    return ;
                }
            }
        }
        System.out.println("用户名称输入错误！");
    }

    public void Admin_Search_Information(){
        System.out.print("<用户查找>请输入用户名或ID：");
        sc.nextLine();
        String str = sc.nextLine();
        for (int i = 0; i < UserService.daoimp.getLength(); i++) {
            if (daoimp.getUser(i).ID.equals(str) || daoimp.getUser(i).userName.equals(str)) {
                Admin_Show_Information(i);
                return ;
            }
        }
        System.out.println("用户名称输入错误！");
    }

    public void Admin_Add_Information(){
        User newUser=new User();

        boolean flag=true;    //判断名称ID是否有重复
        while(flag){
            flag=false;
            System.out.print("<新增用户>请输入用户名：");
            sc.nextLine();
            String name=sc.nextLine();
            for(int j=0;j<daoimp.length;j++){
                if(name.equals(daoimp.getUser(j).userName)){
                    System.out.println("用户名重复，请重新输入!");
                    flag=true;
                    break;
                }
            }
            newUser.userName=name;
        }

        flag=true;    //判断名称ID是否有重复
        while(flag){
            flag=false;
            System.out.print("<新增用户>请输入ID：");
            String ID=sc.nextLine();
            for(int j=0;j<daoimp.length;j++){
                if(ID.equals(daoimp.getUser(j).ID)){
                    System.out.println("用户ID重复，请重新输入!");
                    flag=true;
                    break;
                }
            }
            newUser.ID=ID;
        }

        System.out.print("<新增用户>请输入密码：");
        newUser.password=sc.nextLine();
        System.out.print("<新增用户>请输入身份(manager/seller/customer)：");
        newUser.identity=sc.nextLine();
        System.out.print("<新增用户>请输入手机号：");
        newUser.phoneNumber=sc.nextLine();
        System.out.print("<新增用户>请输入邮箱：");
        newUser.address=sc.nextLine();

        Date currentDate = new Date();       //获取注册时候的日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date=year+"年"+month+"月"+day+"日";
        newUser.registration_time=date;

        daoimp.addMember(newUser);
        System.out.println("用户\""+newUser.userName+"\"新增成功！");
    }

    public void pressEnterToContinue(){
        System.out.print("按回车键返回菜单...");
        sc.nextLine();
    }
}
