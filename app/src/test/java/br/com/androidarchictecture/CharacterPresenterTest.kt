package br.com.androidarchictecture

import br.com.androidarchictecture.pojo.Character
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
import org.mockito.Mock

import org.mockito.Mockito.*
import javax.inject.Inject
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import org.junit.After
import org.junit.Assert
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


/**
 * Created by pedrohenrique on 27/07/17.
 */
class CharacterPresenterTest{

    @Mock
    lateinit var mActivityView: ActivityView

    @Mock
    lateinit var mListCharactersView: ListCharactersView

    @Mock
    lateinit var mCharacterInteractor: CharacterInteractor

    lateinit var mCharacterPresenter: Presenter

    @Before
    fun setup(){

        MockitoAnnotations.initMocks(this)

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

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @After
    fun tearDown() {
        RxAndroidPlugins.reset()
    }


    @Test
    fun testLoadCharacters(){

        var listCharacter: MutableList<Character> = mutableListOf()
        `when`(mCharacterInteractor.loadCharacters(0)).thenReturn(Observable.just(listCharacter))
        mCharacterPresenter.loadCharacters(0)
        verify(mListCharactersView).loadCharacters(listCharacter)

    }

}