package com.example.justsomeapplication

import android.app.Application
import com.example.justsomeapplication.di.AppModule
import com.example.justsomeapplication.di.ApplicationComponent
import com.example.justsomeapplication.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class Application : Application(), HasAndroidInjector {

    companion object {
        lateinit var INSTANCE: com.example.justsomeapplication.Application
        private lateinit var cicerone: Cicerone<Router>
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        initCicerone()

        component = DaggerApplicationComponent.builder()
            .context(this)
            .appModule(AppModule(cicerone.router))
            .build()

        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }
}
