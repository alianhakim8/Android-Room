package com.alian.roomdatabase.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBinding = FragmentUpdateBinding.bind(view)

        updateBinding.etFirstName.setText(args.currentUser.firstName)
        updateBinding.etLastName.setText(args.currentUser.lastName)

        updateBinding.btnUpdate.setOnClickListener {
            updateItem()
        }

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

}