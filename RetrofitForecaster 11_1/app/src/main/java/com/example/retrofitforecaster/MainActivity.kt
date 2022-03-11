package com.example.retrofitforecaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recAdapter = Adapter()
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rView)
        with(recyclerView) {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recAdapter
        }

        viewModel.restoreData()
        if (viewModel.weatherList.isNullOrEmpty()) {
            viewModel.getAllWeatherList("Astrakhan", recyclerView)
        }
        recAdapter.submitList(viewModel.weatherList)
    }
}