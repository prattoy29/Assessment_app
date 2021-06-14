package com.example.assessmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentapp.databinding.Recyclerlayout3Binding
import com.example.assessmentapp.model.Userinfo

class RecyclerAdapter3() : RecyclerView.Adapter<RecyclerAdapter3.ViewHolder>(){

    private var userlist = emptyList<Userinfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter3.ViewHolder {
        var layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        var recyclerlayoutBinding : Recyclerlayout3Binding = Recyclerlayout3Binding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerlayoutBinding)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    inner class ViewHolder(recyclerlayoutBinding: Recyclerlayout3Binding) : RecyclerView.ViewHolder(recyclerlayoutBinding.root)
    {
        var recyclerlayoutBinding : Recyclerlayout3Binding = recyclerlayoutBinding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = userlist[position]
        holder.recyclerlayoutBinding.post = current
    }

    fun setData(newList: List<Userinfo>)
    {
        userlist = newList
        notifyDataSetChanged()
    }
}