package com.tubes.musicappproject

import android.content.ContentUris
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    companion object {
        lateinit var musicListPlayer: ArrayList<MusicTrack>
        var songPosition: Int = 0
        var mediaPlayer: MediaPlayer? = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)



        try {
            val bundle: Bundle? = intent.extras
            musicListPlayer = DataMusic.getAllAudioTrack(this@PlayerActivity)
            if (bundle != null){
                songPosition = bundle.getInt("position", 0)
            }

            player_judul.text = musicListPlayer[songPosition].title
            player_album.text = musicListPlayer[songPosition].album
            player_artist.text = musicListPlayer[songPosition].artist

            Glide.with(this)
                .load(musicListPlayer[songPosition].artUri)
                .apply(RequestOptions().placeholder(R.drawable.cover_white).fitCenter())
                .into(img_player)

//            val play = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, musicListPlayer[songPosition].id).toString()
            val contentUri: Uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, musicListPlayer[songPosition].id)
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.reset()
            mediaPlayer?.setDataSource(applicationContext, contentUri)
            mediaPlayer!!.prepare()
            mediaPlayer?.start()

        }catch (e: Exception){

        }

    }
}