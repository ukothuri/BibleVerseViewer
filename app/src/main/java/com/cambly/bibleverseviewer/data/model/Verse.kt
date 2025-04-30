package com.cambly.bibleverseviewer.data.model

data class Verse(
    val book_name: String,
    val chapter: Int,
    val verse: Int,
    val text: String
)