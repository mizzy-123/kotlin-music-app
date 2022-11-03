package com.tubes.musicappproject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrackActivity : AppCompatActivity() {
    private lateinit var rv_track: RecyclerView
    private lateinit var list: ArrayList<MusicTrack>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        rv_track = findViewById(R.id.rv_grid_track)
        rv_track.setHasFixedSize(true)

        list = DataMusic.getAllAudioTrack(this@TrackActivity)
        showRecyclerGrid()
    }

    private fun showRecyclerGrid() {
        rv_track.layoutManager = GridLayoutManager(this@TrackActivity, 2)
        val gridTrackAdapter = GridTrackAdapter(list)
        rv_track.adapter = gridTrackAdapter

//        gridTrackAdapter.setOnItemClickCallback(object : GridTrackAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: MusicTrack) {
//                showSelectedList(data)
//            }
//        })
    }

//    fun showSelectedList(track: MusicTrack){
//        val intent = Intent(this@TrackActivity, PlayerActivity::class.java)
//        startActivity(intent)
//        Toast.makeText(this, "Kamu memilih " + track.title, Toast.LENGTH_LONG).show()
//    }
}