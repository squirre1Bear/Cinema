package DAO;

import java.util.Map;
import java.util.Scanner;

public class User {
    public String ID;
    public String password;    //密码
    public String identity;    //用户身份（管理员admin 经理manager 前台seller 顾客customer）
    public String userName;    //用户名
    public String registration_time;   //注册时间
    public String phoneNumber;     //手机号
    public String address;     //邮箱
    public int consumption;    //用户消费金额

    public User(String ID,String password,String identity,String userName,String registration_time,String phoneNumber,String address){
        this.ID=ID;
        this.password=password;
        this.identity=identity;
        this.userName=userName;
        this.registration_time=registration_time;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }

    public User(String ID,String password){
        this.ID=ID;
        this.password=password;
        this.identity="customer";
    }
    public User(){}
}
