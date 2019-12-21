package com.example.justsomeapplication.ui.start

import android.os.Bundle
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class StartActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_start)
        setLaunchScreen(Screens.WelcomeScreen())
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override var navigator: Navigator? = SupportAppNavigator(this, R.id.flContainer)
    override val layoutResource: Int
        get() = 0

}