package com.example.justsomeapplication.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
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

    override var navigator: Navigator? = object : SupportAppNavigator(this, R.id.flContainer) {
        override fun setupFragmentTransaction(
            command: Command?,
            currentFragment: Fragment?,
            nextFragment: Fragment?,
            fragmentTransaction: FragmentTransaction?
        ) {
            super.setupFragmentTransaction(
                command,
                currentFragment,
                nextFragment,
                fragmentTransaction
            )
            if (currentFragment != null)
                fragmentTransaction?.setCustomAnimations(
                    R.anim.enter,
                    R.anim.exit,
                    R.anim.back,
                    R.anim.away
                )
        }
    }
}
