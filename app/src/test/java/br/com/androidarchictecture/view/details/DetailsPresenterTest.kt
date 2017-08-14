package br.com.androidarchictecture.view.details

import br.com.androidarchictecture.view.details.contract.DetailsActivityView
import br.com.androidarchictecture.view.details.contract.DetailsCharacterView
import br.com.androidarchictecture.view.details.contract.DetailsPresenter
import br.com.androidarchictecture.view.home.contract.CharacterInteractor
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.util.SimpleIdlingResource
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable

/**
 * Created by pedrohenrique on 08/08/17.
 */
class DetailsPresenterTest{
    lateinit var mIdlingResource: SimpleIdlingResource
    lateinit var mDetailsActivityView: DetailsActivityView
    lateinit var mDetailsCharacterView: DetailsCharacterView
    lateinit var mCharacterInteractor: CharacterInteractor
    lateinit var mDetailsPresenter: DetailsPresenter

    @Before
    fun setup(){
        mIdlingResource = mock()
        mDetailsActivityView = mock()
        mDetailsCharacterView = mock()
        mCharacterInteractor = mock()

        mDetailsPresenter = DetailsPresenterImpl(mDetailsActivityView, mDetailsCharacterView, mCharacterInteractor)

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
    fun testLoadCharactersDetailsSuccessful(){
        var character = Character()
        whenever(mCharacterInteractor.loadCharacterDetails(any())).thenReturn(Observable.just(character))
        mDetailsPresenter.loadDetailsCharacter(any(), mIdlingResource)
        verify(mDetailsCharacterView).showCharacterDetails(character, mIdlingResource)
    }

    @Test
    fun testLoadCharactersDetailsFailed(){
        whenever(mCharacterInteractor.loadCharacterDetails(any())).thenReturn(Observable.error(Exception()))
        mDetailsPresenter.loadDetailsCharacter(any(), mIdlingResource)
        verify(mDetailsCharacterView).showMessageLoadFailed()
    }
}