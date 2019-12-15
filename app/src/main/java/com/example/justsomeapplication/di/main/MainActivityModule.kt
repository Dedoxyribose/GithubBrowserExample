package com.example.justsomeapplication.di.main

import com.example.justsomeapplication.di.main.fragment.MainFragmentModule
import com.example.justsomeapplication.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @MainActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    fun activity(): MainActivity?
}