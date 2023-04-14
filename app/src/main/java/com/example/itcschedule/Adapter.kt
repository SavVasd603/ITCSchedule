package com.example.itcschedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itcschedule.Model.ScheduleX

class ScheduleAdapter(private var scheduleList: List<ScheduleX>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schedule, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val schedule = scheduleList[position]
        holder.bind(schedule)
    }

    override fun getItemCount() = scheduleList.size

    fun updateSchedule(newScheduleList: List<ScheduleX>) {
        scheduleList = newScheduleList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.day)
        private val timeEndTextView: TextView = itemView.findViewById(R.id.timeEnd)
        private val timeStartTextView: TextView = itemView.findViewById(R.id.timeStart)
        private val subjectTextView: TextView = itemView.findViewById(R.id.subject)
        private val teacherTextView: TextView = itemView.findViewById(R.id.teacher)
        private val roomTextView: TextView = itemView.findViewById(R.id.room)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeOfSubj)

        fun bind(schedule: ScheduleX) {
            dayTextView.text = schedule.day
            timeEndTextView.text = schedule.endTime
            timeStartTextView.text = schedule.startTime
            subjectTextView.text = schedule.subject
            teacherTextView.text = schedule.teacherName
            roomTextView.text = schedule.roomNumber
            typeTextView.text = schedule.typeOfSubject
        }
    }
}