package DAO;
import java.util.*;

public interface DAO {    //接口里面包含增删查改四个基本内容。
    // 注意接口里面的定义的变量都是 public static final的常量，数据库要放在DAOImp里面
    public void addMember(User user);    //新增用户
    public User getUser(int i);    //获得用户信息
    public void deleteMember(int i);    //删除用户信息
    public void updatePassword(String password,int i);    //修改用户密码
    public void updateIdentity(String iden,int i);      //修改用户身份
    public void updatePhoneNumber(String phone,int i);      //修改用户手机号
    public void updateAddress(String address,int i);       //修改邮箱
}
