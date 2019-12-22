package com.example.justsomeapplication.ui.user

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

class UserActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override val layoutResource: Int
        get() = R.layout.a_common

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setLaunchScreen(Screens.UserScreen())
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override var navigator: Navigator? = SupportAppNavigator(this, R.id.flContainer)
}
