package com.alivecor.fragmenttest

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alivecor.fragmenttest.databinding.FragmentFirstBinding
import java.text.SimpleDateFormat
import java.util.*


class FirstFragment : Fragment() {

    private lateinit var firstFragmentFirstBinding: FragmentFirstBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        firstFragmentFirstBinding= FragmentFirstBinding.inflate(inflater, container,false)
        return firstFragmentFirstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListner()
    }

    private fun setClickListner() {

        firstFragmentFirstBinding.buttonFirst.setOnClickListener {
            val firstName = firstFragmentFirstBinding.editTextFirstName.text
            val lastName = firstFragmentFirstBinding.editTextSecondName.text
            val dob =firstFragmentFirstBinding.editTextDob.text
            if(!firstName.isNullOrBlank() && !lastName.isNullOrBlank() && !dob.isNullOrBlank()) {
                val bundle = bundleOf(
                    "firstName" to firstName?.trim(),
                    "lastName" to lastName?.trim(),
                    "dob" to dob?.trim()
                )
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }
        firstFragmentFirstBinding.editTextDob.setOnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                val currentDate: Calendar = Calendar.getInstance()
                var mDay = currentDate.get(Calendar.DAY_OF_MONTH)
                var mMonth = currentDate.get(Calendar.MONTH)
                var mYear = currentDate.get(Calendar.YEAR)
                val datePickerDialog =
                    activity?.let { it1 ->
                        DatePickerDialog(
                            it1,
                            OnDateSetListener { _, year, month, dayOfMonth ->
                                firstFragmentFirstBinding.editTextDob.setText(getString(R.string.placeholder_date_of_birth,String.format("%02d", dayOfMonth),String.format("%02d", month+1),year))
                            }, mYear, mMonth, mDay)
                    }
                datePickerDialog?.show()
            }
        }
    }
}