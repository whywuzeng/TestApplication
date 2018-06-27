package com.example.wz1.myapplication.yuanxing;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.yuanxing
 */

public class LoginSession {
    static LoginSession loginSession=null;

    private  User mUser;

    private LoginSession(){}

    public static LoginSession getLoginSession(){
        if (loginSession==null)
        {
            loginSession=new LoginSession();
        }
        return loginSession;
    }

    void setLoginUser(User user){
        this.mUser=user;
    }

    public User getLoginUser(){
        return this.mUser.clone();
    }
}
