package com.example.justsomeapplication.di.user

import com.example.justsomeapplication.di.user.fragment.UserFragmentModule
import com.example.justsomeapplication.ui.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface UserActivityModule {
    @UserScope
    @ContributesAndroidInjector(
        modules = [
            UserFragmentModule::class,
            UserDependenciesModule::class]
    )
    fun activity(): UserActivity?
}