package Identity;
import DAO.*;
import MovidDAO.*;

import java.util.Scanner;

import static MovidDAO.MovieService.moviedaoimp;

public class Seller {
    MovieDAOimp moviedaoimp=new MovieDAOimp();
    User user=null;
    Scanner sc=new Scanner(System.in);

    public Seller(User user){
        this.user=user;
    }
    public void Seller_Menu(){
        System.out.println("———————菜—————单———————");
        System.out.print("用户名:" + this.user.userName);
        System.out.println("； 身份:前台");
        System.out.print("请选择：1.<电影信息> 2.<场次信息> 3.<座位查询> 4.<售票> 5.<退出登录>\n>>");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Seller_ShowAllMovies();
                pressEnterToContinue();
                Seller_Menu();
                break;
            case 2:
                Seller_ShowAllSessions();
                pressEnterToContinue();
                Seller_Menu();
                break;
            case 3:
                Seller_ShowSeats();
                pressEnterToContinue();
                Seller_Menu();
                break;
            case 4:
                Seller_Ticket();
                pressEnterToContinue();
                Seller_Menu();
                break;
            case 5:
                break;
        }
    }

    //显示近一周所有电影信息
    public void Seller_ShowAllMovies(){
        for(int i=0;i<moviedaoimp.getLength();i++){
            moviedaoimp.showMovie(i);
        }
    }

    //显示近一周所有场次
    public void Seller_ShowAllSessions(){
        moviedaoimp.showAllSessions();
    }

    //根据场次查询座位信息
    public void Seller_ShowSeats(){
        for(int i=0;i< moviedaoimp.getSession();i++){
            System.out.print(i+1+". ");
            moviedaoimp.showSession(i);
        }
        boolean flag=true;
        int number=0;       //记录用户输入的场次号
        while(flag){
            System.out.print("<座位查询> 请输入序号：");
            number=sc.nextInt();
            if(number<=0 || number> moviedaoimp.getSession()+1){
                System.out.print("输入错误，请重新输入！");
                continue;
            }
            flag=false;
        }
        moviedaoimp.showSeats(number-1);
    }

    public void Seller_Ticket(){
        for(int i=0;i< moviedaoimp.getSession();i++){
            System.out.print(i+1+". ");
            moviedaoimp.showSession(i);
        }
        boolean flag=true;
        int number=0;       //记录用户输入的场次号
        while(flag){
            System.out.print("<售票> 请输入要选择的场次：");
            number=sc.nextInt();
            if(number<=0 || number> moviedaoimp.getSession()+1){
                System.out.print("输入错误，请重新输入！");
                continue;
            }
            flag=false;
        }
        moviedaoimp.showSeats(number-1);        //在输入场次之后调出对应的座位信息
        System.out.print("<售票> 输入待选座位的行数：");
        int row=sc.nextInt();
        System.out.print("<售票> 输入列对应的字母：");
        sc.nextLine();
        char column=sc.nextLine().charAt(0);
        moviedaoimp.setSessioni(number,row-1,column-'A');
        System.out.println("<售票> 已选1张票，票价"+moviedaoimp.getSessionInfor(number-1).price+"元");

        flag=true;
        a:while (flag){
            System.out.print("<售票> 输入用户手机号码：");
            String phoneNumber=sc.nextLine();
            //如果对应上用户则不再查找用户
            for(int i=0;i<UserService.daoimp.length;i++){
                if(phoneNumber.equals(UserService.daoimp.getUser(i).phoneNumber)){
                    if(user.consumption<100){
                        System.out.println("<付款金额> "+moviedaoimp.getSessionInfor(number-1).price+"元");
                    }
                    else if(user.consumption>=100 && user.consumption<=300){
                        System.out.println("<付款金额> "+moviedaoimp.getSessionInfor(number-1).price*0.95+"元 *银牌用户购票享95折优惠*");
                    }
                    else{
                        System.out.println("<付款金额> "+moviedaoimp.getSessionInfor(number-1).price*0.88+"元 *金牌用户购票享88折优惠*");
                    }

                    System.out.println("购卖成功，可根据票号取票！");
                    String[] beginTime=moviedaoimp.getSessionInfor(number).beginTime.split("-");      //依次为 月份、日期、小时、分钟
                    System.out.print("[电影票] 电影名称："+moviedaoimp.getSessionInfor(number-1).movieName+"； 放映厅:"+moviedaoimp.getSessionInfor(number-1).hallNumber+"号厅； 开始时间:"+beginTime[0]+"月"+beginTime[1]+"日"+beginTime[2]+":"+beginTime[3]);
                    //票号生成规则是 开始时间+放映厅+座位号
                    int col=column-'a'+1;
                    String ticketNo=beginTime[0]+beginTime[1]+beginTime[2]+beginTime[3]+moviedaoimp.getSessionInfor(number-1).hallNumber+row+col;
                    System.out.println(" 票号："+ticketNo);
                    moviedaoimp.addTicket(ticketNo);
                    //增加用户消费金额
                    user.consumption+=moviedaoimp.getSessionInfor(number-1).price;
                    break a;
                }
            }
            System.out.println("手机号输入错误，请重新输入！");
        }
    }

    public void pressEnterToContinue(){
        System.out.print("按回车键返回菜单...");
        sc.nextLine();
    }
}