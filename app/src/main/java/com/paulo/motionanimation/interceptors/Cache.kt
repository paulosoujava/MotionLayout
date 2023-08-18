package com.paulo.motionanimation.interceptors

import android.content.Context
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.util.concurrent.TimeUnit

class CacheInterceptorCache : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}
class ForceCacheInterceptorCache : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!IsInternetAvailable()) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        }
        return chain.proceed(builder.build());
    }
}


fun getContext(applicationContext: Context) = OkHttpClient().newBuilder()
    .cache(Cache(File(applicationContext.cacheDir, "http-cache"), 10L * 1024L * 1024L)) // 10 MiB
    .addNetworkInterceptor(CacheInterceptorCache())//// only if Cache-Control header is not enabled from the server
    .addInterceptor(ForceCacheInterceptor())
    .build();

//Depois disso, podemos usar isso okHttpClientdiretamente
// ou com o Retrofit e funcionará conforme o esperado.