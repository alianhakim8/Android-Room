package com.alian.roomdatabase.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alian.roomdatabase.R
import com.alian.roomdatabase.adapter.ListAdapter
import com.alian.roomdatabase.data.UserViewModel
import com.alian.roomdatabase.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBinding = FragmentListBinding.bind(view)

        // Recycler View
        val adapter = ListAdapter()
        val recyclerView = listBinding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // user view model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        listBinding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

}