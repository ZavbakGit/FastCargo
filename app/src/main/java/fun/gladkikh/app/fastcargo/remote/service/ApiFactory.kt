package `fun`.gladkikh.app.fastcargo.remote.service

import `fun`.gladkikh.app.fastcargo.remote.util.LogJSONInterceptor
import `fun`.gladkikh.app.fastpallet8.Constants
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *The interface which provides methods to get result of webservices
 */
class ApiFactory {



    private val configInterceptor = Interceptor {
        val configUrl = it.request().url()
            .newBuilder()
            .addQueryParameter("uid", Constants.UID)
            .addQueryParameter("platform", "Android")
            .addQueryParameter("app_version", Constants.APP_VERSION)
            .addQueryParameter("version_os", Constants.OS_VERSION)
            .build()

        val configRequest = it.request()
            .newBuilder()
            .url(configUrl)
            .build()

        it.proceed(configRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val authClient = OkHttpClient().newBuilder()
        .addInterceptor(configInterceptor)
        .addInterceptor(LogJSONInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()




    private fun getRetrofit(host: String): Retrofit {
       return Retrofit.Builder()
            .baseUrl(host)
            .client(authClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getApi(host: String) = getRetrofit(host).create(ApiService::class.java)



}