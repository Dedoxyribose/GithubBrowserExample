package com.example.justsomeapplication.di.user.userinfo

import com.example.justsomeapplication.ui.user.userinfo.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserFragmentModule {
    @UserFragmentScope
    @ContributesAndroidInjector
    fun fragment(): UserFragment?
}