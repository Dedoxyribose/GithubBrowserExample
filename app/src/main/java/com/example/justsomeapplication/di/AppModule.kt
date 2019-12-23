package com.example.justsomeapplication.di

import com.example.justsomeapplication.di.start.StartActivityModule
import com.example.justsomeapplication.di.user.UserActivityModule
import com.example.justsomeapplication.retrofit.GithubApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [UserActivityModule::class, StartActivityModule::class])
class AppModule(private val router: Router) {

    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi {
        val baseUrl = "https://api.github.com"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .build()
            .create(GithubApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLogginInterceptor = HttpLoggingInterceptor()
        httpLogginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(httpLogginInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRouter() = router

}