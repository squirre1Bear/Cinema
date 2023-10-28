package Identity;
import DAO.*;
import MovidDAO.Seats;

import static DAO.UserService.*;
import static MovidDAO.MovieService.*;    //从这里修改数据库
import java.util.*;

public class Customer {
    User user=null;
    Scanner sc=new Scanner(System.in);
    public Customer(User user){
        this.user=user;
    }
    public void Customer_Menu(){
        System.out.print("用户名:" + this.user.userName);
        System.out.print("; 身份:顾客; ");
        if(user.consumption<100){
            System.out.println("等级：铜牌用户");
        }
        else if(user.consumption>=100 && user.consumption<=300){
            System.out.println("等级：银牌用户 *购票享95折优惠*");
        }
        else{
            System.out.println("等级：金牌用户 *购票享88折优惠*");
        }

        System.out.print("请选择：1.<密码管理> 2.<场次查看/购票> 3.<取票> 4.<退出登录>\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Customer_Password_Management();
                pressEnterToContinue();
                Customer_Menu();    //选择其他选项的时候继续调用菜单
                break;
            case 2:
                Customer_Ticket();
                pressEnterToContinue();
                Customer_Menu();
                break;
            case 3:
                Customer_Ticket_Get();
                pressEnterToContinue();
                Customer_Menu();
                break;
            case 4:
                return ;
        }
    }

    //顾客密码管理
    public void Customer_Password_Management(){
        System.out.print("<密码管理> 1.密码修改 2.忘记密码\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Customer_Password_Change();
                break;
            case 2:
                Customer_Password_Anew();
                break;
        }
    }

    //修改密码
    public void Customer_Password_Change(){
        String passWord1=null;
        String passWord2=null;
        boolean flag=true;
        a:while(flag){
            System.out.print("<密码修改> 密码至少为8位，且为大小字母、数字、特殊符号组合:");
            sc.nextLine();
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

        System.out.print("<密码修改> 请确认修改后的密码：");
        passWord2=sc.nextLine();
        if(passWord1.equals(passWord2)){
            for(int i=0;i< daoimp.getLength();i++){
                if(daoimp.getUser(i).ID.equals(user.ID)){
                    daoimp.updatePassword(passWord1,i);
                    break;
                }
            }
            System.out.print("密码修改成功！");
        }
    }

    //重置密码
    public void Customer_Password_Anew(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";    //自动生成密码时的可选符号
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {       //生成的新密码长度为8
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        String randomPassword = sb.toString();
        for(int i=0;i< daoimp.getLength();i++){
            if(daoimp.getUser(i).ID.equals(user.ID)){
                daoimp.updatePassword(randomPassword,i);
                System.out.println("密码已重置！");
                break;
            }
        }
    }

    //购票
    public void Customer_Ticket(){
        System.out.print("<场次查看> 1.所有电影场次 2.查找指定电影场次 3.购票\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Customer_Ticket_ShowAllSessions();
                break;
            case 2:
                Customer_Ticket_SearchSession();
                break;
            case 3:
                Customer_Ticket_Buy();
                break;
        }
    }

    //显示近一周所有电影场次
    public void Customer_Ticket_ShowAllSessions(){
        for(int i=0;i< moviedaoimp.getSession();i++) {
            moviedaoimp.showSession(i);
        }
    }

    //查找指定电影的播放场次
    public void Customer_Ticket_SearchSession(){
        System.out.print("<场次查询>请输入影片名称：");
        sc.nextLine();
        String name=sc.nextLine();
        int count=0;
        System.out.println("<场次查询>:");
        for(int i=0;i<moviedaoimp.getSession();i++){
            if(moviedaoimp.getSessionInfor(i).movieName.equals(name)){
                moviedaoimp.showSession(i);
                count++;
            }
        }
        if(count==0){
            System.out.println("未查询到相关电影！");
        }
    }

    //购票
    public void Customer_Ticket_Buy(){
        for(int i=0;i< moviedaoimp.getSession();i++) {      //显示所有场次
            System.out.print(i+". ");
            moviedaoimp.showSession(i);
        }

        boolean flag=true;
        int number=0;       //记录用户输入的场次号
        while(flag){
            System.out.print("<购票> 输入要购买的场次：");
            number=sc.nextInt();
            if(number<=0 || number> moviedaoimp.getSession()+1){
                System.out.print("输入错误，请重新输入！");
                continue;
            }
            flag=false;
        }
        moviedaoimp.showSeats(number-1);     //显示这一场的座位信息

        Seats seats=new Seats();  //用于获取放映厅的座位数
        flag=true;
        int row=0;
        char column='0';
        String chooseSeat[]=new String[seats.ROW*seats.COLUMN];   //购买多张票的时候记录用户选择过的座位号

        int ticketNumber=0;
        while(flag){
            System.out.print("<购票> 输入待选座位的行数：");
            row=sc.nextInt();
            System.out.print("<购票> 输入列对应的字母：");
            sc.nextLine();
            column=sc.nextLine().charAt(0);
            moviedaoimp.setSessioni(number,row-1,column-'A');
            chooseSeat[ticketNumber]=""+row+(column-'a'+1);   //选择的座位按行-列的顺序换为整型，再换为字符串
            ticketNumber++;

            System.out.print("<购票> 已选"+ticketNumber+"张，继续选择Y/付款N：\n>>");
            String choice=sc.nextLine();
            if(choice.equals("N")||choice.equals("n")){
                flag=false;
            }
        }
        System.out.println("<付款> 票数："+ticketNumber+"；单价："+moviedaoimp.getSessionInfor(number-1).price);
        if(user.consumption<100){
            System.out.println("总金额："+ticketNumber*moviedaoimp.getSessionInfor(number-1).price+"元");
        }
        else if(user.consumption>=100 && user.consumption<=300){
            System.out.println("总金额："+ticketNumber*moviedaoimp.getSessionInfor(number-1).price*0.95+"元 *银牌用户购票享95折优惠*");
        }
        else{
            System.out.println("总金额："+ticketNumber*moviedaoimp.getSessionInfor(number-1).price*0.88+"元 *金牌用户购票享88折优惠*");
        }

        System.out.print("<付款> 请选择支付方式：1.支付宝 2.微信 3.银行卡\n>>");
        int pay=sc.nextInt();
        System.out.println("付款成功！");
        String[] beginTime=moviedaoimp.getSessionInfor(number).beginTime.split("-");      //依次为 月份、日期、小时、分钟

        for(int i=0;i<ticketNumber;i++){
            System.out.print("[电影票] 电影名称："+moviedaoimp.getSessionInfor(number-1).movieName+"； 放映厅:"+moviedaoimp.getSessionInfor(number-1).hallNumber+"号厅； 开始时间:"+beginTime[0]+"月"+beginTime[1]+"日"+beginTime[2]+":"+beginTime[3]);
            //票号生成规则是 开始时间+放映厅+座位号
            String ticketNo=beginTime[0]+beginTime[1]+beginTime[2]+beginTime[3]+moviedaoimp.getSessionInfor(number-1).hallNumber+chooseSeat[i];
            System.out.println(" 票号："+ticketNo);
            moviedaoimp.addTicket(ticketNo);
        }

        //增加用户消费金额
        user.consumption+=ticketNumber*moviedaoimp.getSessionInfor(number-1).price;
        //在文件中修改用户消费金额
        daoimp.updateFile();
        sc.nextLine();
    }

    //取票
    public void Customer_Ticket_Get(){
        System.out.println("<取票> 请输入票号：");
        sc.nextLine();
        String tickerNo=sc.nextLine();
        moviedaoimp.checkTicket(tickerNo);
    }

    public void pressEnterToContinue(){
        System.out.print("按回车键返回菜单...");
        sc.nextLine();
    }

}