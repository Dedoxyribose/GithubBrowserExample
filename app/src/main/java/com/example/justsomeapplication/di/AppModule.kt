package com.example.justsomeapplication.di

import android.content.Context
import com.example.justsomeapplication.di.main.MainActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityModule::class])
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

}