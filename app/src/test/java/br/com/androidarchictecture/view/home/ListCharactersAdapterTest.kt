package br.com.androidarchictecture.view.home

import android.os.Environment
import br.com.androidarchictecture.BuildConfig
import br.com.androidarchictecture.pojo.Character
import dalvik.annotation.TestTarget
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by pedrohenrique on 02/08/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class ListCharactersAdapterTest(){

    @Mock
    lateinit var mAdapter: ListCharactersAdapter

    lateinit var listCharacter: MutableList<Character>

    @Before
    fun setup(){

         listCharacter = mutableListOf(Character(),
                Character(),
                Character())

        mAdapter = ListCharactersAdapter(RuntimeEnvironment.application)
        mAdapter.setCharacter(listCharacter)
    }

    @Test
    fun testContext(){
        assertEquals(RuntimeEnvironment.application, mAdapter.context)
    }

    @Test
    fun testListContent(){
        assertEquals(listCharacter.size, mAdapter.characterList.size)
        assertEquals(listCharacter.get(0), mAdapter.characterList.get(0))
        assertEquals(listCharacter.get(1), mAdapter.characterList.get(1))
        assertEquals(listCharacter.get(2), mAdapter.characterList.get(2))
    }

    @Test
    fun testAddMoreItems(){
        var character = Character(0, "Spider-Man", "", "")
        var newListCharacter: MutableList<Character> = mutableListOf(character)

        mAdapter.characterList.addAll(newListCharacter)
        mAdapter.notifyDataSetChanged()

        assertEquals(4, mAdapter.characterList.size)
        assertEquals(character, mAdapter.characterList.get(3))
    }

}
