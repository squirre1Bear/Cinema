package DAO;
import Identity.Administrator;
import MovidDAO.Movie;

import java.io.*;
import java.util.*;
import java.lang.*;

public class DAOImp implements DAO{
    protected ArrayList<User> information=new ArrayList();
    public int length=0;       //注册用户的数量
    public File file;
    private int[] encryptPassword={9,1,6,0,4,2,7,6,8};     //加密字符序列

    public DAOImp(){
        //初始化文件
        try{
            this.file=new File("data.txt");
            //不存在就创建文件
            if(!file.exists()){
                file.createNewFile();
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

        //先从文件中读取初始人员的信息，并修改length的大小
        InitInfor();

    }

    //将用户信息写入文件
    public void writeUser(User user) {
        //将数据写入文件
        try {
            System.out.print(user.registration_time+"这个是时间2");
            //加密以后写入文件
            String password=setEncryptPassword(user.password);    //注意这里不能直接修改user.password，否则会修改information里面的用户数据！
            FileWriter filewriter = new FileWriter(file,true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
            bufferedwriter.newLine();
            //加密的时候可能会出现空格，分割符改成#split#是防止加密后的密码与分隔符重复
            bufferedwriter.write(user.ID + "#split#" + password + "#split#" + user.userName + "#split#" + user.registration_time + "#split#" + user.phoneNumber + "#split#" + user.address+"#split#"+user.identity+ "#split#" +user.consumption);
            //要注意清空缓冲区！不然抛出IO异常！
            bufferedwriter.flush();
            //写入后关闭文件
            filewriter.close();
            bufferedwriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //对密码进行加密
    public String setEncryptPassword(String password){
        char[] password_original=password.toCharArray();
        String password_after="";
        for(int i=0;i<password_original.length;i++){
            password_original[i]-=encryptPassword[i%encryptPassword.length];    //对原密码加密
            password_after = password_after + password_original[i];
        }

        return password_after;     //返回加密后的密码
    }

    //对密码解密
    public String decryptPassword(String password_encrypted){
        char[] encrypted=password_encrypted.toCharArray();
        String password_decrypted="";
        for(int i=0;i<password_encrypted.length();i++){
            encrypted[i]+=encryptPassword[i%encryptPassword.length];   //对文件中加密的密码解密
            password_decrypted = password_decrypted + encrypted[i];
        }
        return password_decrypted;
    }

    //读取用户信息
    public void InitInfor(){
        //存储读取到的用户数据
        String line;
        //读取文件数据
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            while(true){
                User user=new User();
                line = bufferedreader.readLine();
                if(line!=null){
                    this.length++;    //有一个用户则数据集长度加一
                    //将读入的数据转换为User，并写入Information中
                    String infor[]=line.split("#split#");
                    user.ID=infor[0];
                    user.password=decryptPassword(infor[1]);   //对密码解密
                    user.userName=infor[2];
                    user.registration_time=infor[3];
                    user.phoneNumber=infor[4];
                    user.address=infor[5];
                    user.identity=infor[6];
                    user.consumption=Integer.parseInt(infor[7]);
                    this.information.add(user);
                }
                else break;
            }
            //关闭流
            filereader.close();
            bufferedreader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMember(User user){    //新增用户
        //将新注册的用户写入文件中
        information.add(user);
        writeUser(user);   //写入文件的时候将密码加密
        this.length++;
    }

    //清空文件内容
    public void emptifyFile(){
        try{
            FileWriter filewriter = new FileWriter(file);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

            bufferedwriter.write("");
            bufferedwriter.flush();
            filewriter.close();
            bufferedwriter.close();
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    //更改information之后，更新文件中的数据
    public void updateFile(){
        emptifyFile();
        //将删除后的数据重新写入文件
        try {
            FileWriter filewriter= new FileWriter(file,true);
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

            for(int j=0;j<this.length;j++){
                User user=information.get(j);
                bufferedwriter.write(user.ID + "#split#" + user.password + "#split#" + user.userName + "#split#" + user.registration_time + "#split#" + user.phoneNumber + "#split#" + user.address+"#split#"+user.identity+ "#split#" +user.consumption);
                bufferedwriter.newLine();
                bufferedwriter.flush();
            }
            filewriter.close();
            bufferedwriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //删除用户数据
    public void deleteMember(int i){
        information.remove(i);
        this.length--;
        updateFile();
    }

    public void updatePassword(String password,int i){     //修改用户密码
        User user= information.get(i);      //取下标为i的用户信息
        user.password=password;     //修改密码
        information.set(i,user);       //将修改后的内容放回数据表
        updateFile();
    }

    public void updateIdentity(String iden, int i) {        //修改身份
        User user=information.get(i);
        user.identity=iden;
        this.information.set(i,user);
        updateFile();
    }

    public void updatePhoneNumber(String phone, int i) {       //修改手机号
        User user=information.get(i);
        user.phoneNumber=phone;
        this.information.set(i,user);
        updateFile();
    }

    public void updateAddress(String address, int i) {      //修改邮箱
        User user=information.get(i);
        user.address=address;
        this.information.set(i,user);
        updateFile();
    }

    public User getUser(int i){      //获得下标为i用户的信息
        User user=information.get(i);
        return user;
    }

    public int getLength(){
        return this.length;
    }
}