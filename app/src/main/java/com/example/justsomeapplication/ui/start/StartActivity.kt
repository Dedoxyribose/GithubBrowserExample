package com.example.justsomeapplication.ui.start

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.start.welcome.WelcomeFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class StartActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_common)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, WelcomeFragment()).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
