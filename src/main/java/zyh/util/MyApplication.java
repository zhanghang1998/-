package zyh.util;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DiskCacheConfig cacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("image")
                .setMaxCacheSize(8*1024*1024)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(cacheConfig)
                .build();

        //初始化
        Fresco.initialize(this,config);

    }
}
