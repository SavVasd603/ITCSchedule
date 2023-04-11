package com.example.itcschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itcschedule.API.ApiClient
import com.example.itcschedule.Model.ScheduleX
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ScheduleAdapter
    private lateinit var scheduleList: List<ScheduleX>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ScheduleAdapter(emptyList())
        recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            val response = withContext(Dispatchers.IO) { ApiClient.scheduleApi.getSchedule() }

            if (response.isSuccessful) {
                val schedule = response.body()?.schedule
                scheduleList = schedule ?: emptyList()
                adapter = ScheduleAdapter(scheduleList)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this@MainActivity, "Error loading schedule", Toast.LENGTH_SHORT).show()
            }
        }
    }
}