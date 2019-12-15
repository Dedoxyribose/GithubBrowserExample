package com.example.justsomeapplication.ui.main

import android.os.Bundle
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.base.BaseActivity
import com.example.justsomeapplication.ui.main.fragment.MainFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_common)
        supportFragmentManager.beginTransaction().add(R.id.flContainer, MainFragment()).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
