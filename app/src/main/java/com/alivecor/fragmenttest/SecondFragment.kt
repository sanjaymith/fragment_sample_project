package com.alivecor.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alivecor.fragmenttest.databinding.FragmentSecondBinding
import com.alivecor.fragmenttest.viewmodel.DetailsViewModel

class SecondFragment : Fragment() {

    private lateinit var secondBinding: FragmentSecondBinding
    private lateinit var mViewModel: DetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        secondBinding = FragmentSecondBinding.inflate(inflater,container,false)
        return secondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initData()
        secondBinding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    private fun initData() {
        secondBinding.firstName.text = getString(R.string.placeholder_first_name,arguments?.get("firstName").toString())
        secondBinding.lastName.text = getString(R.string.placeholder_last_name,arguments?.get("lastName").toString())
        mViewModel.ageCalculated(arguments?.get("dob").toString())

        mViewModel.ageCalculatedValue.observe(viewLifecycleOwner, Observer {
            secondBinding.age.text= getString(R.string.placeholder_age,it)
        })
    }
}