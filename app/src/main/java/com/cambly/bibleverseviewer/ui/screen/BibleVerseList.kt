package com.cambly.bibleverseviewer.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cambly.bibleverseviewer.data.model.Verse
import com.cambly.bibleverseviewer.viewmodel.BibleViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun BibleVerseList(viewModel: BibleViewModel = hiltViewModel()) {
    val verses by viewModel.verses.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 56.dp)) {
        items(verses.size) { index ->
            VerseItem(verses[index])
        }
    }
}

@Composable
fun VerseItem(verse: Verse) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(16.dp)
    ) {
        Text(text = "${verse.book_name} ${verse.chapter}:${verse.verse}", style = MaterialTheme.typography.bodyLarge)
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = verse.text.trim(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}