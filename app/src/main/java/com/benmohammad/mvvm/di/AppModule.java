package com.benmohammad.mvvm.di;

import android.content.Context;

import com.benmohammad.mvvm.App;
import com.benmohammad.mvvm.common.domain.model.CommonGreetingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    CommonGreetingRepository provideCommonHelloService() {
        return new CommonGreetingRepository();
    }
}
