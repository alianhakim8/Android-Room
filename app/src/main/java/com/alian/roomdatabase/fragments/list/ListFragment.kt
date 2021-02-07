package com.alian.roomdatabase.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alian.roomdatabase.R
import com.alian.roomdatabase.adapter.ListAdapter
import com.alian.roomdatabase.viewmodel.UserViewModel
import com.alian.roomdatabase.databinding.FragmentListBinding


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

        // add menu
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete) {
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes") { _, _ ->
                mUserViewModel.deleteAllUsers()
                Toast.makeText(
                    requireContext(),
                    "Successfully Remove all user",
                    Toast.LENGTH_SHORT
                ).show()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            builder.setTitle("Delete All User ?")
            builder.setMessage("Are you sure ? ")
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }


}