package com.cambly.bibleverseviewer.utils // 📦 JUnit test rule location

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher // 🔍 Used to create custom test rule
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule( // ⚖️ A JUnit Rule to replace Dispatchers.Main with a test dispatcher
    private val dispatcher: TestDispatcher = StandardTestDispatcher() // 🧪 Deterministic coroutine execution
) : TestWatcher() {

    override fun starting(description: Description) {
        Dispatchers.setMain(dispatcher) // 🔄 Replaces Dispatchers.Main before each test
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain() // 🔁 Restores Dispatchers.Main after each test
    }
}
