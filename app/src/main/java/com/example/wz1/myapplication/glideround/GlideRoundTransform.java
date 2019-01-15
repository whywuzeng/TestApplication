package com.example.wz1.myapplication.glideround;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2019-01-14.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.glideround
 */
public class GlideRoundTransform extends BitmapTransformation{

    //设置获取 radius 角度
    public final float radius ;


    private static final String ID = "com.example.wz1.myapplication.glideround.GlideRoundTransform";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);


    public GlideRoundTransform() {
        this(4f);
    }

    public  GlideRoundTransform(float raduispx) {
        radius = Resources.getSystem().getDisplayMetrics().density * raduispx;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GlideRoundTransform && ((GlideRoundTransform) o).radius == radius ;
    }

    @Override
    public int hashCode() {
        return (int) (ID.hashCode()+radius*1000);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {

        return roundCrop(pool,toTransform);
    }

    private  Bitmap roundCrop(BitmapPool pool, Bitmap source) {

        if (source==null)
            return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result==null)
        {
            result= Bitmap.createBitmap(source.getWidth(),source.getHeight(),Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        //像素衍生  钳  像钳子一样拉开
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //方锯齿
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF,radius,radius,paint);
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID+radius).getBytes(CHARSET));
    }
}
