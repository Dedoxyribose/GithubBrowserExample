package com.example.justsomeapplication.di.start.welcome

import com.example.justsomeapplication.ui.start.welcome.WelcomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface WelcomeFragmentModule {
    @WelcomeFragmentScope
    @ContributesAndroidInjector
    fun fragment(): WelcomeFragment?
}