package com.example.justsomeapplication.di

import android.content.Context
import com.example.justsomeapplication.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: Application?)
}