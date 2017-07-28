package br.com.androidarchictecture.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by pedrohenrique on 27/07/17.
 */
@Module
class TestModule{

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