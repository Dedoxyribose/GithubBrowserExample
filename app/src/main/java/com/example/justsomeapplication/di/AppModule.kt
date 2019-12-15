package com.example.justsomeapplication.di

import android.content.Context
import com.example.justsomeapplication.di.main.MainActivityModule
import com.example.justsomeapplication.di.start.StartActivityModule
import dagger.Module
import dagger.Provides

@Module(includes = [MainActivityModule::class, StartActivityModule::class])
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

}