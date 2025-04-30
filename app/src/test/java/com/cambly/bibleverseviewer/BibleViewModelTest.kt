package com.cambly.bibleverseviewer

import app.cash.turbine.test
import com.cambly.bibleverseviewer.data.model.Verse
import com.cambly.bibleverseviewer.data.repository.BibleRepository
import com.cambly.bibleverseviewer.utils.MainDispatcherRule
import com.cambly.bibleverseviewer.viewmodel.BibleViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BibleViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var repository: BibleRepository
    private lateinit var viewModel: BibleViewModel

    @Before
    fun setup() {
        repository = mockk()
        coEvery { repository.fetchVerses() } returns flow {
            emit(listOf(Verse("Matthew", 25, 31, "Test text")))
        }
        viewModel = BibleViewModel(repository)
    }

    @Test
    fun `test verse loaded`() = runTest {
        viewModel.verses.test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Matthew", result[0].book_name)
            cancelAndConsumeRemainingEvents()
        }
    }
}
