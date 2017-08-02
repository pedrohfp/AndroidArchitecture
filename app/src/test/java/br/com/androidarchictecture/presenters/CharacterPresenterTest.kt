package br.com.androidarchictecture.presenters

import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource
import br.com.androidarchictecture.view.home.CharacterPresenterImpl
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.*
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import org.junit.After
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import io.reactivex.plugins.RxJavaPlugins
import org.mockito.ArgumentMatchers
import org.mockito.Mock


/**
 * Created by pedrohenrique on 27/07/17.
 */
class CharacterPresenterTest{

    lateinit var mActivityView: ActivityView
    lateinit var mListCharactersView: ListCharactersView
    lateinit var mIndlingResource: SimpleIdlingResource
    lateinit var mCharacterInteractor: CharacterInteractor
    lateinit var mCharacterPresenter: Presenter

    @Before
    fun setup(){

        mActivityView = mock(ActivityView::class.java)
        mListCharactersView = mock(ListCharactersView::class.java)
        mIndlingResource = mock(SimpleIdlingResource::class.java)
        mCharacterInteractor = mock(CharacterInteractor::class.java)

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
        `when`(mCharacterInteractor.loadCharacters(ArgumentMatchers.anyInt())).thenReturn(Observable.just(listCharacter))
        mCharacterPresenter.loadCharacters(ArgumentMatchers.anyInt(), mIndlingResource)
        verify(mListCharactersView).loadCharacters(listCharacter, mIndlingResource)
    }

    @Test
    fun testLoadCharactersFailed(){
        `when`(mCharacterInteractor.loadCharacters(ArgumentMatchers.anyInt())).thenReturn(Observable.error(Exception()))
        mCharacterPresenter.loadCharacters(ArgumentMatchers.anyInt(), mIndlingResource)
        verify(mListCharactersView).showMessageLoadFailed()
    }

}