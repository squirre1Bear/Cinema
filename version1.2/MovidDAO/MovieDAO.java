package MovidDAO;

public interface MovieDAO {
    public void addMovie(Movie movie);  //添加电影
    public Movie getMovie(int i);       //获取电影信息
    public void showMovie(int i);        //显示第i部电影
    public void deleteMovie(int i);     //删除电影
    public void updateName(String Name,int i);    //修改电影名称
    public void updateDirector(String director,int i);      //修改导演
    public void updateCharacter(String mainCharacter,int i);      //修改主演
    public void updateDuration(String duration,int i);       //修改时长
    public void updateGut(String gut,int i);       //修改剧情简介
}
