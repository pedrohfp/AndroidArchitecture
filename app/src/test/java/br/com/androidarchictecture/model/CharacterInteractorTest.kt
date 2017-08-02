package br.com.androidarchictecture.model

import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by pedrohenrique on 02/08/17.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class CharacterInteractorTest{

    lateinit var mInteractor: CharacterInteractor

    lateinit var mRetrofit: Retrofit

    @Before
    fun setup(){

        mRetrofit = Retrofit.Builder()
                .baseUrl(MarvelApplication.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mInteractor = CharacterInteractorImpl(mRetrofit)

    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadCharacters(){
        val subscriber: TestSubscriber<MutableList<Character>> = TestSubscriber.create()
        mInteractor.loadCharacters(ArgumentMatchers.anyInt()).subscribe({ characters: MutableList<br.com.androidarchictecture.pojo.Character> ->
            assertEquals(20, characters.size)
        })

        subscriber.assertNoErrors()
    }

}