package com.cambly.bibleverseviewer.data.remote

import com.cambly.bibleverseviewer.data.model.Verse

data class BibleResponse(
    val verses: List<Verse>
)