package com.example.myapplicationandroid2023.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private VolleySingleton(Context context)
    {
        this.requestQueue = Volley.newRequestQueue(context);
        this.imageLoader = new ImageLoader(
            this.requestQueue,
            new ImageLoader.ImageCache()
            {
                private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(10);

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url, bitmap);
                }
            }
        );
    }
    public static VolleySingleton getInstance(Context context)
    {
        if(instance == null)
            instance = new VolleySingleton(context);
        return instance;
    }
    public ImageLoader getImageLoader()
    {
        return this.imageLoader;
    }
}
