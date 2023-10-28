package MovidDAO;

public class Movie {
    public String movieName;     //电影名称
    public String style;     //电影类型
    public String director;      //导演
    public String mainCharacter;       //主演
    public String movieDuration;       //时长
    public String movieGut;        //简介

    public String beginTime;      //开始时间
    public int hallNumber;        //放映厅号码
    public float price;           //每张票的售价
    Seats seatsimp=new Seats();
    public char seats[][]=new char[seatsimp.ROW][seatsimp.COLUMN];      //每一个seats数组对应一场电影的座位

    public Movie(String movieName, String style, String director, String mainCharacter, String movieDuration, String movieGut){
        this.movieName=movieName;
        this.style=style;
        this.director=director;
        this.mainCharacter = mainCharacter;
        this.movieDuration = movieDuration;
        this.movieGut=movieGut;
    }

    public Movie(){}
}
