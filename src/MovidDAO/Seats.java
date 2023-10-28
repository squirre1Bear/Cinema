package MovidDAO;

public class Seats {
    public int COLUMN=12;    //列数，便于随时修改座位数
    public int ROW=7;       //行数

    char seat1[][]=new char[ROW][COLUMN];
    char seat2[][]=new char[ROW][COLUMN];
    char seat3[][]=new char[ROW][COLUMN];
    char seat4[][]=new char[ROW][COLUMN];
    char seat5[][]=new char[ROW][COLUMN];
    char seat6[][]=new char[ROW][COLUMN];
    char seat7[][]=new char[ROW][COLUMN];
    char seat8[][]=new char[ROW][COLUMN];
    char seat9[][]=new char[ROW][COLUMN];

    public Seats(){    //初始化电影座位
        seatInitialize(seat1);
        seatOccupied(seat1,4,5);
        seatOccupied(seat1,4,6);
        seatOccupied(seat1,3,4);
        seatOccupied(seat1,3,5);
        seatOccupied(seat1,3,6);

        seatInitialize(seat2);
        seatInitialize(seat3);
        seatInitialize(seat4);
        seatInitialize(seat5);
        seatInitialize(seat6);
        seatInitialize(seat7);
        seatInitialize(seat8);
        seatInitialize(seat9);

    }

    public void seatInitialize(char seat[][]){
        for(int i=0;i<ROW;i++) {
            for (int j = 0; j < COLUMN; j++) {
                seat[i][j] = '_';
            }
        }
    }

    public void seatOccupied(char seat[][],int i,int j){
            seat[i][j]='X';
    }
}