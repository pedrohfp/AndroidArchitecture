package br.com.androidarchictecture.presenters

import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.pojo.StringWord
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.home.CharacterPresenterImpl
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import org.junit.Before
import org.junit.Test

import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import org.junit.After
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import io.reactivex.plugins.RxJavaPlugins
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.nhaarman.mockito_kotlin.*
import org.mockito.ArgumentMatchers


/**
 * Created by pedrohenrique on 27/07/17.
 */
class CharacterPresenterTest{

    lateinit var mActivityView: ActivityView
    lateinit var mListCharactersView: ListCharactersView
    lateinit var mIndlingResource: SimpleIdlingResource
    lateinit var mCharacterInteractor: CharacterInteractor
    lateinit var mString: StringWord
    lateinit var mCharacterPresenter: Presenter

    @Before
    fun setup(){

        mActivityView = mock()
        mListCharactersView = mock()
        mIndlingResource = mock()
        mCharacterInteractor = mock()
        mString = StringWord()

        mCharacterPresenter = CharacterPresenterImpl(mActivityView, mListCharactersView, mCharacterInteractor)

        val immediate = object : Scheduler() {
            override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadCharactersSuccessful(){
        var listCharacter: MutableList<Character> = mutableListOf()
        whenever(mCharacterInteractor.loadCharacters(0, mString.word)).thenReturn(Observable.just(listCharacter))
        mCharacterPresenter.loadCharacters(0, mIndlingResource, mString.word)
        verify(mListCharactersView).loadCharacters(listCharacter, mIndlingResource, true)
    }

    @Test
    fun testLoadCharactersFailed(){
        whenever(mCharacterInteractor.loadCharacters(0, mString.word)).thenReturn(Observable.error(Exception()))
        mCharacterPresenter.loadCharacters(0, mIndlingResource, mString.word)
        verify(mListCharactersView).showMessageLoadFailed()
    }
}