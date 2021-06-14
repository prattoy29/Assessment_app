package com.example.assessmentapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentapp.databinding.RecyclerlayoutBinding
import com.example.assessmentapp.model.Barcodes

class RecyclerAdapter(private val codelist : ArrayList<Barcodes>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        var layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        var recyclerlayoutBinding : RecyclerlayoutBinding = RecyclerlayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(recyclerlayoutBinding)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val current = codelist[position]
        holder.recyclerlayoutBinding.codes = current
    }

    override fun getItemCount(): Int {
        return codelist.size
    }

    inner class ViewHolder(recyclerlayoutBinding: RecyclerlayoutBinding) : RecyclerView.ViewHolder(recyclerlayoutBinding.root)
    {
        var recyclerlayoutBinding : RecyclerlayoutBinding = recyclerlayoutBinding
    }

}