package com.example.justsomeapplication.di.main.fragment;

import com.example.justsomeapplication.ui.main.fragment.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainFragmentModule {

    @MainFragmentScope
    @ContributesAndroidInjector
    MainFragment fragment();
}
