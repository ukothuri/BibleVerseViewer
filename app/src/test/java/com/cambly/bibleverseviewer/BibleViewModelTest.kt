package com.cambly.bibleverseviewer // ✔ Test file for BibleViewModel

import app.cash.turbine.test // 🔄 Turbine extension for testing Flow emissions
import com.cambly.bibleverseviewer.data.model.Verse
import com.cambly.bibleverseviewer.data.repository.BibleRepository
import com.cambly.bibleverseviewer.utils.MainDispatcherRule // ⚖️ Custom JUnit rule to override Dispatchers.Main for testing
import com.cambly.bibleverseviewer.viewmodel.BibleViewModel
import io.mockk.coEvery // ✅ Used to stub coroutine calls
import io.mockk.mockk // ✅ Creates a mock instance of the repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow // 🔄 Used to return mocked flow from repository
import kotlinx.coroutines.test.runTest // 🧪 Coroutine test scope
import org.junit.Assert.assertEquals // 🧪 Assertion function
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BibleViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule() // 🧪 Overrides Dispatchers.Main with test dispatcher

    private lateinit var repository: BibleRepository // 🔧 Mocked data source
    private lateinit var viewModel: BibleViewModel // 🎯 Class under test

    @Before
    fun setup() {
        repository = mockk() // 🔧 Create a mock repository
        coEvery { repository.fetchVerses() } returns flow {
            emit(listOf(Verse("Matthew", 25, 31, "Test text"))) // 🎯 Predefined data to emit
        }
        viewModel = BibleViewModel(repository) // 🚀 Inject mock into ViewModel
    }

    @Test
    fun `test verse loaded`() = runTest {
        viewModel.verses.test { // 📡 Observe the Flow from the ViewModel
            val result = awaitItem() // ⏳ Wait for the first emission
            assertEquals(1, result.size) // ✅ Check item count
            assertEquals("Matthew", result[0].book_name) // ✅ Validate expected verse
            cancelAndConsumeRemainingEvents() // 🔚 Cleanup
        }
    }
}
