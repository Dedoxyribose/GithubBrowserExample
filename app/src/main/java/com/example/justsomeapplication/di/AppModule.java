package com.example.justsomeapplication.di;

import android.content.Context;

import com.example.justsomeapplication.di.main.MainActivityModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = {MainActivityModule.class})
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
