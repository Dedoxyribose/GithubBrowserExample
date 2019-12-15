package com.example.justsomeapplication.ui.base

import com.example.justsomeapplication.Application
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Replace

abstract class BaseActivity : MvpAppCompatActivity() {

    protected open var navigator: Navigator? = null

    override fun onResumeFragments() {
        Application.INSTANCE.getNavigatorHolder().setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        Application.INSTANCE.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    protected fun setLaunchScreen(screen: Screen) {
        navigator?.applyCommands(arrayOf(Replace(screen)))
    }
}