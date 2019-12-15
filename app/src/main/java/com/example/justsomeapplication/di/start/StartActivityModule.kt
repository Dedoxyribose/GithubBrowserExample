package com.example.justsomeapplication.di.start

import com.example.justsomeapplication.di.start.welcome.WelcomeFragmentModule
import com.example.justsomeapplication.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface StartActivityModule {
    @StartScope
    @ContributesAndroidInjector(modules = [WelcomeFragmentModule::class])
    fun activity(): StartActivity?
}