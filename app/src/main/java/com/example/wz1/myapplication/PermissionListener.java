package com.example.wz1.myapplication;

import java.util.List;

/**
 * Created by Administrator on 2019-01-16.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */
public interface PermissionListener {
    void granted();
    void denied(List<String> deniedList);
}
