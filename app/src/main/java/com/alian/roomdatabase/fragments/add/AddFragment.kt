package com.alian.roomdatabase.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alian.roomdatabase.R
import com.alian.roomdatabase.data.User
import com.alian.roomdatabase.data.UserViewModel
import com.alian.roomdatabase.databinding.FragmentAddBinding


class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var addBinding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBinding = FragmentAddBinding.bind(view)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        addBinding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = addBinding.etFirstName.text.toString()
        val lastName = addBinding.etLastName.text.toString()

        if (inputCheck(firstName, lastName)) {
            // create user
            val user = User(0, firstName, lastName)
            // add data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
            // navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }

}