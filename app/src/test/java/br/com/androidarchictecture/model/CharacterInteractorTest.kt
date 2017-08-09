package br.com.androidarchictecture.model

import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.pojo.*
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import com.nhaarman.mockito_kotlin.any
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
import io.reactivex.Observer

/**
 * Created by pedrohenrique on 02/08/17.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class CharacterInteractorTest{

    lateinit var mInteractor: CharacterInteractor

    lateinit var mRetrofit: Retrofit

    private val spiderManId = 1009610

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
        val charactersList: MutableList<Character> = mutableListOf()
        mInteractor.loadCharacters(0, "").subscribe({ characters: MutableList<Character> ->
            charactersList.addAll(characters)
        })

        assertEquals(20, charactersList.size)
        subscriber.assertNoErrors()
    }

    @Test
    fun testLoadCharactersBySearch(){
        val subscriber: TestSubscriber<MutableList<Character>> = TestSubscriber.create()
        val charactersList: MutableList<Character> = mutableListOf()
        mInteractor.loadCharacters(0, "Spider-Man").subscribe({ characters: MutableList<Character> ->
            charactersList.addAll(characters)
        })

        assertNotNull(charactersList)
        assertTrue(0 != charactersList.size)
        subscriber.assertNoErrors()
    }

    @Test
    fun testLoadCharacterDetails(){
        val subscriber: TestSubscriber<MutableList<Character>> = TestSubscriber.create()
        var characterDetails = Character()
        mInteractor.loadCharacterDetails(spiderManId.toLong()).subscribe({ character: Character ->
            characterDetails = character
        })

        assertNotNull(characterDetails)
        assertTrue(!characterDetails.mName.isEmpty())
        subscriber.assertNoErrors()
    }
}