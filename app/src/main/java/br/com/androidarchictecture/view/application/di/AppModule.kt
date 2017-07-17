package br.com.androidarchictecture.view.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 14/07/17.
 */

@Module
class AppModule{

    val mApplication: Application

    constructor(mApplication: Application) {
        this.mApplication = mApplication
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

}
