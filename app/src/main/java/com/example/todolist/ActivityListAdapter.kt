package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ActivityListAdapter(context: Context, val resource: Int, val data: ArrayList<String>) :
    ArrayAdapter<String>(context, resource, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return createViewFromResource(inflater, position, convertView, parent)
    }

    private fun createViewFromResource(
        inflater: LayoutInflater,
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val name = data[position]
        val view: View = convertView ?: inflater.inflate(resource, parent, false)
        val newActivityView = view.findViewById<TextView>(R.id.activityTitle)

        newActivityView.text = name
        return view

    }
}