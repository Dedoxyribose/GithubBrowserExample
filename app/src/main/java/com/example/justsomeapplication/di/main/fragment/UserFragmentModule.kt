package com.example.justsomeapplication.di.main.fragment

import com.example.justsomeapplication.ui.main.fragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserFragmentModule {
    @UserFragmentScope
    @ContributesAndroidInjector(modules = [UserFragmentDependenciesModule::class])
    fun fragment(): UserFragment?
}