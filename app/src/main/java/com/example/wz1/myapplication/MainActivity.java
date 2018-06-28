package com.example.wz1.myapplication;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.wz1.myapplication.entity.AppEntity;
import com.example.wz1.myapplication.entity.AppUtils;
import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private LottieAnimationView lt_pulldown_arrow;
    private LottieAnimationView lt_pulldown_refresh;
    private TextView tv_refresh_success;
    private ImageView iv_refresh_success;
    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lt_pulldown_arrow=(LottieAnimationView)findViewById(R.id.lt_pulldown_arrow);
        lt_pulldown_refresh=(LottieAnimationView)findViewById(R.id.lt_pulldown_refresh);
        tv_refresh_success=(TextView)findViewById(R.id.tv_refresh_success);
        iv_refresh_success=(ImageView)findViewById(R.id.iv_refresh_success);
        btn_click=(Button)findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lt_pulldown_arrow.playAnimation();
            }
        });

        lt_pulldown_arrow.playAnimation();

        getAndroidProcess(this);

//        lt_pulldown_refresh.setVisibility(View.VISIBLE);
//        lt_pulldown_refresh.playAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            lt_pulldown_arrow.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    if (!valueAnimator.isRunning())
                    {
    //                    tv_refresh_success.setVisibility(View.VISIBLE);
    //                    iv_refresh_success.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }


    /**
     * 5.0系统以上获取运行的进程方法
     * @param context
     * @param version
     * @return
     */
    private List<AppEntity> getAndroidProcess(Context context) {
        List<AppEntity> resule = new ArrayList<AppEntity>();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm = context.getPackageManager();
        AppUtils proutils = new AppUtils(context);
        List<AndroidAppProcess> listInfo = AndroidProcesses.getRunningAppProcesses();
        if(listInfo.isEmpty() || listInfo.size() == 0){
            return null;
        }
        for (AndroidAppProcess info : listInfo) {
            ApplicationInfo app = proutils.getApplicationInfo(info.name);
            // 过滤自己当前的应用
            if (app == null || context.getPackageName().equals(app.packageName)) {
                continue;
            }
            // 过滤系统的应用
            if ((app.flags & app.FLAG_SYSTEM) > 0) {
                continue;
            }
            AppEntity ent = new AppEntity();
            ent.setAppIcon(app.loadIcon(pm));//应用的图标
            ent.setAppName(app.loadLabel(pm).toString());//应用的名称
            ent.setPackageName(app.packageName);//应用的包名
            // 计算应用所占内存大小
            int[] myMempid = new int[] { info.pid };
            Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(myMempid);
            double memSize = memoryInfo[0].dalvikPrivateDirty / 1024.0;
            int temp = (int) (memSize * 100);
            memSize = temp / 100.0;
            ent.setMemorySize(memSize);//应用所占内存的大小

            resule.add(ent);
        }
        return resule;
    }

//
//    public static List<TaskBean> getTaskInfos() {
//        List<AndroidAppProcess> processInfos = ProcessManager.getRunningAppProcesses();
//
//        List<TaskBean> taskinfos = new ArrayList<TaskBean>();
//        // 遍历运行的程序,并且获取其中的信息
//        for (AndroidAppProcess processInfo : processInfos) {
//            TaskBean taskinfo = new TaskBean();
//            // 应用程序的包名
//            String packname = processInfo.name;
//            taskinfo.setPackageName(packname);
//            // 湖区应用程序的内存 信息
//            android.os.Debug.MemoryInfo[] memoryInfos = UIUtils.getActManager()
//                    .getProcessMemoryInfo(new int[] { processInfo.pid });
//            long memsize = memoryInfos[0].getTotalPrivateDirty() * 1024L;
//            taskinfo.setMemSize(memsize);
//            taskinfo.setPackageName(processInfo.getPackageName());
//            try {
//                // 获取应用程序信息
//                ApplicationInfo applicationInfo = UIUtils.getPacManager().getApplicationInfo(
//                        packname, 0);
//                Drawable icon = applicationInfo.loadIcon(UIUtils.getPacManager());
//                taskinfo.setIcon(icon);
//                String name = applicationInfo.loadLabel(UIUtils.getPacManager()).toString();
//                taskinfo.setName(name);
//
//                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
//                    // 用户进程
//                    taskinfo.setUser(true);
//                } else {
//                    // 系统进程
//                    taskinfo.setSystem(true);
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                // 系统内核进程 没有名称
//                taskinfo.setName(packname);
//                Drawable icon = UIUtils.getContext().getResources().getDrawable(
//                        R.drawable.ic_launcher);
//                taskinfo.setIcon(icon);
//            }
//            if (taskinfo != null) {
//                taskinfos.add(taskinfo);
//                for (int i=0;i<taskinfos.size();i++) {
//                    if (taskinfos.get(i).getPackageName().equals(Constants.PACKAGE_INFO)){
//                        taskinfos.remove(i);
//                    }
//                }
//            }
//        }
//        return taskinfos;
//    }
}
