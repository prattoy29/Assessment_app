package com.example.assessmentapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assessmentapp.R
import com.example.assessmentapp.adapters.RecyclerAdapter3
import com.example.assessmentapp.repository.Repository


class Fragment3 : Fragment() {

    lateinit var viewModel: FragmentViewModel
    private val myAdapter by lazy { RecyclerAdapter3() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var recyclerView : RecyclerView = view.findViewById(R.id.recycle3)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val repository = Repository()
        val viewModelFactory = FragmentViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(FragmentViewModel::class.java)
        viewModel.getCustomPosts()
        viewModel.myCustomPost.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
            }
        })

    }
}