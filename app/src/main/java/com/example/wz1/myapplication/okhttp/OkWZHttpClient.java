package com.example.wz1.myapplication.okhttp;


import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;

import okhttp3.Authenticator;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;

/**
 * Created by Administrator on 2018-07-09.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.okhttp
 */

public class OkWZHttpClient {

    HostnameVerifier hostnameVerifier;
    CertificatePinner certificatePinner;
    Authenticator proxyAuthenticator;
    Authenticator authenticator;
    ConnectionPool connectionPool;

    public OkWZHttpClient(Builder builder) {
        this.hostnameVerifier=builder.hostnameVerifier;
        CertificatePinner certificatePinner=builder.certificatePinner;
        Authenticator proxyAuthenticator=builder.proxyAuthenticator;
        Authenticator authenticator=builder.authenticator;
        ConnectionPool connectionPool=builder.connectionPool;
    }

    public static final class Builder{
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        Authenticator proxyAuthenticator;
        Authenticator authenticator;
        ConnectionPool connectionPool;

        int connectTimeout;
        int readTimeout;

        public Builder(){
            //默认值
            hostnameVerifier=null;
            certificatePinner=null;
            proxyAuthenticator=null;
            authenticator=null;
            connectionPool=null;

            connectTimeout=60;
            readTimeout=60;
        }

        public Builder connectTimeOut(long time, TimeUnit unit){
            connectTimeout= checkDuration("timeout",time,unit);
            return this;
        }

        public static int checkDuration(String name, long duration, java.util.concurrent.TimeUnit unit) {
            if (duration < 0) throw new IllegalArgumentException(name + " < 0");
            if (unit == null) throw new NullPointerException("unit == null");
            long millis = unit.toMillis(duration);
            if (millis > Integer.MAX_VALUE) throw new IllegalArgumentException(name + " too large.");
            if (millis == 0 && duration > 0) throw new IllegalArgumentException(name + " too small.");
            return (int) millis;
        }

        public OkWZHttpClient build(){
            return new OkWZHttpClient(this);
        }
    }
}
