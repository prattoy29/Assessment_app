package com.example.assessmentapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentapp.R
import com.example.assessmentapp.adapters.RecyclerAdapter
import com.example.assessmentapp.model.Barcodes
import com.google.firebase.database.*

class Fragment2 : Fragment() {

    private lateinit var ref : DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var codelist : ArrayList<Barcodes>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_2, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter(view)

    }

    private fun setUpAdapter(view: View) {
        recyclerView = view.findViewById(R.id.recycle2)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        codelist = arrayListOf<Barcodes>()
        clearData()
    }

    private fun getCodes() {
        ref = FirebaseDatabase.getInstance().getReference("Barcode Information")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    for (usersnapshot in snapshot.children)
                    {
                        val code = usersnapshot.getValue(Barcodes::class.java)
                        codelist.add(code!!)

                    }
                    recyclerView.adapter = RecyclerAdapter(codelist)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                else{
                    Toast.makeText(context, "No barcode available", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Database Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onResume() {
        super.onResume()
        clearData()
        getCodes()
    }

    override fun onPause() {
        super.onPause()
        clearData()
    }

    private fun clearData() {
        codelist.clear()
        recyclerView.adapter?.notifyDataSetChanged()
    }



}