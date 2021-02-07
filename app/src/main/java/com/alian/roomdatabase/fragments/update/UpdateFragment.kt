package com.alian.roomdatabase.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alian.roomdatabase.R
import com.alian.roomdatabase.viewmodel.UserViewModel
import com.alian.roomdatabase.databinding.FragmentUpdateBinding
import com.alian.roomdatabase.model.User

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private lateinit var updateBinding: FragmentUpdateBinding
    private lateinit var mUserViewModel: UserViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        updateBinding = FragmentUpdateBinding.bind(view)

        updateBinding.etFirstName.setText(args.currentUser.firstName)
        updateBinding.etLastName.setText(args.currentUser.lastName)

        updateBinding.btnUpdate.setOnClickListener {
            updateItem()
        }
        // Add menu
        setHasOptionsMenu(true)

    }

    private fun updateItem() {
        val firstName = updateBinding.etFirstName.text.toString()
        val lastName = updateBinding.etLastName.text.toString()
        if (inputCheck(firstName, lastName)) {
            // create user object
            val updateUser = User(args.currentUser.id, firstName, lastName)
            // update current user
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Update successful", Toast.LENGTH_SHORT).show()
            // navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully Remove ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName} ?")
        builder.setMessage("Are you sure want to delete ${args.currentUser.firstName} ? ")
        builder.show()
    }

}