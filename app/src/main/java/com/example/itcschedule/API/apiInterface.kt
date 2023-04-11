package com.example.itcschedule.API

import com.example.itcschedule.Model.Schedule
import com.example.itcschedule.Model.ScheduleX
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface apiInterface {
    @GET("9bd78f2a-2229-4b2f-9736-1df4eeec0fc5")
    suspend fun getSchedule(): Response<Schedule>
}