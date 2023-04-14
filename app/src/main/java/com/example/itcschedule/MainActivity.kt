package com.example.itcschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itcschedule.API.ApiClient
import com.example.itcschedule.Model.ScheduleX
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ScheduleAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.scheduleList.observe(this, { scheduleList ->
            adapter.updateSchedule(scheduleList)
        })
        viewModel.errorMessage.observe(this, { errorMessage ->
            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
        })
        viewModel.loading.observe(this, { loading ->
            // show/hide loading indicator as needed
        })

        viewModel.getSchedule()
    }
}