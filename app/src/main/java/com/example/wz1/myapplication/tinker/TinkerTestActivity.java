package com.example.wz1.myapplication.tinker;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wz1.myapplication.PermissionListener;
import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.systembar.BaseActivity;
import com.tencent.tinker.lib.library.TinkerLoadLibrary;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;

import java.util.List;

/**
 * Created by Administrator on 2019-01-16.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.tinker
 */
public class TinkerTestActivity extends BaseActivity {
    private TextView mTvAbi;

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.READ_LOGS",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvAbi = findViewById(R.id.tv_abi);
        mTvAbi.setText(mTvAbi.getText() + android.os.Build.CPU_ABI);

        if (Build.VERSION.SDK_INT >= 23) {//判断当前系统是不是Android6.0
            requestRuntimePermissions(PERMISSIONS_STORAGE, new PermissionListener() {
                @Override
                public void granted() {
                    //权限申请通过

                }

                @Override
                public void denied(List<String> deniedList) {
                    //权限申请未通过
                    for (String denied : deniedList) {
                        if (denied.equals("android.permission.ACCESS_FINE_LOCATION")) {
                        } else {
                        }
                    }
                }
            });
        }
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_tinker_test;
    }

    public void say(View view) {
        Toast.makeText(getApplicationContext(), "Hello 就是干 bugly tinkerId:[base-1.0.0.12]", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), "Hello World 123", Toast.LENGTH_SHORT).show();
    }

    public void string_from_so(View view) {
        String string = JniUtil.hello();
        Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
    }

    public void install_patch(View view) {
        //sdcard目录
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
//        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), getCacheDir().getAbsolutePath() + "/patch_signed_7zip.apk");
    }

    public void uninstall_patch(View view) {
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
        Tinker.with(getApplicationContext()).cleanPatch();
    }

    public void show_info(View view) {
        // add more Build Info
        final StringBuilder sb = new StringBuilder();
        Tinker tinker = Tinker.with(getApplicationContext());
        if (tinker.isTinkerLoaded()) {
            sb.append(String.format("[patch is loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
            sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

        } else {
            sb.append(String.format("[patch is not loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getApplicationContext())));
        }
        sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));

        final TextView v = new TextView(this);
        v.setText(sb);
        v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setTextColor(0xFF000000);
        v.setTypeface(Typeface.MONOSPACE);
        final int padding = 16;
        v.setPadding(padding, padding, padding, padding);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setView(v);
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void kill_myself(View view) {
        ShareTinkerInternals.killAllOtherProcess(getApplicationContext());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    // 使用Hack的方式（测试成功）
    public void load_library_hack(View view) {
        String CPU_ABI = android.os.Build.CPU_ABI;
        // 将tinker library中的 CPU_ABI架构的so 注册到系统的library path中。
        TinkerLoadLibrary.installNavitveLibraryABI(this, CPU_ABI);
    }

    // 不使用Hack的方式（测试失败）
    public void load_library_no_hack(View view) {
        String CPU_ABI = android.os.Build.CPU_ABI;
        TinkerLoadLibrary.loadLibraryFromTinker(getApplicationContext(), "lib/" + CPU_ABI, JniUtil.LIB_NAME);
    }

    // load lib/armeabi library（测试失败）
    public void load_library_armeabi(View view) {
        TinkerLoadLibrary.loadArmLibrary(getApplicationContext(), JniUtil.LIB_NAME);
    }

    // load lib/armeabi-v7a library（测试失败）
    public void load_library_armeabi_v7a(View view) {
        TinkerLoadLibrary.loadArmV7Library(getApplicationContext(), JniUtil.LIB_NAME);
    }
}
