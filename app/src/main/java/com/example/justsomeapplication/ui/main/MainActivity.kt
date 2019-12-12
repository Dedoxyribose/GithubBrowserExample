package com.example.justsomeapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.justsomeapplication.R
import com.example.justsomeapplication.ui.main.fragment.MainFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, MainFragment()).commit()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
