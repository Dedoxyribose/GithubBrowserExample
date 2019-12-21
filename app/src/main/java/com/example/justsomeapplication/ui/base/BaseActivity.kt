package com.example.justsomeapplication.ui.base

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.example.justsomeapplication.Application
import kotlinx.android.synthetic.main.v_appbar.*
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Replace

abstract class BaseActivity : MvpAppCompatActivity() {

    protected open var navigator: Navigator? = null
    @get:LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

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

    fun setTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        } else {
            super.setTitle(title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}