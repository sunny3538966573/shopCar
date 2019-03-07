package com.bw.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by 1607c王晴
 * date 2019/3/6
 * Describe:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig shopCar = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("shopCar")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();

        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(shopCar).build();
        Fresco.initialize(this,build);
    }
}
