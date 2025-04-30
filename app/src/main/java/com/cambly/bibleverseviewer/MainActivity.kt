package com.cambly.bibleverseviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cambly.bibleverseviewer.ui.screen.BibleVerseList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Marks this Activity as an injection target
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibleVerseList()
        }
    }
}
