package com.tubes.musicappproject

data class MusicTrack(
    val id: Long = 0,
    val title: String,
    val album: String,
    val artist: String,
    val path: String,
    val duration: Long = 0,
    val artUri: String = "") {
}