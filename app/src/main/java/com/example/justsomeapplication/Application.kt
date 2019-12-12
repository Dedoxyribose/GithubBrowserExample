package com.example.justsomeapplication

import android.app.Application
import com.example.justsomeapplication.di.AppModule
import com.example.justsomeapplication.di.ApplicationComponent
import com.example.justsomeapplication.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class Application : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .context(this)
            .appModule(AppModule(this))
            .build()

        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
