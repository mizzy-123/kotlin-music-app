package com.tubes.musicappproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv_library: RecyclerView
    private val list = ArrayList<GridLibrary>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_library = findViewById(R.id.rv_grid_library)
        rv_library.setHasFixedSize(true)

        list.addAll(dataGridLibrary.dataGrid)
        showRecyclerGrid()
        requestRuntimePermission()
    }

    private fun requestRuntimePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), 121)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 121){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRecyclerGrid() {
        rv_library.layoutManager = GridLayoutManager(this, 2)
        val gridLibraryAdapter = GridLibraryAdapter(list)
        rv_library.adapter = gridLibraryAdapter

        gridLibraryAdapter.setOnItemClickCallback(object : GridLibraryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GridLibrary) {
                showSelectedList(data)
            }
        })
    }

    private fun showSelectedList(library: GridLibrary) {
        when {
            library.id.equals("1") -> {

            }
            library.id.equals("2") -> {
                val intent = Intent(this, TrackActivity::class.java)
                startActivity(intent)
            }
            library.id.equals("3") -> {

            }
            library.id.equals("4") -> {

            }
        }
        Toast.makeText(this, "Kamu memilih " + library.nameLibrary, Toast.LENGTH_SHORT).show()
    }
}