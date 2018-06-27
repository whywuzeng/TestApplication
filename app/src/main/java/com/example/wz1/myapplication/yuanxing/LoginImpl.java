package com.example.wz1.myapplication.yuanxing;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.yuanxing
 */

public class LoginImpl implements login {
    @Override
    public void login() {
        User logineduser = new User();
        logineduser.age = 22;
        logineduser.address = new Address("长沙", "岳麓区", "银双路");
        logineduser.name = "wuzeng";
        logineduser.phoneNum = "15116157225";
        
    }
}
