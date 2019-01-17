package com.example.wz1.myapplication;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Administrator on 2019-01-17.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */
public class PPApplication extends TinkerApplication {

    public PPApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.example.wz1.myapplication.tinker.TinkerApplicationLike", "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
