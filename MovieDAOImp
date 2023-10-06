package MovidDAO;

import java.util.*;

public class MovieDAOimp implements MovieDAO{
    private int length=0;       //电影数量
    private int sessions=0;     //电影场次
    private ArrayList<Movie> movies=new ArrayList();    //包含电影名，类型，导演，主演，时长，简介
    private ArrayList<Movie> timeHall=new ArrayList();     //包含电影名,放映厅，开始时间，座位信息
    private ArrayList<String> ticketNo=new ArrayList<>();     //存储票号
    Seats seats=new Seats();

    //获得第i部电影
    public Movie getMovie(int i) {
        Movie moviei=movies.get(i);
        return moviei;
    }

    //显示第i部电影的信息
    public void showMovie(int i){
            Movie moviei=movies.get(i);
            System.out.println("电影名:"+moviei.movieName+"； 类型:"+moviei.style+"； 导演:"+moviei.director+"； 主演:"+moviei.mainCharacter +"； 时长:"+moviei.movieDuration +"； 简介:"+moviei.movieGut);
    }

    //添加电影
    public void addMovie(Movie movie) {
        movies.add(movie);
        length++;
    }

    //删除电影
    public void deleteMovie(int i) {
        movies.remove(i);
        length--;
    }

    //修改电影主演
    public void updateCharacter(String mainCharacter, int i) {
        Movie movie=movies.get(i);
        movie.mainCharacter =mainCharacter;
        movies.set(i,movie);
    }

    //修改导演
    public void updateDirector(String director, int i) {
        Movie movie=movies.get(i);
        movie.director=director;
        movies.set(i,movie);
    }

    //修改时长
    public void updateDuration(String duration, int i) {
        Movie movie=movies.get(i);
        movie.movieDuration =duration;
        movies.set(i,movie);
    }

    //修改剧情简介
    public void updateGut(String gut, int i) {
        Movie movie=movies.get(i);
        movie.movieGut=gut;
        movies.set(i,movie);
    }

    //修改电影名称
    public void updateName(String Name, int i) {
        Movie movie=movies.get(i);
        movie.movieName=Name;
        movies.set(i,movie);
    }

    //获得正在上映的电影总数
    public int getLength(){
        return length;
    }
    //获得近一周所有场次数量
    public int getSession(){
        return sessions;
    }

    //显示第i场电影的信息
    public void showSession(int i){
        String[] beginTime=timeHall.get(i).beginTime.split("-");      //依次为 月份、日期、小时、分钟
        System.out.println("电影:"+timeHall.get(i).movieName+"； 放映厅:"+timeHall.get(i).hallNumber+"号厅； 开始时间:"+beginTime[0]+"月"+beginTime[1]+"日 "+beginTime[2]+":"+beginTime[3]+"；票价:"+timeHall.get(i).price+"元");
    }

    //获得第i场电影的信息
    public Movie getSessionInfor(int i){
        return timeHall.get(i);
    }

    //显示所有场次
    public void showAllSessions(){
        for(int i=0;i<sessions;i++){
            showSession(i);
        }
    }

    //添加场次
    public void addSession(Movie movie){
        movies.add(movie);
    }

    //更新放映电影名称
    public void session_updateName(String newName, int i){
        Movie movie=timeHall.get(i);
        movie.movieName=newName;
        timeHall.set(i,movie);
    }

    //更新放映厅
    public void session_updateNumebr(int hallNumber,int i){
        Movie movie=timeHall.get(i);
        movie.hallNumber=hallNumber;
        timeHall.set(i,movie);
    }

    //更新开始时间
    public void session_updateTime(String newTime, int i){
        Movie movie=timeHall.get(i);
        movie.beginTime=newTime;
        timeHall.set(i,movie);
    }

    //删除场次
    public void seccion_remove(int i){
        timeHall.remove(i);
        sessions--;
    }

    //显示第i场的座位信息
    public void showSeats(int i){
        System.out.println("[座位信息] "+timeHall.get(i).movieName+" "+timeHall.get(i).hallNumber+"号厅"+" 总座位数："+seats.COLUMN*seats.ROW);
        System.out.println("\"_\"代表空座位，\"X\"代表座位已售出");
        System.out.print("  ");
        char column='A';
        for(int j=1;j<=seats.COLUMN;j++){     //输出每一列的标号
            System.out.print(column+" ");
            column++;
        }
        System.out.println();
        for(int k=0;k<seats.ROW;k++){
            System.out.print(k+1+" ");
            for(int j=0;j<seats.COLUMN;j++){
                System.out.print(timeHall.get(i).seats[k][j]+" ");
            }
            System.out.println();
        }
    }

    //将第i场电影的j，k座位设置为售出
    public void setSessioni(int i,int j,int k){
        Movie sellTicket=timeHall.get(i-1);
        char sell[][]=sellTicket.seats;
        if(sell[i][j]=='X'){            //当座位已经售出时会给出报错信息
            System.out.println("该座位已经售出，请重新选择");
        }
        else{
            System.out.print("");//输入用户手机号

            sell[i][j]='X';

        }
        sellTicket.seats=sell;
        timeHall.set(i,sellTicket);
    }

    //添加售出电影票的票号
    public void addTicket(String ticketNo){
        this.ticketNo.add(ticketNo);
    }

    //
    public void checkTicket(String ticketNo){
        if(this.ticketNo.contains(ticketNo)){
            System.out.print("票号"+ticketNo+" 出票成功！");
        }
        else {
            System.out.print("票号输入错误或已经取票!");
        }
    }
    //构造函数，初始化电影信息
    public MovieDAOimp(){
        //添加正在上映的电影
        Movie movie1 = new Movie("坚如磐石", "犯罪、悬疑", "张艺谋", "雷佳音、张国立、周冬雨", "127分钟", "金江市副市长郑刚（张国立 饰）之子苏见明（雷佳音 饰）不顾父亲的劝阻，应邀赴约首富黎志田（于和伟 饰）的“鸿门宴”，不料却被迫观看了一出“人手下火锅”的猖狂戏码。旧案翻起，风雨欲来，各方蛰伏势力蠢蠢欲动，筹谋与算计、审视与怀疑，光怪陆离之中，人性欲望翻腾，谁将撕去最后的面具？");
        Movie movie2 = new Movie("孤注一掷", "剧情、动作", "申奥", " 张艺兴、金晨、王传君", "130分钟", "电影取材自上万起真实诈骗案例，境外网络诈骗全产业链骇人内幕将在大银幕上揭秘。程序员潘生（张艺兴 饰）、模特安娜（金晨 饰）被海外高薪招聘吸引，却落入境外诈骗工厂的陷阱。为了离开，两人准备从赌徒阿天（王大陆 饰）身上套现、完成业绩……潘生与安娜能否逃过诈骗集团头目陆经理（王传君 饰）的残暴折磨？面对警察（咏梅 饰）的跨国调查和追捕，他们又会何去何从？");
        Movie movie3 = new Movie("莫斯科行动", "犯罪、动作", "邱礼涛", "张涵予、刘德华、文咏珊", "128分钟", "90年代初，苏联解体，俄罗斯经济接近崩溃。大批中国倒爷乘坐国际列车将中国商品倒卖到俄罗斯，获取天价利润。以苗青山（黄轩 饰）为首的悍匪集团在列车上进行了丧心病狂的连环洗劫！中国警察崔振海（张涵予 饰）带领公安小队，展开抓捕行动。过程中，化名为瓦西里的神秘人（刘德华 饰）和一名叫真真的女子（文咏珊 饰）引起了警方的注意，一个更大的惊天阴谋逐渐揭开……");
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        length=3;

        //把所有属性整合成一个对象。调用的时候根据变量名可以轻松找到对于内容，比用list存直观太多、
        Movie movieTimeHall1=new Movie();
        movieTimeHall1.movieName="坚如磐石";
        movieTimeHall1.hallNumber=1;
        movieTimeHall1.beginTime="10-1-14-30";    //10月1日14:30开始
        movieTimeHall1.seats=seats.seat1;
        movieTimeHall1.price=35;
        timeHall.add(movieTimeHall1);
        sessions++;

        Movie movieTimeHall8=new Movie();
        movieTimeHall8.movieName="莫斯科行动";
        movieTimeHall8.hallNumber=3;
        movieTimeHall8.beginTime="10-1-15-30";    //10月1日15:30开始
        movieTimeHall8.seats=seats.seat8;
        movieTimeHall8.price=30;
        timeHall.add(movieTimeHall8);
        sessions++;

        Movie movieTimeHall3=new Movie();
        movieTimeHall3.movieName="坚如磐石";
        movieTimeHall3.hallNumber=1;
        movieTimeHall3.beginTime="10-1-17-30";    //10月1日17:30开始
        movieTimeHall3.seats=seats.seat3;
        movieTimeHall3.price=33;
        timeHall.add(movieTimeHall3);
        sessions++;

        Movie movieTimeHall4=new Movie();
        movieTimeHall4.movieName="孤注一掷";
        movieTimeHall4.hallNumber=3;
        movieTimeHall4.beginTime="10-1-19-30";    //10月1日19:30开始
        movieTimeHall4.seats=seats.seat4;
        movieTimeHall4.price=39.9F;
        timeHall.add(movieTimeHall4);
        sessions++;

        Movie movieTimeHall2=new Movie();
        movieTimeHall2.movieName="坚如磐石";
        movieTimeHall2.hallNumber=2;
        movieTimeHall2.beginTime="10-1-20-00";    //10月1日20:00开始
        movieTimeHall2.seats=seats.seat2;
        movieTimeHall2.price=43.9F;
        timeHall.add(movieTimeHall2);
        sessions++;

        Movie movieTimeHall5=new Movie();
        movieTimeHall5.movieName="孤注一掷";
        movieTimeHall5.hallNumber=2;
        movieTimeHall5.beginTime="10-1-21-20";    //10月1日21:20开始
        movieTimeHall5.seats=seats.seat5;
        movieTimeHall5.price=35;
        timeHall.add(movieTimeHall5);
        sessions++;

        Movie movieTimeHall6=new Movie();
        movieTimeHall6.movieName="莫斯科行动";
        movieTimeHall6.hallNumber=1;
        movieTimeHall6.beginTime="10-2-8-30";    //10月2日8:30开始
        movieTimeHall6.seats=seats.seat6;
        movieTimeHall6.price=25;
        timeHall.add(movieTimeHall6);
        sessions++;

        Movie movieTimeHall7=new Movie();
        movieTimeHall7.movieName="莫斯科行动";
        movieTimeHall7.hallNumber=3;
        movieTimeHall7.beginTime="10-2-14-10";    //10月2日14:10开始
        movieTimeHall7.seats=seats.seat7;
        movieTimeHall7.price=30;
        timeHall.add(movieTimeHall7);
        sessions++;

    }
}
