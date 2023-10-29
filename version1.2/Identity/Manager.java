package Identity;
import DAO.User;
import MovidDAO.Movie;
import MovidDAO.MovieDAOimp;
import java.util.*;

public class Manager {
    int Hall_Number=4;      //总放映厅个数
    User user=new User();
    Scanner sc=new Scanner(System.in);
    MovieDAOimp moviedaoimp=new MovieDAOimp();
    public Manager(User user){      //初始化传入用户信息
        this.user=user;
    }
    public void Manager_Menu(){
            System.out.print("用户名:" + this.user.userName);
            System.out.println("； 身份:经理；");
            System.out.print("请选择：1.<影片管理>  2.<排片管理> 3.<退出登录>\n>>");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (!Manager_MovieInfor_Management()) {    //当选择退出的时候进入if语句
                        return;
                    }
                    pressEnterToContinue();
                    Manager_Menu();    //选择其他选项的时候继续调用菜单
                case 2:
                    if(!Manager_Arrange_Management()){
                        return ;
                    }
                    pressEnterToContinue();
                    Manager_Menu();
                case 3:
                    return ;
            }
    }

    //排片管理
    public boolean Manager_Arrange_Management(){
        System.out.print("<排片管理> 1.增加电影场次 2.修改电影场次 3.删除电影场次 4.列出近一周所有场次\n>>");
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                Manager_Arrange_Add();
                break;
            case 2:
                Manager_Arrange_Update();
                break;
            case 3:
                Manager_Arrange_Remove();
                break;
            case 4:
                Mananger_Arrange_ShowAll();
                break;
        }
        return true;
    }

    //增加电影场次
    public void Manager_Arrange_Add(){
        boolean flag_hall=true;     //判断输入的放映厅序号是否正确
        int hallNum=0;
        Movie movie=new Movie();
        System.out.print("<增加场次> 请输入电影名称:");
        sc.nextLine();
        String name=sc.nextLine();
        while(flag_hall){
            System.out.print("<增加场次> 请输入放映厅序号：");       //输入不在放映厅序号范围内时会报错
            hallNum=sc.nextInt();
            if(hallNum>Hall_Number||hallNum<=0){
                System.out.println("序号错误,请重新输入！");
                continue;
            }
            flag_hall=false;
        }
        System.out.print("<增加场次> 请输入开始时间：");
        String beginTime=sc.nextLine();

        movie.movieName=name;
        movie.hallNumber=hallNum;
        movie.beginTime=beginTime;

        moviedaoimp.addSession(movie);
        System.out.print("场次增加成功！");
    }

    //修改电影场次
    public void Manager_Arrange_Update(){
        for(int i=0;i< moviedaoimp.getSession();i++){      //显示所有场次，让用户找到要修改的电影场次
            System.out.print(i+1+". ");
            moviedaoimp.showSession(i);
        }
        boolean flag=true;
        int number=0;
        while(flag){
            System.out.print("<修改场次> 请输入序号：");
            number=sc.nextInt();
            if(number<=0 || number> moviedaoimp.getSession()+1){
                System.out.print("输入错误，请重新输入！");
                continue;
            }
            flag=false;
        }
        System.out.print("<修改场次> 1.修改名称 2.修改放映厅 3.修改开始时间\n>>");
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                System.out.print("请输入修改后的名称：");
                sc.nextLine();
                String newName=sc.nextLine();
                moviedaoimp.session_updateName(newName,number-1);
                System.out.println("影片修改成功！");
                break;
            case 2:
                System.out.print("请输入修改后的放映厅：");
                int newNumber=sc.nextInt();
                moviedaoimp.session_updateNumebr(newNumber,number-1);
                System.out.println("影片修改成功！");
                break;
            case 3:
                System.out.print("请输入修改后的开始时间：");
                sc.nextLine();
                String newTime=sc.nextLine();
                moviedaoimp.session_updateTime(newTime,number-1);
                System.out.println("影片修改成功！");
                break;
        }
    }

    //删除电影场次
    public void Manager_Arrange_Remove(){
        for(int i=0;i< moviedaoimp.getSession();i++){      //显示所有场次，找到要删除的电影场次
            System.out.print(i+1+".");
            moviedaoimp.showSession(i);
        }
        boolean flag=true;
        int number=0;
        while(flag){
            System.out.print("<修改场次> 请输入序号：");     //场次输入错误时会提示用户重新输入
            number=sc.nextInt();
            if(number<=0 || number> moviedaoimp.getSession()+1){
                System.out.print("输入错误，请重新输入！");
                continue;
            }
            flag=false;
        }
        System.out.print("<场次删除>删除后无法恢复，是否删除(是Y/否N)");
        sc.nextLine();
        String choice=sc.nextLine();
        if(choice.equals("Y")||choice.equals("y")){
            moviedaoimp.seccion_remove(number-1);
            System.out.println("该场次删除成功！");
        }
    }

    //显示所有电影场次
    public void Mananger_Arrange_ShowAll(){
        moviedaoimp.showAllSessions();
    }

    //影片管理
    public boolean Manager_MovieInfor_Management(){
        System.out.print("<影片管理> 1.列出所有电影信息 2.添加电影 3.影片修改 4.删除影片 5.影片查询 6.退出\n>>");
        int choice=sc.nextInt();
        switch (choice){
            case 1:
                for(int i=0;i<moviedaoimp.getLength();i++){
                    moviedaoimp.showMovie(i);
                }
                break;
            case 2:
                Movie movie=new Movie();
                System.out.print("请输入电影名称:");
                sc.nextLine();
                movie.movieName=sc.nextLine();
                System.out.print("电影类型:");
                movie.style=sc.nextLine();
                System.out.print("导演:");
                movie.director=sc.nextLine();
                System.out.print("主演:");
                movie.mainCharacter =sc.nextLine();
                System.out.print("时长:");
                movie.movieDuration =sc.nextLine();
                System.out.print("简介:");
                movie.movieGut=sc.nextLine();

                moviedaoimp.addMovie(movie);
                System.out.print("电影《"+movie.movieName+"》添加完成！");
            case 3:
                if(Manager_MovieInfor_Update())        //修改电影信息,选择退出时返回值为false
                    return true;
                else
                    return false;
            case 4:
                Manager_MovieInfor_Remove();

                break;
            case 5:
                Manager_MovieInfor_Search();
                break;
            case 6:
                return false;
        }

        return true;
    }

    //影片管理中的修改影片
    public boolean Manager_MovieInfor_Update(){
        System.out.print("<影片修改>请输入要修改的影片名称：");
        sc.nextLine();
        String name=sc.nextLine();
        int count=0;
        for(int i=0;i<moviedaoimp.getLength();i++){
            if(moviedaoimp.getMovie(i).movieName.equals(name)){
                System.out.print("<影片修改>");
                moviedaoimp.showMovie(i);
                count=i;
                break;
            }
        }
        System.out.print("<影片修改> 1.片名 2.导演 3.主演 4.时长 5.简介\n>>");
        int change=sc.nextInt();
        switch (change){
            case 1:
                System.out.print("<影片修改> 请输入更改后的电影名称：");
                sc.nextLine();
                String afterName=sc.nextLine();
                moviedaoimp.updateName(afterName,count);
                break;
            case 2:
                sc.nextLine();
                System.out.print("<影片修改> 请输入更改后的导演：");
                String afterDirector=sc.nextLine();
                moviedaoimp.updateDirector(afterDirector,count);
                break;
            case 3:
                sc.nextLine();
                System.out.print("<影片修改> 请输入更改后的主演：");
                String afterCharacter=sc.nextLine();
                moviedaoimp.updateCharacter(afterCharacter,count);
                break;
            case 4:
                sc.nextLine();
                System.out.print("<影片修改> 请输入更改后的时长：");
                String afterDuration=sc.nextLine();
                moviedaoimp.updateDuration(afterDuration,count);
                break;
            case 5:
                sc.nextLine();
                System.out.print("<影片修改> 请输入更改后的简介：");
                String afterGut=sc.nextLine();
                moviedaoimp.updateGut(afterGut,count);
                break;
            case 6:
                return false;
        }
        System.out.print("影片修改成功！");
        return true;
    }

    //影片管理中的删除影片
    public void Manager_MovieInfor_Remove(){
        System.out.print("<影片删除>请输入要删除的影片名称：");
        sc.nextLine();
        String name=sc.nextLine();
        int count=0;
        for(int i=0;i<moviedaoimp.getLength();i++) {
            if (moviedaoimp.getMovie(i).movieName.equals(name)) {
                System.out.print("<影片删除>");
                moviedaoimp.showMovie(i);
                count = i;
                break;
            }
        }
        System.out.print("<影片修改> 删除后无法恢复，是否删除(是Y/否N)\n>>");
        String yn=sc.nextLine();
        if(yn.equals("Y")||yn.equals("y")){
            moviedaoimp.deleteMovie(count);
            System.out.print("影片删除成功！");
        }
    }

    public void Manager_MovieInfor_Search(){
        System.out.print("<影片查询>请输入影片名称或导演：");
        sc.nextLine();
        String name=sc.nextLine();
        int count=0;
        for(int i=0;i<moviedaoimp.getLength();i++){
            if(moviedaoimp.getMovie(i).movieName.equals(name) || moviedaoimp.getMovie(i).director.equals(name)){
                System.out.print("<影片查询>");
                moviedaoimp.showMovie(i);
            }
        }
        if(count==0){
            System.out.print("未查询到相关电影！");
        }
    }
    public void pressEnterToContinue(){
        System.out.print("按回车键返回菜单...");
        sc.nextLine();
    }
}
