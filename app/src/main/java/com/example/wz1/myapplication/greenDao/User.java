package com.example.wz1.myapplication.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2019-01-24.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.greenDao
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long id;

    @Index(unique = true)
    private String key;

    @Property(nameInDb = "UserName")
    private String name;

    @NotNull
    private int repos;

    @Transient
    private int tempUsageCount;

    @Generated(hash = 331011436)
    public User(long id, String key, String name, int repos) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.repos = repos;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepos() {
        return this.repos;
    }

    public void setRepos(int repos) {
        this.repos = repos;
    }

}
