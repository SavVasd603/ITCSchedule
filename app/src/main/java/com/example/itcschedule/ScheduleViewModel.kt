package com.example.itcschedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itcschedule.API.ApiClient
import com.example.itcschedule.Model.ScheduleX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ScheduleViewModel: ViewModel() {

    private val _scheduleList = MutableLiveData<List<ScheduleX>>()
    val scheduleList: LiveData<List<ScheduleX>> = _scheduleList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getSchedule() {
        viewModelScope.launch {
            _loading.value = true
            val response = withContext(Dispatchers.IO) { ApiClient.scheduleApi.getSchedule() }

            if (response.isSuccessful) {
                _scheduleList.value = response.body()?.schedule ?: emptyList()
            } else {
                _errorMessage.value = "Error loading schedule"
            }
            _loading.value = false
        }
    }

}