package br.com.androidarchictecture

import br.com.androidarchictecture.application.TestApplication
import br.com.androidarchictecture.dagger.DaggerTestCharacterComponent
import br.com.androidarchictecture.pojo.Character
import br.com.androidarchictecture.view.application.MarvelApplication
import br.com.androidarchictecture.view.home.CharacterPresenter
import br.com.androidarchictecture.view.home.MainActivity
import br.com.androidarchictecture.view.home.contract.ActivityView
import br.com.androidarchictecture.view.home.contract.CharacterInteractorContract
import br.com.androidarchictecture.view.home.contract.ListCharactersView
import br.com.androidarchictecture.view.home.contract.Presenter
import br.com.androidarchictecture.view.home.di.CharacterPresenterModule
import br.com.androidarchictecture.view.home.di.DaggerCharacterComponent
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.*
import javax.inject.Inject

/**
 * Created by pedrohenrique on 27/07/17.
 */
class CharacterPresenterTest{

    @Mock
    lateinit var mActivityView: ActivityView

    @Mock
    lateinit var mListCharactersView: ListCharactersView

    @Inject
    lateinit var mCharacterInteractor: CharacterInteractorContract

    @Inject
    lateinit var mCharacterPresenter: CharacterPresenter

    @Before
    fun setup(){
        mActivityView = mock(ActivityView::class.java)
        mListCharactersView = mock(ListCharactersView::class.java)

        DaggerTestCharacterComponent.builder()
                .testComponent(TestApplication.mTestComponent)
                .characterPresenterModule(CharacterPresenterModule(mActivityView, mListCharactersView!!))
                .build()
                .inject(this)
    }

    @Test
    fun testLoadCharacters(){
        `when`(mCharacterInteractor.loadCharacters(0)).thenReturn(Observable.just(mutableListOf()))
        mCharacterPresenter.loadCharacters(0)
        verify(mListCharactersView).loadCharacters(ArgumentMatchers.any())
    }

}