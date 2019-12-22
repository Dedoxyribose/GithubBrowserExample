package com.example.justsomeapplication.di.user.fragment

import com.example.justsomeapplication.ui.user.fragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserFragmentModule {
    @UserFragmentScope
    @ContributesAndroidInjector
    fun fragment(): UserFragment?
}